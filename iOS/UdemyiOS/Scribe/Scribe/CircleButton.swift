//
//  CircleButton.swift
//  Scribe
//
//  Created by Tim Serio on 9/14/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

@IBDesignable
class CircleButton: UIButton {

    @IBInspectable var cornerRadius: CGFloat = 30.0 {
        didSet {
           setupView()
        }
    }
    
    override func prepareForInterfaceBuilder() {
        setupView()
    }
    
    func setupView() {
        layer.cornerRadius = cornerRadius
    }
}
