//
//  ViewController.swift
//  Roshambo
//
//  Created by Tim Serio on 8/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet weak var rockImage: UIButton!
    @IBOutlet weak var paperImage: UIButton!
    @IBOutlet weak var scissorsImage: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    
    let resultsController = UIStoryboard(name:"Main", bundle:nil).instantiateViewController(withIdentifier: "resultsController") as! ResultsViewController
    
    @IBAction func rockAction(_ sender: UIButton) {
        resultsController.userValue = 1
        present(resultsController, animated: true, completion: nil)
    }
    
    @IBAction func paperAction(_ sender: UIButton) {
        
    }
    
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "paperSegue" {
            let controller = segue.destination as! ResultsViewController
            controller.userValue = 2
            
        } else if segue.identifier == "scissorsSegue" {
            let controller = segue.destination as! ResultsViewController
            controller.userValue = 3
            
        }
    }
    
    

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    

}

