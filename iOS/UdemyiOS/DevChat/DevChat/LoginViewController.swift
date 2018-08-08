//
//  LoginViewController.swift
//  DevChat
//
//  Created by Tim Serio on 9/29/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class LoginViewController: UIViewController {

    @IBOutlet weak var emailField: RoundTextField!
    
    @IBOutlet weak var passwordField: RoundTextField!
    override func viewDidLoad() {
        super.viewDidLoad()

        
    }
    
    @IBAction func loginPressed(_ sender: RoundedButton) {
        if let email = emailField.text, let pass = passwordField.text, (email.characters.count > 0 && pass.characters.count > 0) {
            AuthService.instance.login(email: email, password: pass, onComplete: { (errMsg, data) in
                guard errMsg == nil else {
                    let alert = UIAlertController(title: "Error Authenticating", message: errMsg, preferredStyle: .alert)
                    alert.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
                    self.present(alert, animated: true, completion: nil)
                    return
                }
                
                self.dismiss(animated: true, completion: nil)
            })
        } else {
            let alert = UIAlertController(title: "Username and Password Required", message: "You must enter an email and a password", preferredStyle: .alert)
            alert.addAction(UIAlertAction(title: "OK", style: .cancel, handler: nil))
            present(alert, animated: true, completion: nil)
        }
    }

}
