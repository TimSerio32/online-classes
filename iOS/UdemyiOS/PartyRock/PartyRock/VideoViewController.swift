//
//  VideoViewController.swift
//  PartyRock
//
//  Created by Tim Serio on 9/4/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class VideoViewController: UIViewController {

    @IBOutlet weak var webView: UIWebView!
    
    @IBOutlet weak var titleLbl: UILabel!
    
    private var _partyRock: PartyRock!
    
    var partyRock: PartyRock {
        get {
            return _partyRock
        } set {
            _partyRock = newValue
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        titleLbl.text = partyRock.videoTitle
        webView.loadHTMLString(partyRock.videoURL, baseURL: nil)
        //self.webView.frame = self.view.bounds
        self.webView.scalesPageToFit = true
    }

}
