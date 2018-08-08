//
//  DetailedMemeViewController.swift
//  PickingImages
//
//  Created by Tim Serio on 8/13/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class DetailedMemeViewController: UIViewController {
    
    @IBOutlet weak var imageView: UIImageView!
        
    var meme: Meme? = nil
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.imageView.image = self.meme?.memedImage
    }
}
