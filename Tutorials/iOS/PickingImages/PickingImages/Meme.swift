//
//  Meme.swift
//  PickingImages
//
//  Created by Tim Serio on 8/13/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
import UIKit

class Meme {
    var topText: String
    var bottomText: String
    var image: UIImage
    var memedImage: UIImage
    
    init(topText: String, bottomText: String, image: UIImage, memedImage: UIImage) {
        self.topText = topText
        self.bottomText = bottomText
        self.image = image
        self.memedImage = memedImage
    }
}
