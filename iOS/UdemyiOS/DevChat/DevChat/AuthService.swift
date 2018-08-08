//
//  AuthService.swift
//  DevChat
//
//  Created by Tim Serio on 9/30/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
import FirebaseAuth

typealias Completion = (_ errMsg: String?, _ data: AnyObject?) -> Void

class AuthService {
    private static let _instance = AuthService()
    
    static var instance: AuthService {
        return _instance
    }
    
    func login(email: String, password: String, onComplete: Completion?) {
        Auth.auth().signIn(withEmail: email, password: password) { (user, error) in
            if error != nil {
                if let errorCode = AuthErrorCode(rawValue: error!._code) {
                    if errorCode == .errorCodeUserNotFound {
                        Auth.auth().createUser(withEmail: email, password: password, completion: Completion { (user, error) in
                            if error != nil {
                                self.handelFirebaseError(error: error! as? NSError, onComplete: onComplete)
                            } else {
                                if user?.uid != nil {
                                    DataService.instance.saveUser(uid: user!.uid)
                                    Auth.auth().signIn(withEmail: email, password: password, completion: { (user, error) in
                                        if error != nil {
                                            self.handelFirebaseError(error: error! as? NSError, onComplete: onComplete)
                                        } else {
                                            onComplete?(nil, user)
                                        }
                                        
                                    })
                                }
                            }
                        })
                    }
                } else {
                   self.handelFirebaseError(error: error! as NSError, onComplete: onComplete)
                }
            } else {
                onComplete?(nil, user)
            }
        }
    }
    
    func handelFirebaseError(error: NSError, onComplete: Completion?) {
        if let errorCode = AuthErrorCode(rawValue: error.code) {
            switch (errorCode) {
            case .errorCodeInvalidEmail:
                onComplete?("Invalid email address", nil)
                break
            case .errorCodeWrongPassword:
                onComplete?("Invalid password", nil)
                break
            case .errorCodeEmailAlreadyInUse, .errorCodeAccountExistsWithDifferentCredential:
                onComplete?("Could not create account. Email already in use.", nil)
                break
            default:
                onComplete?("There was a problem authenticating. Try again.", nil)
                break
            }
        }
    }
    
}
