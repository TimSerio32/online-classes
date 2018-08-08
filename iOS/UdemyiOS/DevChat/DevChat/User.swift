//
//  User.swift
//  DevChat
//
//  Created by Tim Serio on 9/30/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

struct User {
    private var _userName: String
    private var _uid: String
    
    var uid: String {
        return _uid
    }
    
    var userName: String {
        return _userName
    }
    
    init(uid: String, userName: String) {
        _uid = uid
        _userName = userName
    }
}
