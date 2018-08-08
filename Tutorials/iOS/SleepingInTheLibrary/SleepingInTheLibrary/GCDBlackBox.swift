//
//  GCDBlackBox.swift
//  SleepingInTheLibrary
//
//  Created by Tim Serio on 7/27/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation

func performUIUpdatesOnMain(_ updates: @escaping () -> Void) {
    DispatchQueue.main.async {
        updates()
    }
}
