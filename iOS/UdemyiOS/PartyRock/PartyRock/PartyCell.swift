//
//  PartyCell.swift
//  PartyRock
//
//  Created by Tim Serio on 9/4/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class PartyCell: UITableViewCell {

    @IBOutlet weak var videoPreviewImage: UIImageView!
    
    @IBOutlet weak var videoTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
    }

    func updateUI(partyRock: PartyRock) {
        videoTitle.text = partyRock.videoTitle
        
        let url = URL(string: partyRock.imageURL)!
        
        //async background thread
        DispatchQueue.global().async {
            do {
                let data = try Data(contentsOf: url)
                //sync main thread (for ui)
                DispatchQueue.global().sync {
                    self.videoPreviewImage.image = UIImage(data: data)
                }
            } catch {
                
            }
        }
    }
    
}
