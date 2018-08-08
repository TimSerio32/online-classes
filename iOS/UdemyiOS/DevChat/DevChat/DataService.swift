//
//  DataService.swift
//  DevChat
//
//  Created by Tim Serio on 9/30/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

let FIR_CHILD_USERS = "users"
let FIR_CHILD_FIRST = "firstName"
let FIR_CHILD_LAST = "lastName"
let FIR_CHILD_PROFILE = "profile"

import Foundation
import FirebaseDatabase
import FirebaseStorage

class DataService {
    private static let _instance = DataService()
    
    static var instance: DataService {
        return _instance
    }
    
    var mainRef: DatabaseReference {
        return Database.database().reference()
    }
    
    var usersRef: DatabaseReference {
        return mainRef.child(FIR_CHILD_USERS)
    }
    
    var mainStorageRef: StorageReference {
        return Storage.storage().reference(forURL: "gs://devchat-309f0.appspot.com")
    }
    
    var imagesStorageRef: StorageReference {
        return mainStorageRef.child("images")
    }
    
    var videoStorageRef: StorageReference {
        return mainStorageRef.child("videos")
    }
    
    func saveUser(uid: String) {
        let profile: [String: Any] = [ FIR_CHILD_FIRST: "" as Any, FIR_CHILD_LAST: "" as Any ]
        mainRef.child(FIR_CHILD_USERS).child(uid).child(FIR_CHILD_PROFILE).setValue(profile)
    }
    
    func sendMediaPullRequest(senderUID: String, sendingTo: [String: User], mediaURL: URL, textSnippet: String? = nil) {
        
        var uids = [String]()
        for uid in sendingTo.keys {
            uids.append(uid)
        }
        
        let pr: [String: Any] = ["mediaURL": mediaURL.absoluteString as Any, "userID": senderUID as Any, "openCount": 0 as Any, "recipients": uids as Any]
        
        mainRef.child("pullRequests").childByAutoId().setValue(pr)
    }
}
