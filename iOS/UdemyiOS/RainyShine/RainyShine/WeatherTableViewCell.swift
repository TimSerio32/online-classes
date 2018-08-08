//
//  WeatherTableViewCell.swift
//  RainyShine
//
//  Created by Tim Serio on 9/7/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class WeatherTableViewCell: UITableViewCell {

    @IBOutlet weak var weatherIcon: UIImageView!
    @IBOutlet weak var dayLbl: UILabel!
    @IBOutlet weak var weatherType: UILabel!
    @IBOutlet weak var highTemp: UILabel!
    @IBOutlet weak var lowTemp: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
    }

    func configureCell(forecast: Forecast) {
        lowTemp.text = "\(forecast.lowTemp)"
        highTemp.text = "\(forecast.highTemp)"
        weatherType.text = forecast.weatherType
        weatherIcon.image = UIImage(named: forecast.weatherType)
        dayLbl.text = forecast.date
    }
}
