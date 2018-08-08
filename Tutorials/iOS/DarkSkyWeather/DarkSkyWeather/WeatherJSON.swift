//
//  WeatherJSON.swift
//  DarkSkyWeather
//
//  Created by Tim Serio
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation
import CoreLocation

struct WeatherJSON {
    
    let summary: String
    let icon: String
    let temperature: Double
    
    enum SerializationError: Error {
        case missing(String)
        case invalid(String, Any)
    }
    
    init(json: [String:AnyObject]) throws {
        guard let summary = json["summary"] as? String else {
            throw SerializationError.missing("Summary is missing.")
        }
        
        guard let icon = json["icon"] as? String else {
            throw SerializationError.missing("Icon is missing.")
        }
        
        guard let temp = json["temperatureMax"] as? Double else {
            throw SerializationError.missing("Temperature is missing.")
        }
        
        self.summary = summary
        self.icon = icon
        self.temperature = temp
    }
    
    static let baseUrl = "https://api.darksky.net/forecast/397690ffc56b25bf240bc59b748b6313/"
    
    /*
    static func current(withLocation location: CLLocationCoordinate2D, completion: @escaping ([WeatherJSON]?) -> ()) {
        let url = baseUrl + "\(location.latitude),\(location.longitude)"
        
        print(url)
        
        let request = URLRequest(url: URL(string: url)!)
        
        let task = URLSession.shared.dataTask(with: request) { (data: Data?, repsonse: URLResponse?, error: Error?) in
            var forecastArray: [WeatherJSON] = []
            if let data = data {
                do {
                    if let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String:AnyObject] {
                        if let currentForecast = json["currently"] as? [String: AnyObject] {
                            guard let currIcon = json["icon"] as? String else {
                                throw SerializationError.missing("Icon is missing.")
                            }
                            if let currentData = currentForecast["data"] as? [[String: AnyObject]] {
                                for dataPoint in currentData {
                                    if let weatherObject = try? WeatherJSON(json: dataPoint) {
                                        forecastArray.append(weatherObject)
                                    }
                                }
                            }
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
        */
 
    static func forecast(withLocation location: CLLocationCoordinate2D, completion: @escaping ([WeatherJSON]?) -> ()) {
        
        let url = baseUrl + "\(location.latitude),\(location.longitude)"
        
        print(url)
        
        let request = URLRequest(url: URL(string: url)!)
        
        let task = URLSession.shared.dataTask(with: request) { (data: Data?, repsonse: URLResponse?, error: Error?) in
            var forecastArray: [WeatherJSON] = []
            if let data = data {
                do {
                    if let json = try JSONSerialization.jsonObject(with: data, options: []) as? [String:AnyObject] {
                        if let dailyForecasts = json["daily"] as? [String: AnyObject] {
                            if let dailyData = dailyForecasts["data"] as? [[String: AnyObject]] {
                                for dataPoint in dailyData {
                                    if let weatherObject = try? WeatherJSON(json: dataPoint) {
                                        forecastArray.append(weatherObject)
                                    }
                                }
                            }
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
