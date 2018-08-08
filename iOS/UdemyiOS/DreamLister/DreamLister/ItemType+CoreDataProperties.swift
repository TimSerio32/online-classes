//
//  ItemType+CoreDataProperties.swift
//  DreamLister
//
//  Created by Tim Serio on 9/10/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
import CoreData


extension ItemType {

    @nonobjc public class func fetchRequest() -> NSFetchRequest<ItemType> {
        return NSFetchRequest<ItemType>(entityName: "ItemType")
    }

    @NSManaged public var type: String?
    @NSManaged public var toItem: Item?

}
