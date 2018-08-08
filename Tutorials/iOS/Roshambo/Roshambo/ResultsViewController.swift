//
//  ResultsViewController.swift
//  Roshambo
//
//  Created by Tim Serio on 8/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ResultsViewController: UIViewController {
    
    @IBOutlet weak var resultsLabel: UILabel!
    
    @IBOutlet weak var imgView: UIImageView!
    
    @IBOutlet weak var userResult: UILabel!
    
    var userValue: Int?
    
    func randomValue() -> Int {
        // generate a random Int32 using arc4Random
        let randomValue = 1 + arc4random() % 3
        // return a more convenient Int, initialized with the random value
        return Int(randomValue)
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        let opponentVal = self.randomValue()

        if userValue == 1 && opponentVal == 1 {
            userResult.text = "You chose rock."
            resultsLabel.text = "You tied!"
            imgView.image = UIImage(named: "rock.png")
        } else if userValue == 1 && opponentVal == 2 {
            userResult.text = "You chose rock."
            resultsLabel.text = "You lost!"
            imgView.image = UIImage(named: "paper.png")
        } else if userValue == 1 && opponentVal == 3 {
            userResult.text = "You chose rock."
            resultsLabel.text = "You won!"
            imgView.image = UIImage(named: "scissors.png")
        } else if userValue == 2 && opponentVal == 1 {
            userResult.text = "You chose paper."
            resultsLabel.text = "You won!"
            imgView.image = UIImage(named: "rock.png")
        } else if userValue == 2 && opponentVal == 2 {
            userResult.text = "You chose paper."
            resultsLabel.text = "You tied!"
            imgView.image = UIImage(named: "paper.png")
        } else if userValue == 2 && opponentVal == 3 {
            userResult.text = "You chose paper."
            resultsLabel.text = "You lost!"
            imgView.image = UIImage(named: "scissors.png")
        } else if userValue == 3 && opponentVal == 1 {
            userResult.text = "You chose scissors."
            resultsLabel.text = "You lost!"
            imgView.image = UIImage(named: "rock.png")
        } else if userValue == 3 && opponentVal == 2 {
            userResult.text = "You chose scissors."
            resultsLabel.text = "You won!"
            imgView.image = UIImage(named: "paper.png")
        } else if userValue == 3 && opponentVal == 3 {
            userResult.text = "You chose scissors."
            resultsLabel.text = "You tied!"
            imgView.image = UIImage(named: "scissors.png")
        }
        
        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
