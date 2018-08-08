//
//  Item+CoreDataClass.swift
//  DreamLister
//
//  Created by Tim Serio on 9/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
import CoreData

@objc(Item)
public class Item: NSManagedObject {

    
    public override func awakeFromInsert() {
        super.awakeFromInsert()
        self.created = NSDate()
    }
    
}
