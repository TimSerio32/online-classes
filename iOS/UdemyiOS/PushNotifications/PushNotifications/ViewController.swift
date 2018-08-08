//
//  ViewController.swift
//  PushNotifications
//
//  Created by Tim Serio on 9/9/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import Firebase
import FirebaseInstanceID
import FirebaseMessaging

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        
        FIRMessaging.messaging().subscribe(toTopic: "/topics/news")
    }


}

