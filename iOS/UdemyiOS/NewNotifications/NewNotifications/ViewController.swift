//
//  ViewController.swift
//  NewNotifications
//
//  Created by Tim Serio on 9/17/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import UserNotifications

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        UNUserNotificationCenter.current().requestAuthorization(options: [.alert, .badge, .sound], completionHandler: { (granted, error) in
            if granted {
                print("Notification access granted")
            } else {
                print(error?.localizedDescription ?? "Default")
            }
        })
    }

    @IBAction func notifyBtnTapped(_ sender: UIButton) {
        scheduleNotification(inSeconds: 5) { (success) in
            if success {
                print("Successfully scheduled notification")
            } else {
                print("Error scheduling notification")
            }
        }
    }
    
    func scheduleNotification(inSeconds: TimeInterval, completion: @escaping (_ Success: Bool) -> ()) {
        let myImage = "cassius"
        guard let imageUrl = Bundle.main.url(forResource: myImage, withExtension: "gif") else {
            completion(false)
            return
        }
        
        var attachment: UNNotificationAttachment
        
        attachment = try! UNNotificationAttachment(identifier: "myNotification", url: imageUrl, options: .none)
        
        let notif = UNMutableNotificationContent()
        
        notif.categoryIdentifier = "myNotificationCategory"
        
        notif.title = "NewNotification"
        notif.subtitle = "Subtitle"
        notif.body = "Body of Notification"
        
        notif.attachments = [attachment ]
        
        let notificationTrigger = UNTimeIntervalNotificationTrigger(timeInterval: inSeconds, repeats: false)
        let request = UNNotificationRequest(identifier: "myNotification", content: notif, trigger: notificationTrigger)
        UNUserNotificationCenter.current().add(request) { (error) in
            if error != nil {
                print(error ?? "Default")
                completion(false)
            } else {
                completion(true)
            }
        }
    }

}

