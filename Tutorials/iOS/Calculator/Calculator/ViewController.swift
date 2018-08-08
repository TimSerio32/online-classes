//
//  ViewController.swift
//  Calculator
//
//  Created by Tim Serio on 6/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController {
    
    @IBOutlet var display: UILabel!
    @IBOutlet var displayTwo: UILabel!
    
    // Add ✅ button to storyboard
    /*override func viewDidLoad() {
        super.viewDidLoad()
        model.addUnaryOperation(named: "✅") { [weak weakSelf = self] in
            weakSelf?.display.textColor = UIColor.green
            return sqrt($0)
        }
    }*/
    
    private var isTyping = false {
        didSet {
            initialNullValueAsOperand = false
        }
    }
    
    private var initialNullValueAsOperand = true
    
    var displayValue: Double {
        get {
            return Double(display.text!)!
        }
        set {
            display.text = String(describing: newValue)
        }
    }

    @IBAction func touchDigit(_ sender: UIButton) {
        let digit = sender.currentTitle!
        if(isTyping) {
            let textCurrentlyInDisplay = display.text!
            if(textCurrentlyInDisplay == "0.0"){
                display.text = digit
            }else{
                display.text = textCurrentlyInDisplay + digit
            }
        }else {
            display.text = digit
            isTyping = true
        }
    }
    
    private var model: CalculatorModel = CalculatorModel()
    
    @IBAction func performOperation(_ sender: UIButton) {
        if isTyping || initialNullValueAsOperand {
            model.setOperand(displayValue)
            isTyping = false
        }
        
        let mathSymbol = sender.currentTitle
        model.performOperation(mathSymbol!)
        displayValue = model.result
        let postfixText = model.partialResult ? "..." : "="
        displayTwo.text = model.description + postfixText
    }

    @IBAction func clear(_ sender: UIButton) {
        model.clear()
        displayValue = model.result
        displayTwo.text = String(0)
        initialNullValueAsOperand = true
    }
    
    @IBAction func backspace(_ sender: UIButton) {
        guard isTyping else { return }
        if (display.text?.characters.count)! < 2{
            displayValue = 0.0
            isTyping = false
            return
        }
        
        display.text = String(display.text!.characters.dropLast())
    }
    
    
    @IBAction func makeFloatingPt(_ sender: UIButton) {
        if(!isTyping) {
            display.text = "0."
        }else if(display.text?.range(of: ".") == nil) {
            display.text = display.text! + "."
        }
        isTyping = true
    }
    

}

