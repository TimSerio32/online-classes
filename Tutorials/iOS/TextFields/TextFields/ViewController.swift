//
//  ViewController.swift
//  TextFields
//
//  Created by Tim Serio on 8/12/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {
    @IBOutlet weak var zipCode: UITextField!
    @IBOutlet weak var cashValue: UITextField!
    @IBOutlet weak var switchText: UITextField!
    @IBOutlet weak var swtch: UISwitch!
    
    
    //let zipCodeDelegate = ZipCodeTextFieldDelegate()
    //let cashDelegate = CashTextFieldDelegate()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        //self.zipCode.delegate = self.zipCodeDelegate
        //self.cashValue.delegate = self.cashDelegate
        self.switchText.delegate = self
        
        self.swtch.setOn(false, animated: false)
        
    }
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        return self.swtch.isOn
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        textField.resignFirstResponder()
        return true
    }
    @IBAction func switchAction(_ sender: UISwitch) {
        if !(sender.isOn) {
            self.switchText.resignFirstResponder()
        }
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    


}

