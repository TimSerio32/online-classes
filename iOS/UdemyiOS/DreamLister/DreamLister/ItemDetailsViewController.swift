//
//  ItemDetailsViewController.swift
//  DreamLister
//
//  Created by Tim Serio on 9/11/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import CoreData

class ItemDetailsViewController: UIViewController, UIPickerViewDataSource, UIPickerViewDelegate, UIImagePickerControllerDelegate, UINavigationControllerDelegate, UITextFieldDelegate {

    @IBOutlet weak var storePicker: UIPickerView!
    @IBOutlet weak var titleField: CustomTextField!
    @IBOutlet weak var priceField: CustomTextField!
    @IBOutlet weak var detailsField: CustomTextField!
    @IBOutlet weak var imagePick: UIImageView!
    @IBOutlet weak var typeField: CustomTextField!
    
    
    var stores = [Store]()
    var itemToEdit: Item?
    var imagePicker: UIImagePickerController!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if let topItem = self.navigationController?.navigationBar.topItem {
            topItem.backBarButtonItem = UIBarButtonItem(title: "", style: UIBarButtonItemStyle.plain, target: nil, action: nil)
        }
        titleField.delegate = self
        priceField.delegate = self
        detailsField.delegate = self
        typeField.delegate = self
        storePicker.delegate = self
        storePicker.dataSource = self
        
        imagePicker = UIImagePickerController()
        imagePicker.delegate = self
        
        /*
        let store = Store(context: context)
        store.name = "Best Buy"
        let store1 = Store(context: context)
        store1.name = "Frys Electronics"
        let store2 = Store(context: context)
        store2.name = "Target"
        let store3 = Store(context: context)
        store3.name = "Amazon"
        let store4 = Store(context: context)
        store4.name = "K Mart"
        let store5 = Store(context: context)
        store5.name = "Tesla"
        
        ad.saveContext()*/
        getStores()
        
        if itemToEdit != nil {
            loadItemData()
        }
        
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        self.view.endEditing(true)
        return true
    }

    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        let store = stores[row]
        return store.name
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return stores.count
    }

    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        
    }
    
    func getStores() {
        let fetchRequest:NSFetchRequest<Store> = Store.fetchRequest()
        
        do {
            self.stores = try context.fetch(fetchRequest)
            self.storePicker.reloadAllComponents()
        } catch {
            
        }
    }
    
    @IBAction func savePressed(_ sender: UIButton) {
        
        var item: Item!
        let picture = Image(context: context)
        picture.image = imagePick.image
        
        
        
        if itemToEdit == nil {
            item = Item(context: context)
        } else {
            item = itemToEdit
        }
        
        item.toImage = picture
        
        if let title = titleField.text {
            item.title = title
        }
        
        if let price = priceField.text {
            item.price = (price as NSString).doubleValue
        }
        
        if let details = detailsField.text {
            item.details = details
        }
        
        if let type = typeField.text {
            var itemType: ItemType!
            itemType = ItemType(context: context)
            itemType.type = type
            item.toItemType = itemType
        }
        
        item.toStore = stores[storePicker.selectedRow(inComponent: 0)]
        
        ad.saveContext()
        
        navigationController?.popViewController(animated: true)
    }
    
    func loadItemData() {
        if let item = itemToEdit {
            titleField.text = item.title
            priceField.text = "\(item.price)"
            detailsField.text = item.details
            typeField.text = item.toItemType?.type
            
            
            imagePick.image = item.toImage?.image as? UIImage
            
            if let store = item.toStore {
                var index = 0
                repeat {
                    let s = stores[index]
                    if s.name == store.name {
                        storePicker.selectRow(index, inComponent: 0, animated: false)
                        break
                    }
                    
                    index += 1
                } while(index < stores.count)
            }
        }
    }
    
    @IBAction func deletePressed(_ sender: UIBarButtonItem) {
        
        if itemToEdit != nil {
            context.delete(itemToEdit!)
            ad.saveContext()
        }
        
        navigationController?.popViewController(animated: true)
    }
    
    @IBAction func addImage(_ sender: UIButton) {
        present(imagePicker, animated: true, completion: nil)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String: Any]) {
        if let img = info[UIImagePickerControllerOriginalImage] as? UIImage {
            imagePick.image = img
        }
        
        imagePicker.dismiss(animated: true, completion: nil)
    }
    
}
