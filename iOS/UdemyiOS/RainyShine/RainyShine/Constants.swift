//
//  Constants.swift
//  RainyShine
//
//  Created by Tim Serio on 9/6/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
/*
let BASE_URL = "http://api.openweathermap.org/data/2.5/weather?"
let LATITUDE = "lat="
let LONGITUDE = "&lon="
let APP_ID = "&appid="
let API_KEY = "c763406d694eb599962a2662cdf4e980"*/

typealias DownloadComplete = () -> ()

let CURRENT_WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?lat=\(Location.sharedInstance.latitude)&lon=\(Location.sharedInstance.longitude)&appid=c763406d694eb599962a2662cdf4e980"
let FORECAST_URL = "http://samples.openweathermap.org/data/2.5/forecast/daily?lat=\(Location.sharedInstance.latitude)&lon=\(Location.sharedInstance.longitude)&cnt=10&appid=c763406d694eb599962a2662cdf4e980"
