//
//  UserTableViewCell.swift
//  DevChat
//
//  Created by Tim Serio on 9/30/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class UserTableViewCell: UITableViewCell {

    @IBOutlet weak var userNameLbl: UILabel!
    override func awakeFromNib() {
        super.awakeFromNib()
        setCheckMark(selected: false)
    }
    
    func updateUI(user: User) {
        userNameLbl.text = user.userName
    }

    func setCheckMark(selected: Bool) {
        let imageStr = selected ? "messageindicatorchecked1" : "messageindicator1"
        self.accessoryView = UIImageView(image: UIImage(named: imageStr))
    }

}
