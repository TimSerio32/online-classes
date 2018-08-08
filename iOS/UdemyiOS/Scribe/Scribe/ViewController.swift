//
//  ViewController.swift
//  Scribe
//
//  Created by Tim Serio on 9/14/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import Speech
import AVFoundation

class ViewController: UIViewController, AVAudioPlayerDelegate {

    @IBOutlet weak var activitySpinner: UIActivityIndicatorView!
    @IBOutlet weak var transcriptionTextView: UITextView!
    
    var audioPlayer: AVAudioPlayer!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        activitySpinner.isHidden = true
    }

    func audioPlayerDidFinishPlaying(_ player: AVAudioPlayer, successfully flag: Bool) {
        player.stop()
        activitySpinner.stopAnimating()
        activitySpinner.isHidden = true
    }
    
    func requestSpeechAuth() {
        SFSpeechRecognizer.requestAuthorization { (authStatus) in
            if authStatus == SFSpeechRecognizerAuthorizationStatus.authorized {
                if let path = Bundle.main.url(forResource: "test", withExtension: "m4a") {
                    do {
                        let sound = try AVAudioPlayer(contentsOf: path)
                        self.audioPlayer = sound
                        self.audioPlayer.delegate = self
                        sound.play()
                    } catch {
                        print("Error")
                    }
                    
                    let recognizer = SFSpeechRecognizer()
                    let request = SFSpeechURLRecognitionRequest(url: path)
                    recognizer?.recognitionTask(with: request) { (result,error) in
                        if let error = error {
                            print("There was an error: \(error)")
                        } else {
                            self.transcriptionTextView.text = result?.bestTranscription.formattedString
                        }
                    }
                }
            }
        }
    }
    
    @IBAction func playBtnPressed(_ sender: CircleButton) {
        activitySpinner.isHidden = false
        activitySpinner.startAnimating()
        requestSpeechAuth()
    }
    
}

