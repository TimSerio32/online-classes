//
//  RoundedImageView.swift
//  MVCTest
//
//  Created by Tim Serio on 9/5/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class RoundedImageView: UIImageView {

    override func awakeFromNib() {
        self.layer.cornerRadius = 5.0
        self.clipsToBounds = true
    }

}
