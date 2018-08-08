//
//  MusicListViewController.swift
//  SwappingScreens
//
//  Created by Tim Serio on 9/3/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class MusicListViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = UIColor.blue
        
    }
    @IBAction func backButtonPressed(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    
    @IBAction func load3rdScreenPressed(_ sender: UIButton) {
        let songTitle = "Roll In My Sweet Baby's Arms"
        performSegue(withIdentifier: "song", sender: songTitle)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let destination = segue.destination as? PlaySongViewController {
            if let song = sender as? String {
                destination.selectedSong = song
            }
        }
    }
    

}
