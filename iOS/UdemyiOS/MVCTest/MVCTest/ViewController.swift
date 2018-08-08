//
//  ViewController.swift
//  MVCTest
//
//  Created by Tim Serio on 9/5/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var fullNameLbl: UILabel!
    @IBOutlet weak var renameField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let person = Person(first: "John", last: "Hancock")
        
        // Manipulated data to show in the view
        // Model layer logic in view controller
        fullNameLbl.text = person.fullName
    }


    @IBAction func renamePressed(_ sender: UIButton) {
        if let txt = renameField.text {
            person.firstName = txt
            fullNameLbl.text = person.fullName
        }
    }
}

