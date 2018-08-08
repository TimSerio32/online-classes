//
//  ViewController.swift
//  PickingImages
//
//  Created by Tim Serio on 8/13/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate {
    @IBOutlet weak var toolbar: UIToolbar!
    @IBOutlet weak var albumBtn: UIBarButtonItem!
    @IBOutlet weak var cameraBtn: UIBarButtonItem!
    @IBOutlet weak var saveBtn: UIBarButtonItem!
    @IBOutlet weak var imagePickerView: UIImageView!
    @IBOutlet weak var topTextField: UITextField!
    @IBOutlet weak var bottomTextField: UITextField!
    
    var clearTopTextField = true
    var clearBottomTextField = true
    
    let memeTextAttributes:[String:Any] = [
        NSStrokeColorAttributeName: UIColor.black,
        NSForegroundColorAttributeName: UIColor.white,
        NSFontAttributeName: UIFont(name: "HelveticaNeue-CondensedBlack", size: 40)!,
        NSStrokeWidthAttributeName: -5.0]
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if !UIImagePickerController.isSourceTypeAvailable(.camera) {
            cameraBtn.isEnabled = false
        }
        if imagePickerView.image == nil {
            topTextField.isHidden = true
            bottomTextField.isHidden = true
        } else {
            topTextField.frame.size.width = 1/3 * view.frame.size.width
            bottomTextField.frame.size.width = 1/3 * view.frame.size.width
            topTextField.isHidden = false
            bottomTextField.isHidden = false
            topTextField.text = "TOP"
            bottomTextField.text = "BOTTOM"
            topTextField.textAlignment = .center
            bottomTextField.textAlignment = .center
        }
        topTextField.defaultTextAttributes = memeTextAttributes
        bottomTextField.defaultTextAttributes = memeTextAttributes
        
        self.subscribeToKeyboardNotifications()
        

    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.tabBarController?.tabBar.isHidden = true
        topTextField.delegate = self
        bottomTextField.delegate = self
        
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        self.unsubscribeFromKeyboardNotifications()
    }
    
    func textField(_ textField: UITextField, shouldChangeCharactersIn range: NSRange, replacementString string: String) -> Bool {
        textField.text = (textField.text! as NSString).replacingCharacters(in: range, with: string.uppercased())
        return false
    }
   
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if textField == topTextField {
            if clearTopTextField {
                clearTopTextField = false
                topTextField.text = ""
            }
        } else if textField == bottomTextField {
            if clearBottomTextField {
                clearBottomTextField = false
                bottomTextField.text = ""
            }
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    @IBAction func pickImage(_ sender: UIBarButtonItem) {
        let imagePicker = UIImagePickerController()
        imagePicker.sourceType = .photoLibrary
        imagePicker.delegate = self
        present(imagePicker, animated: true, completion: nil)
    }
    
    @IBAction func pickCamera(_ sender: UIBarButtonItem) {
        let imagePicker = UIImagePickerController()
        if UIImagePickerController.isSourceTypeAvailable(.camera) {
            imagePicker.sourceType = .camera
        }
        
        imagePicker.delegate = self
        present(imagePicker, animated: true, completion: nil)
    }
    
    
    
    private func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : AnyObject]) {
        if let img = info["UIImagePickerControllerOriginalImage"] as? UIImage {
            imagePickerView.image = img
            dismiss(animated: true, completion: nil)
        }
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
    
    func subscribeToKeyboardNotifications() {
        NotificationCenter.default.addObserver(self, selector: Selector("keyboardWillShow:"), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: Selector("keyboardWillHide:"), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
    }
    
    func unsubscribeFromKeyboardNotifications() {
        NotificationCenter.default.removeObserver(self, name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.removeObserver(self, name: NSNotification.Name.UIKeyboardWillHide, object: nil)
    }
    
    func keyboardWillShow(notification: NSNotification) {
        if bottomTextField.isFirstResponder {
            self.view.frame.origin.y -= getKeyboardHeight(notification: notification)
            self.view.layoutIfNeeded()
        }
    }
    
    func keyboardWillHide(notification: NSNotification) {
        if bottomTextField.isFirstResponder {
            self.view.frame.origin.y += getKeyboardHeight(notification: notification)
            self.view.layoutIfNeeded()
        }
    }
    
    func getKeyboardHeight(notification: NSNotification) -> CGFloat {
        let userInfo = notification.userInfo
        let keyboardSize = userInfo![UIKeyboardFrameEndUserInfoKey] as! NSValue
        return keyboardSize.cgRectValue.height
    }
    
    func generateMemedImage() -> UIImage {
        toolbar.isHidden = true
        
        UIGraphicsBeginImageContext(self.view.frame.size)
        self.view.drawHierarchy(in: self.view.frame, afterScreenUpdates: true)
        let memedImage: UIImage = UIGraphicsGetImageFromCurrentImageContext()!
        UIGraphicsEndImageContext()
        
        toolbar.isHidden = false
        return memedImage
    }
    
    
    @IBAction func save(_ sender: UIBarButtonItem) {
        if let img = imagePickerView.image {
            let meme = Meme(topText: topTextField.text!, bottomText: bottomTextField.text!, image: img, memedImage: generateMemedImage())
            (UIApplication.shared.delegate as! AppDelegate).memes.append(meme)
            let vc = UIActivityViewController(activityItems: [meme.memedImage], applicationActivities: nil)
            present(vc, animated: true, completion: nil)
        
        }
    }
    
    @IBAction func done(_ sender: UIBarButtonItem) {
        self.dismiss(animated: true, completion: nil)
    }
    
    //@IBAction func done(sender: UIBarButtonItem) {
        //self.dismissViewControllerAnimated(true, completion: nil)
    //}

    

    /*@IBAction func pickAnImage(_ sender: UIBarButtonItem) {
        let pickerController = UIImagePickerController()
        pickerController.delegate = self
        self.present(pickerController, animated: true, completion: nil)
    }
    
    @IBAction func pickAnImageFromCamera(_ sender: Any) {
        
        let imagePicker = UIImagePickerController()
        imagePicker.delegate = self
        present(imagePicker, animated: true, completion: nil)
    }
    
    @IBAction func pickAnImageFromAlbum(_ sender: Any) {
        
        let imagePicker = UIImagePickerController()
        imagePicker.delegate = self
        imagePicker.sourceType = .photoLibrary
        present(imagePicker, animated: true, completion: nil)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String: Any]) {
        if let image = info[/* TODO: Dictionary Key Goes Here */] as? UIImage {
            imagePickerView.image = image
        }
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        
    }*/
}

