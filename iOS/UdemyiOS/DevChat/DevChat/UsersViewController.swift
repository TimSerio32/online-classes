//
//  UsersViewController.swift
//  DevChat
//
//  Created by Tim Serio on 9/30/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import FirebaseDatabase
import FirebaseStorage
import FirebaseAuth

class UsersViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet weak var tableView: UITableView!
    
    private var users = [User]()
    private var selectedUsers = [String: User]()
    
    private var _snapData: Data?
    private var _videoURL: URL?
    
    var snapData: Data? {
        set {
            _snapData = newValue
        } get {
            return _snapData
        }
    }
    
    var videoURL: URL? {
        set {
            _videoURL = newValue
        } get {
            return _videoURL
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.delegate = self
        tableView.dataSource = self
        tableView.allowsMultipleSelection = true
        
        navigationItem.rightBarButtonItem?.isEnabled = false
        
        // move parsing code to model class file
        DataService.instance.usersRef.observeSingleEvent(of: .value) { (snapshot: DataSnapshot) in
            if let users = snapshot.value as? [String: Any] {
                for (key, value) in users {
                    if let dict = value as? [String: Any] {
                        if let profile = dict["profile"] as? [String: Any] {
                            if let firstName = profile["firstName"] as? String {
                                let uid = key
                                let user = User(uid: uid, userName: firstName)
                                self.users.append(user)
                            }
                        }
                    }
                }
            }
            
            self.tableView.reloadData()
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        // dry principle
        navigationItem.rightBarButtonItem?.isEnabled = true
        let cell = tableView.cellForRow(at: indexPath) as! UserTableViewCell
        cell.setCheckMark(selected: true)
        let user = users[indexPath.row]
        selectedUsers[user.uid] = user
    }
    
    func tableView(_ tableView: UITableView, didDeselectRowAt indexPath: IndexPath) {
        // dry principle
        let cell = tableView.cellForRow(at: indexPath) as! UserTableViewCell
        cell.setCheckMark(selected: false)
        let user = users[indexPath.row]
        selectedUsers[user.uid] = nil
        
        if selectedUsers.count <= 0 {
            navigationItem.rightBarButtonItem?.isEnabled = false
        }
    }

    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return users.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "UserCell") as! UserTableViewCell
        let user = users[indexPath.row]
        cell.updateUI(user: user)
        return cell
    }
    
    // Add a spinner to show loading of sending images
    // Add an alert to display when it's done loading
    @IBAction func sendPRBtnPressed(sender: Any) {
        if let url = _videoURL {
            let videoName = "\(NSUUID().uuidString)\(url)"
            let ref = DataService.instance.videoStorageRef.child(videoName)
            
            _ = ref.putFile(from: url, metadata: nil, completion: { (meta: StorageMetadata?, err: Error?) in
                
                if err != nil {
                    print("Error uploading video: \(String(describing: err?.localizedDescription))")
                } else {
                    let downloadURL = meta!.downloadURL()
                    DataService.instance.sendMediaPullRequest(senderUID: (Auth.auth().currentUser?.uid)!, sendingTo: self.selectedUsers, mediaURL: downloadURL!, textSnippet: "Coding today was LEGIT!")
                }
                
            })
            self.dismiss(animated: true, completion: nil)
        } else if let snap = _snapData {
            let ref = DataService.instance.imagesStorageRef.child("\(NSUUID().uuidString).jpg")
            
            _ = ref.putData(snap, metadata: nil, completion: { (meta: StorageMetadata?, err: Error?) in
                if err != nil {
                    
                    print("Error uploading snapshot: \(String(describing: err?.localizedDescription))")
                } else {
                    // let downloadURL = meta!.downloadURL()
                    _ = meta!.downloadURL()
                }
            })
            self.dismiss(animated: true, completion: nil)
        }
    }

}
