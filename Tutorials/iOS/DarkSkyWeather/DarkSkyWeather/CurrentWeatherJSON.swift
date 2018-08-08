//
//  CurrentWeatherJSON.swift
//  DarkSkyWeather
//
//  Created by Tim Serio on 8/29/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
import CoreLocation

struct CurrentWeatherJSON {
    
    //let location: String
    let icon: String
    let temperature: Double
    
    enum SerializationError: Error {
        case missing(String)
        case invalid(String, Any)
    }
    
    init(json: [String:AnyObject]) throws {
        /*guard let summary = json["summary"] as? String else {
            throw SerializationError.missing("Summary is missing.")
        }*/
        
        guard let icon = json["icon"] as? String else {
            throw SerializationError.missing("Icon is missing.")
        }
        
        guard let temp = json["temperatureMax"] as? Double else {
            throw SerializationError.missing("Temperature is missing.")
        }
        
        //self.summary = summary
        self.icon = icon
        self.temperature = temp
    }
    
    static let baseUrl = "https://api.darksky.net/forecast/397690ffc56b25bf240bc59b748b6313/"
    
    static func current(withLocation location: CLLocationCoordinate2D, completion: @escaping ([CurrentWeatherJSON]?) -> ()) {
        let url = baseUrl + "\(location.latitude),\(location.longitude)"
        
        print(url)
        
        let request = URLRequest(url: URL(string: url)!)
        
        let task = URLSession.shared.dataTask(with: request) { (data: Data?, repsonse: URLResponse?, error: Error?) in
            var forecastArray: [CurrentWeatherJSON] = []
            if let data = data {
                do {
                    if let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String:AnyObject] {
                        if let currentForecast = json["currently"] as? [String: AnyObject] {
                            if let weatherObject = try? CurrentWeatherJSON(json: currentForecast) {
                                forecastArray.append(weatherObject)
                            }
                            /*
                            guard let currIcon = json["icon"] as? String else {
                                throw SerializationError.missing("Icon is missing.")
                            }
                            self.icon = currIcon
                            if let currentData = currentForecast["data"] as? [[String: AnyObject]] {
                                for dataPoint in currentData {
                                    if let weatherObject = try? CurrentWeatherJSON(json: dataPoint) {
                                        forecastArray.append(weatherObject)
                                    }
                                }
                            }*/
                        }
                    }
                } catch {
                    print(error.localizedDescription)
                }
                completion(forecastArray)
            }
        }
        task.resume()
    }
}
