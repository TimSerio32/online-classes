//
//  Location.swift
//  RainyShine
//
//  Created by Tim Serio on 9/7/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import CoreLocation

class Location {
    static var sharedInstance = Location()
    private init() {}
    
    var latitude: Double!
    var longitude: Double!
    
}
