//
//  RoundTextField.swift
//  DevChat
//
//  Created by Tim Serio on 9/28/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation

@IBDesignable
class RoundTextField: UITextField {
    
    @IBInspectable var cornerRadius: CGFloat = 0 {
        didSet {
            layer.cornerRadius = cornerRadius
            layer.masksToBounds = cornerRadius > 0
        }
    }
    
    @IBInspectable var borderWidth: CGFloat = 0 {
        didSet {
            layer.borderWidth = borderWidth
        }
    }
    
    @IBInspectable var borderColor: UIColor? {
        didSet {
            layer.borderColor = borderColor?.cgColor
        }
    }
    
    @IBInspectable var bgColor: UIColor? {
        didSet {
            backgroundColor = bgColor
        }
    }
    
    @IBInspectable var placeholderColor: UIColor? {
        didSet {
            let rawStr = attributedPlaceholder?.string != nil ? attributedPlaceholder!.string : ""
            let str = NSAttributedString(string: rawStr, attributes: [NSForegroundColorAttributeName: placeholderColor!])
            attributedPlaceholder = str
        }
    }
}
