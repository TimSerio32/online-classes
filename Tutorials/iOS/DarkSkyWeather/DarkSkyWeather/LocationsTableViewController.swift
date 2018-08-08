//
//  LocationsTableViewController.swift
//  DarkSkyWeather
//
//  Created by Tim Serio on 8/27/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import CoreLocation
import CoreData

class LocationsTableViewController: UITableViewController, UISearchBarDelegate {
    @IBOutlet weak var searchBar: UISearchBar!
    var forecastData = [CurrentWeatherJSON]()
    var locations: [NSManagedObject] = []
    
    /*let container = (UIApplication.shared.delegate as! AppDelegate).persistentContainer
    let context: NSManagedObjectContext = container.viewContext
    let location: NSManagedObject = NSEntityDescription.insertNewObject(forEntityName: "Locations", into: context)
    let locName = location.value(forKeyPath: "Locatio.location") as? String
    do {
        try context.save()
    } catch {
    
    }*/
    
    override func viewDidLoad() {
        super.viewDidLoad()
        searchBar.delegate = self
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        
        let managedContext = appDelegate.persistentContainer.viewContext
        
        let fetchRequest = NSFetchRequest<NSManagedObject>(entityName: "Locations")
        
        do {
            locations = try managedContext.fetch(fetchRequest)
        } catch let error as NSError {
            print("Could not fetch. \(error), \(error.userInfo)")
        }
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        searchBar.resignFirstResponder()
        if let locationString = searchBar.text, !(locationString.isEmpty) {
            //append to locations
            self.save(name: locationString)
            DispatchQueue.main.async {
                self.tableView.reloadData()
            }
            //updateWeatherForLocation(location: locationString)
        }
    }
    /*
    func updateWeatherForLocation(location: String) {
        CLGeocoder().geocodeAddressString(location) { (placemarks: [CLPlacemark]?, error: Error?) in
            if error == nil {
                if let location = placemarks?.first?.location {
                    CurrentWeatherJSON.current(withLocation: location.coordinate, completion: { (results:[CurrentWeatherJSON]?) in
                        
                        if let weatherData = results {
                            self.forecastData = weatherData
                            
                            DispatchQueue.main.async {
                                self.tableView.reloadData()
                            }
                        }
                    })
                }
            }
        }
    }*/
    
    func save(name: String) {
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        
        let managedContext = appDelegate.persistentContainer.viewContext
        
        let entity = NSEntityDescription.entity(forEntityName: "Locations", in: managedContext)!
        
        let location = NSManagedObject(entity: entity, insertInto: managedContext)
        
        location.setValue(name, forKey: "locations")
        
        do {
            try managedContext.save()
            locations.append(location)
        } catch let error as NSError {
            print("Could not save. \(error), \(error.userInfo)")
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return locations.count
    }

    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "locationCell", for: indexPath) as! LocationTableViewCell
        
        let loc = locations[indexPath.row]
        //let weatherObject = forecastData[indexPath.row]
        
        //cell.textLabel?.text = loc.value(forKey: "locations") as? String
        cell.locationLabel.text = loc.value(forKey: "locations") as? String
        ////cell.locationLabel.text = locations[indexPath.row]
        //cell.temperatureLabel.text = "\(Int(weatherObject.temperature)) °F"
        //cell.imgView?.image = UIImage(named: weatherObject.icon)
        
        /*cell.textLabel?.text = weatherObject.summary
         cell.detailTextLabel?.text = "\(Int(weatherObject.temperature)) °F"
         cell.imageView?.image = UIImage(named: weatherObject.icon)*/
        
        return cell
    }
    

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
