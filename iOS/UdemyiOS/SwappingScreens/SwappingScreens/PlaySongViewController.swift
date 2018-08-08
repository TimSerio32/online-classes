//
//  PlaySongViewController.swift
//  SwappingScreens
//
//  Created by Tim Serio on 9/3/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class PlaySongViewController: UIViewController {

    @IBOutlet weak var songTitleLabel: UILabel!
    
    private var _selectedSong: String!
    
    var selectedSong: String {
        get {
            return _selectedSong
        } set {
            _selectedSong = newValue
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        songTitleLabel.text = _selectedSong!
    }

}
