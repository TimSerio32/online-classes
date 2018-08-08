//
//  ViewController.swift
//  MiraclePills
//
//  Created by Tim Serio on 9/1/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate, UITextFieldDelegate {

    @IBOutlet weak var nameTextField: UITextField!
    @IBOutlet weak var addressTextField: UITextField!
    @IBOutlet weak var cityTextField: UITextField!
    @IBOutlet weak var stateTextField: UITextField!
    @IBOutlet weak var statePicker: UIPickerView!
    @IBOutlet weak var zipCodeLabel: UILabel!
    @IBOutlet weak var zipCodeTextField: UITextField!
    @IBOutlet weak var buyNowOutlet: UIButton!
    //var button: UIButton = UIButton()
    
    let states = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Conneticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"]
    
    let button: UIButton = UIButton(type: UIButtonType.custom)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        statePicker.dataSource = self
        statePicker.delegate = self
        stateTextField.delegate = self
        nameTextField.delegate = self
        addressTextField.delegate = self
        cityTextField.delegate = self
        zipCodeTextField.delegate = self
        
        zipCodeTextField.addDoneButtonToKeyboard(myAction:  #selector(self.zipCodeTextField.resignFirstResponder))
        /*
        button.setTitle("Return", for: UIControlState.normal)
        button.setTitleColor(UIColor.black, for: UIControlState.normal)
        button.frame = CGRect(x: 0, y: 163, width: 106, height: 53)
        button.adjustsImageWhenHighlighted = false
        button.addTarget(self, action: #selector(ViewController.done), for: UIControlEvents.touchUpInside)
        //self.addDoneButtonOnKeyboard()*/
        /*
        button.setTitle("Return", for: UIControlState.normal)
        button.setTitleColor(UIColor.black, for: UIControlState.normal)
        button.frame = CGRect(x: 0, y: 163, width: 106, height: 53)
        button.adjustsImageWhenHighlighted = false
        button.addTarget(self, action: #selector(ViewController.Done(_:)), for: UIControlEvents.touchUpInside)*/
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    /*
    func done() {
        self.zipCodeTextField.resignFirstResponder()
    }*/
    /*
    func animateViewMoving (up:Bool, moveValue :CGFloat){
        let movementDuration:TimeInterval = 0.3
        let movement:CGFloat = ( up ? -moveValue : moveValue)
        
        UIView.beginAnimations("animateView", context: nil)
        UIView.setAnimationBeginsFromCurrentState(true)
        UIView.setAnimationDuration(movementDuration)
        
        self.view.frame = self.view.frame.offsetBy(dx: 0, dy: movement)
        UIView.commitAnimations()
    }

    func addDoneButtonOnKeyboard() {
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect(x: 0, y: 0, width: 320, height: 50))
        doneToolbar.barStyle = UIBarStyle.default
        
        let flexSpace = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Return", style: UIBarButtonItemStyle.done, target: self, action: #selector(ViewController.doneButtonAction))
        
        var items = [UIBarButtonItem]()
        items.append(flexSpace)
        items.append(done)
        
        doneToolbar.items = items
        doneToolbar.sizeToFit()
        
        self.zipCodeTextField.inputAccessoryView = doneToolbar
        
    }
    
    func doneButtonAction() {
        self.zipCodeTextField.resignFirstResponder()
    }*/
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        if textField == stateTextField {
            statePicker.isHidden = false
            zipCodeLabel.isHidden = true
            zipCodeTextField.isHidden = true
            buyNowOutlet.isHidden = true
            return false
        } else if textField == zipCodeTextField {
            statePicker.isHidden = true
            zipCodeLabel.isHidden = false
            zipCodeTextField.isHidden = false
            buyNowOutlet.isHidden = false
            return true
        } else {
            return true
        }/*else if textField == zipCodeTextField {
            NotificationCenter.default.addObserver(self, selector: #selector(ViewController.keyboardWillShow(_:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
            return true
        }*/
    }
    
    /*
    func textFieldDidBeginEditing(_ textField: UITextField) {
        if textField == cityTextField || textField == zipCodeTextField {
            animateViewMoving(up: true, moveValue: 100)
        }
    }*/
    
    /*
    func textFieldDidEndEditing(textField: UITextField) {
        if textField == email || textField == password {
            animateViewMoving(false, moveValue: 100)
        }
    }*/
    
    @IBAction func stateTextFieldPressed(_ sender: UITextField) {
        statePicker.isHidden = false
        zipCodeLabel.isHidden = true
        zipCodeTextField.isHidden = true
        buyNowOutlet.isHidden = true
    }
    
    @IBAction func zipCodeTextFieldPressed(_ sender: UITextField) {
        statePicker.isHidden = true
        zipCodeLabel.isHidden = false
        zipCodeTextField.isHidden = false
        buyNowOutlet.isHidden = false
    }
    
    /*
    func textFieldDidBeginEditing(_ textField: UITextField) {
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.keyboardWillShow(_:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
    }
    
    
    func textFieldDidBeginEditing(_ textField: UITextField) {
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.textFieldShouldBeginEditing(_:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
    }
    
    func textFieldShouldBeginEditing(state: UITextField) -> Bool {
        if state == zipCodeTextField {
            DispatchQueue.main.async { () -> Void in
                
                self.button.isHidden = false
                let keyBoardWindow = UIApplication.shared.windows.last
                self.button.frame = CGRect(x: 0, y: (keyBoardWindow?.frame.size.height)!-53, width: 106, height: 53)
                keyBoardWindow?.addSubview(self.button)
                keyBoardWindow?.bringSubview(toFront: self.button)
                UIView.animate(withDuration: (((state.userInfo! as NSDictionary).object(forKey: UIKeyboardAnimationCurveUserInfoKey) as AnyObject).doubleValue)!, delay: 0, options: UIViewAnimationOptions.curveEaseIn, animations: { () -> Void in
                    self.view.frame = self.view.frame.offsetBy(dx: 0, dy: 0)
                }, completion: { (complete) -> Void in
                    print("Complete")
                })
            }
            return true
        }
        return true
    }
    
    func keyboardWillShow(_ note : Notification) -> Void{
        DispatchQueue.main.async { () -> Void in
            
            self.button.isHidden = false
            let keyBoardWindow = UIApplication.shared.windows.last
            self.button.frame = CGRect(x: 0, y: (keyBoardWindow?.frame.size.height)!-53, width: 106, height: 53)
            keyBoardWindow?.addSubview(self.button)
            keyBoardWindow?.bringSubview(toFront: self.button)
            UIView.animate(withDuration: (((note.userInfo! as NSDictionary).object(forKey: UIKeyboardAnimationCurveUserInfoKey) as AnyObject).doubleValue)!, delay: 0, options: UIViewAnimationOptions.curveEaseIn, animations: { () -> Void in
                self.view.frame = self.view.frame.offsetBy(dx: 0, dy: 0)
            }, completion: { (complete) -> Void in
                print("Complete")
            })
        }
    }*/
    


    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return states.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return states[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        stateTextField.text = states[row]
        //statePickerBtn.setTitle(states[row], for: UIControlState.normal)
        statePicker.isHidden = true
        zipCodeLabel.isHidden = false
        zipCodeTextField.isHidden = false
        buyNowOutlet.isHidden = false
        
    }
    
    override func shouldPerformSegue(withIdentifier identifier: String?, sender: Any?) -> Bool {
        if let ident = identifier {
            if ident == "segue" {
                if nameTextField.text! == "" || addressTextField.text! == "" || cityTextField.text! == "" || stateTextField.text! == "" || zipCodeTextField.text! == "" {
                    return false
                }
            }
        }
        return true
    }
}

extension UITextField{
    
    func addDoneButtonToKeyboard(myAction:Selector?){
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect(x: 0, y: 0, width: 300, height: 40))
        doneToolbar.barStyle = UIBarStyle.default
        
        let flexSpace = UIBarButtonItem(barButtonSystemItem: UIBarButtonSystemItem.flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Done", style: UIBarButtonItemStyle.done, target: self, action: myAction)
        
        var items = [UIBarButtonItem]()
        items.append(flexSpace)
        items.append(done)
        
        doneToolbar.items = items
        doneToolbar.sizeToFit()
        
        self.inputAccessoryView = doneToolbar
    }
}
