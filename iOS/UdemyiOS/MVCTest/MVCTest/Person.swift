//
//  Person.swift
//  MVCTest
//
//  Created by Tim Serio on 9/5/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation

class Person {
    private var _firstName: String!
    private var _lastName: String!
    
    //computed property
    var firstName: String {
        get {
            return _firstName
        } set {
            _firstName = newValue
        }
        
    }
    
    var lastName: String! {
        return _lastName
    }
    
    init(first: String, last: String) {
        self._firstName = first
        self._lastName = last
    }
    
    var fullName: String! {
        return "\(_firstName) \(_lastName)"
    }
}
