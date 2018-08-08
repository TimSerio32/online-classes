//
//  CameraViewController.swift
//  DevChat
//
//  Created by Tim Serio on 9/27/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import FirebaseAuth

class CameraViewController: AAPLCameraViewController, AAPLCameraVCDelegate {
    


    @IBOutlet weak var cameraBtn: UIButton!
    @IBOutlet weak var recordBtn: UIButton!
    @IBOutlet weak var previewView: AAPLPreviewView!
    override func viewDidLoad() {
        delegate = self
        self._previewView = previewView
        
        super.viewDidLoad()
    }
    
    override func viewDidAppear(_ animated: Bool) {
        
        // ?: guard FIRAuth.auth()?.currentUser != nil else {
        guard Auth.auth().currentUser != nil else {
            performSegue(withIdentifier: "LoginVC", sender: nil)
            return
        }
    }
    
    @IBAction func recordBtnTapped(_ sender: UIButton) {
        toggleMovieRecording()
    }

    @IBAction func changeCameraBtnTapped(_ sender: UIButton) {
        changeCamera()
    }
    
    
    func shouldEnableCameraUI(_ enable: Bool) {
        cameraBtn.isEnabled = enable
    }

    func shouldEnableRecordUI(_ enable: Bool) {
        recordBtn.isEnabled = enable
    }
    
    func recordingHasStarted() {
        
    }
    
    func canStartRecording() {
        
    }
    
    func videoRecordingComplete(_ videoURL: URL!) {
        performSegue(withIdentifier: "UsersVC", sender: ["videoURL": videoURL])
    }
    
    func videoRecordingFailed() {
        
    }
    
    func snapshotTaken(_ snapshotData: Data!) {
        performSegue(withIdentifier: "UsersVC", sender: ["snapshotData": snapshotData])
    }
    
    func snapshotFailed() {
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let usersVC = segue.destination as? UsersViewController {
            if let videoDict = sender as? [String:URL] {
                let url = videoDict["videoURL"]
                usersVC.videoURL = url
            } else if let snapDict = sender as? [String: Data] {
                let snapData = snapDict["snapshotData"]
                usersVC.snapData = snapData
            }
        }
    }
}

