//
//  WeatherViewController.swift
//  RainyShine
//
//  Created by Tim Serio on 9/5/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import CoreLocation
import Alamofire

class WeatherViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, CLLocationManagerDelegate {
    
    // Open Weather API Key: c763406d694eb599962a2662cdf4e980

    @IBOutlet weak var dateLbl: UILabel!
    @IBOutlet weak var currentTempLbl: UILabel!
    @IBOutlet weak var locationLbl: UILabel!
    @IBOutlet weak var currentWeatherIcon: UIImageView!
    @IBOutlet weak var currentWeatherTypeLbl: UILabel!
    @IBOutlet weak var tableView: UITableView!
    
    
    //var locationManager: CLLocationManager!
    let locationManager = CLLocationManager()
    var currentLocation: CLLocation!
    
    //var currentWeather = CurrentWeather()
    var currentWeather: CurrentWeather!
    var forecast: Forecast!
    var forecasts = [Forecast]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestWhenInUseAuthorization()
        locationManager.startMonitoringSignificantLocationChanges()
        
        tableView.delegate = self
        tableView.dataSource = self
        
        currentWeather = CurrentWeather()
        //forecast = Forecast()
        
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        locationAuthStatus()
    }
    
    func locationAuthStatus() {
        if CLLocationManager.authorizationStatus() == .authorizedWhenInUse {

            currentLocation = locationManager.location
            Location.sharedInstance.latitude = currentLocation.coordinate.latitude
            Location.sharedInstance.longitude = currentLocation.coordinate.longitude
            currentWeather.downloadWeatherDetails {
                // self.updateMainUI()
                self.downloadForecastData {
                    self.updateMainUI()
                }
            }
        } else {
            locationManager.requestWhenInUseAuthorization()
            locationAuthStatus()
        }
    }
    
    func downloadForecastData(completed: @escaping DownloadComplete) {
        // Downloading forecast weather data for TableView
        let forecastURL = URL(string: FORECAST_URL)
        Alamofire.request(forecastURL!).responseJSON { (response) in
            
            //Alamofire.request(forecastURL!).responseJSON { response in
            let result = response.result
            
            if let dict = result.value as? [String: Any] {
                
                if let list = dict["list"] as? [[String: Any]] {
                    self.forecasts = [Forecast]()
                    for obj in list {
                        let forecast = Forecast(weatherDict: obj)
                        self.forecasts.append(forecast)
                        //print(obj)
                    }
                    self.tableView.reloadData()
                }
                
            }
            completed()
        }
    }
    
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return forecasts.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "weatherCell", for: indexPath) as? WeatherTableViewCell {
            let forecast = forecasts[indexPath.row]
            cell.configureCell(forecast: forecast)
            return cell
        }
        return WeatherTableViewCell()
        
        
    }
    
    func updateMainUI() {
        dateLbl.text = currentWeather.date
        currentTempLbl.text = "\(currentWeather.currentTemp)°F"
        currentWeatherTypeLbl.text = currentWeather.weatherType
        locationLbl.text = currentWeather.cityName
        currentWeatherIcon.image = UIImage(named: currentWeather.weatherType)
    }
    
}

extension Double
{
    func truncate(places : Int)-> Double
    {
        return Double(floor(pow(10.0, Double(places)) * self)/pow(10.0, Double(places)))
    }
}

