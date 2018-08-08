//
//  ViewController.swift
//  Faceit
//
//  Created by Tim Serio on 6/20/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class FaceViewController: UIViewController {

    @IBOutlet weak var faceView: FaceView! {
        //this executes only when iOS hooks up this outlet to the faceView
        didSet {
            let handler = #selector(FaceView.changeScale(byReactingTo:))
            let pinchRecognizer = UIPinchGestureRecognizer(target: faceView, action: handler)
            faceView.addGestureRecognizer(pinchRecognizer)
            let tapRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.toggleEyes(byReacingTo:)))
            tapRecognizer.numberOfTapsRequired = 1
            faceView.addGestureRecognizer(tapRecognizer)
            let swipeUpRecognizer = UISwipeGestureRecognizer(target: self, action: #selector(increaseHappiness))
            swipeUpRecognizer.direction = .up
            faceView.addGestureRecognizer(swipeUpRecognizer)
            let swipeDownRecognizer = UISwipeGestureRecognizer(target: self, action: #selector(decreaseHappiness))
            swipeDownRecognizer.direction = .down
            faceView.addGestureRecognizer(swipeDownRecognizer)
            updateUI()
        }
    }
    
    func toggleEyes(byReacingTo tapRecognizer: UITapGestureRecognizer) {
        if tapRecognizer.state == .ended {
            let eyes: FacialExpression.Eyes = expression.eyes == .closed ? .open : .closed
            expression = FacialExpression(eyes: eyes, mouth: expression.mouth)
        }
    }
    
    //no arguments because swipe gesture is discrete; we don't need to look at state
    func increaseHappiness() {
        expression = expression.happier
    }
    
    func decreaseHappiness() {
        expression = expression.sadder
    }
    
    var expression = FacialExpression(eyes:.open, mouth: .grin) {
        didSet {
            updateUI()
        }
    }
    
    private func updateUI() {
        switch expression.eyes {
            case .open:
                //optional chaining to prevent a crash in the program if expression is called before faceView
                faceView?.eyesOpen = true
            case .closed:
                faceView?.eyesOpen = false
            case .squinting:
                faceView?.eyesOpen = false
        }
        faceView?.mouthCurve = mouthCurves[expression.mouth] ?? 0.0
    }
    
    private let mouthCurves = [FacialExpression.Mouth.grin:0.5, .frown:-1.0, .smile:1.0, .neutral:0.0, .smirk:-0.5]

}

