//
//  EmotionsViewController.swift
//  Faceit
//
//  Created by Tim Serio on 6/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class EmotionsViewController: UIViewController {

    // MARK: - Navigation

    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        var destinationViewController = segue.destination
        if let navigationController = destinationViewController as? UINavigationController {
            destinationViewController = navigationController.visibleViewController ?? destinationViewController
        }
        if let faceViewController = destinationViewController as? FaceViewController {
            if let identifier =  segue.identifier {
                if let expressin = emotions[identifier] {
                    faceViewController.expression = expressin
                }
                faceViewController.navigationItem.title = (sender as? UIButton)?.currentTitle
            }
        }
        
    }
    
    private let emotions: Dictionary<String,FacialExpression> = [
        "sad" : FacialExpression(eyes: .closed, mouth: .frown),
        "happy" : FacialExpression(eyes: .open, mouth: .smile),
        "worried" : FacialExpression(eyes: .open, mouth: .smirk)
    ]

}
