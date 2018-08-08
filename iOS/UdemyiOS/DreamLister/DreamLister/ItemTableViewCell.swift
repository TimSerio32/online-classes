//
//  ItemTableViewCell.swift
//  DreamLister
//
//  Created by Tim Serio on 9/11/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ItemTableViewCell: UITableViewCell {

    @IBOutlet weak var imgView: UIImageView!
    @IBOutlet weak var title: UILabel!
    @IBOutlet weak var price: UILabel!
    @IBOutlet weak var details: UILabel!
    @IBOutlet weak var type: UILabel!
    
    
    func configureCell(item: Item) {
        title.text = item.title
        price.text = "$\(item.price)"
        details.text = item.details
        type.text = item.toItemType?.type
        imgView.image = item.toImage?.image as? UIImage
    }

}
