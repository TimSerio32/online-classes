//
//  ViewController.swift
//  Presentations
//
//  Created by Tim Serio on 8/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func experiment(_ sender: UIButton) {
        /*
        let controller = UIImagePickerController()
        self.present(controller, animated: true, completion: nil)
        */

        
        let image = UIImage()
        
        //let controller = UIActivityViewController(
        
        let controller = UIActivityViewController(activityItems: [image], applicationActivities: nil)
        
        
        self.present(controller, animated: true, completion: nil)
        
        
        /*
        let controller = UIAlertController()
        controller.title = "Test Alert"
        controller.message = "This is a test"
        let cancel = UIAlertAction(title: "Cancel", style: UIAlertActionStyle.default) { action in
            self.dismiss(animated: true, completion: nil)
        }
        controller.addAction(cancel)
        self.present(controller, animated: true, completion: nil) 
         */
    }
    

    
    

}

