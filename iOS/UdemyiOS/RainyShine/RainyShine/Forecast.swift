//
//  Forecast.swift
//  RainyShine
//
//  Created by Tim Serio on 9/6/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import Alamofire

class Forecast {
    
    var _date: String!
    var _weatherType: String!
    var _highTemp: String!
    var _lowTemp: String!
    
    var date: String {
        if _date == nil {
            _date = ""
        }
        
        return _date
    }
    
    var weatherType: String {
        if _weatherType == nil {
            _weatherType = ""
        }
        
        return _weatherType
    }
    
    var highTemp: String {
        if _highTemp == nil {
            _highTemp = ""
        }
        
        return _highTemp
    }
    
    var lowTemp: String {
        if _lowTemp == nil {
            _lowTemp = ""
        }
        
        return _lowTemp
    }
    
    init(weatherDict: [String: Any]) {
    
        if let temp = weatherDict["temp"] as? [String: Any] {
            if let min = temp["min"] as? Double {
                let kelvinToFarenheitPreDivision = (min * (9/5) - 459.67)
                let kelvinToFarenheit = Double(round(10 * kelvinToFarenheitPreDivision/10))
                self._lowTemp = "\(kelvinToFarenheit)"
            }
            
            if let max = temp["max"] as? Double {
                let kelvinToFarenheitPreDivision = (max * (9/5) - 459.67)
                let kelvinToFarenheit = Double(round(10 * kelvinToFarenheitPreDivision/10))
                self._highTemp = "\(kelvinToFarenheit)"
            }
        }
        
        if let weather = weatherDict["weather"] as? [[String: Any]] {
            if let main = weather[0]["main"] as? String {
                self._weatherType = main
            }
        }
        
        if let date = weatherDict["dt"] as? Double {
            let unixConvertedDate = Date(timeIntervalSince1970: date)
            let dateFormatter = DateFormatter()
            dateFormatter.dateStyle = .full
            dateFormatter.dateFormat = "EEEE"
            dateFormatter.timeStyle = .none
            self._date = unixConvertedDate.dayOfTheWeek()
        }
        
    }
    /*
    init(weatherDict: Dictionary<String, Any>){
        if let temp = weatherDict["main"] as? Dictionary<String, Any>{
            if let min = temp["temp_min"] as? Double{
                //Convert Kelvin to centigrade and update our model
                self._lowTemp = "\(Int(min - 273.15))"
            }
            
            if let max = temp["temp_max"] as? Double{
                //Convert Kelvin to centigrade and update our model
                self._highTemp = "\(Int(max - 273.15))"
            }
        }
        
        if let weather = weatherDict["weather"] as? [Dictionary<String, Any>]{
            if let main = weather[0]["main"] as? String{
                self._weatherType = main
            }
        }
        
        if let forecastTime = weatherDict["dt_txt"] as? String{
            let dateFormatter = DateFormatter()
            //This is the date string pattern match we are sending in from JSON
            dateFormatter.dateFormat = "yyyy-MM-dd HH:mm:ss"
            //Convert our JSON date string to a Date type
            let dateString = dateFormatter.date(from: forecastTime)
            //Now rematch our expected output style
            dateFormatter.dateFormat = "ha" //Shows only the hour and a AM/PM indicator
            //Update our data model now
            self._forecastTime = "\(dateFormatter.string(from: dateString!))"
        }
    }*/
    
}

extension Date {
    func dayOfTheWeek() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        return dateFormatter.string(from: self)
    }
}
