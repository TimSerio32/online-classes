//
//  ViewController.swift
//  SleepingInTheLibrary
//
//  Created by Tim Serio on 7/27/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var photoImageView: UIImageView!
    @IBOutlet weak var photoTitleLabel: UILabel!
    @IBOutlet weak var grabImageButton: UIButton!
    
    
    @IBAction func grabNewImage(_ sender: UIButton) {
        setUIEnabled(false)
        getImageFromFlickr()
    }
    
    private func setUIEnabled(_ enabled: Bool) {
        photoTitleLabel.isEnabled = enabled
        grabImageButton.isEnabled = enabled
        
        if enabled {
            grabImageButton.alpha = 1.0
        } else {
            grabImageButton.alpha = 0.5
        }
    }
    
    private func getImageFromFlickr() {
        
        let methodParameters = [
            Constants.FlickrParameterKeys.Method: Constants.FlickrParameterValues.GalleryPhotosMethod,
            Constants.FlickrParameterKeys.APIKey: Constants.FlickrParameterValues.APIKey,
            Constants.FlickrParameterKeys.GalleryID: Constants.FlickrParameterValues.GalleryID,
            Constants.FlickrParameterKeys.Extras: Constants.FlickrParameterValues.MediumURL,
            Constants.FlickrParameterKeys.Format: Constants.FlickrParameterValues.ResponseFormat,
            Constants.FlickrParameterKeys.NoJSONCallback: Constants.FlickrParameterValues.DisableJSONCallback
        ]
        
        let session = URLSession.shared
        let urlString = Constants.Flickr.APIBaseURL + escapedParameters(parameters: methodParameters as [String : AnyObject])
        
        let url = URL(string: urlString)!
        let request = URLRequest(url: url)
        
        
        let task = session.dataTask(with: request) {
            (data, response, error) in
            
            func displayError(_ error: String) {
                print(error)
                print("URL at time of error: \(url)")
                performUIUpdatesOnMain {
                    self.setUIEnabled(true)
                }
            }
            
            if error == nil {
                
                if let data = data {
                    let parsedResult:[String:AnyObject]!
                    do {
                        parsedResult = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as! [String: AnyObject]
                    } catch {
                        displayError("Could not parse the data as JSON: '\(data)'")
                        return
                    }
                    if let photosDictionary = parsedResult[Constants.FlickrResponseKeys.Photo] as? [String:AnyObject], let photosArray = photosDictionary["photos"] as? [[String: AnyObject]] {
                        let randomPhotoIndex = Int(arc4random_uniform(UInt32(photosArray.count)))
                        let photoDictionary = photosArray[randomPhotoIndex] as [String:AnyObject]
                        if let imageUrlString = photoDictionary[Constants.FlickrResponseKeys.MediumURL] as? String, let photoTitle = photoDictionary[Constants.FlickrResponseKeys.Title] as? String {
                            let imageUrl = URL(string: imageUrlString)
                        
                            if let imageData = try? Data(contentsOf: imageUrl!) {
                                performUIUpdatesOnMain {
                                    print("\(String(describing: imageUrl))")
                                    self.photoImageView.image = UIImage(data: imageData)
                                    self.photoTitleLabel.text = photoTitle
                                    self.setUIEnabled(true)
                                }
                            } else {
                                displayError("Image does not exist at \(imageUrl!)" )
                            }
                        }
                    }
                }
            }
        }
        task.resume()
        /*
        let methodParameters = [
            Constants.FlickrParameterKeys.Method: Constants.FlickrParameterValues.GalleryPhotosMethod,
            Constants.FlickrParameterKeys.APIKey: Constants.FlickrParameterValues.APIKey,
            Constants.FlickrParameterKeys.GalleryID: Constants.FlickrParameterValues.GalleryID,
            Constants.FlickrParameterKeys.Extras: Constants.FlickrParameterValues.MediumURL,
            Constants.FlickrParameterKeys.Format: Constants.FlickrParameterValues.ResponseFormat,
            Constants.FlickrParameterKeys.NoJSONCallback: Constants.FlickrParameterValues.DisableJSONCallback
        ]
        
        let session = URLSession.shared
        let urlString = Constants.Flickr.APIBaseURL + escapedParameters(parameters: methodParameters as [String : AnyObject])
        
        let url = URL(string: urlString)!
        let request = URLRequest(url: url)
        
        let task = session.dataTask(with: request) {
            (data, response, error) in
            
            func displayError(_ error: String) {
                print(error)
                print("URL at time of error: \(url)")
                performUIUpdatesOnMain {
                    self.setUIEnabled(true)
                }
            }
            
            guard (error == nil) else {
                displayError("There was an error with your request: \(error!)")
                return
            }
            
            guard let statusCode = (response as? HTTPURLResponse)?.statusCode, statusCode >= 200 && statusCode <= 299 else {
                displayError("Your request returned a status code other than 2xx")
                return
            }
            
            guard let data = data else {
                displayError("No data returned by the request")
                return
            }
            let parsedResult: [String:AnyObject]!
            do {
                parsedResult = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as! [String: AnyObject]
            } catch {
                displayError("Could not parse the data as JSON: '\(data)'")
                return
            }
            
            guard let stat = parsedResult[Constants.FlickrResponseKeys.Status] as? String, stat == Constants.FlickrResponseValues.OKStatus else {
                displayError("Flickr API returned an error. See error code and message in \(parsedResult)")
                return
            }
            
            guard let photosDictionary = parsedResult[Constants.FlickrResponseKeys.Photos] as? [String:AnyObject], let photoArray = photosDictionary[Constants.FlickrResponseKeys.Photo] as? [[String:AnyObject]] else {
                displayError("Cannot find keys '\(Constants.FlickrResponseKeys.Photos)' and '\(Constants.FlickrResponseKeys.Photo)' in \(parsedResult)")
                return
            }
            
            let randomPhotoIndex = Int(arc4random_uniform(UInt32(photoArray.count)))
            let photoDictionary = photoArray[randomPhotoIndex] as [String:AnyObject]
            let photoTitle = photoDictionary[Constants.FlickrResponseKeys.Title] as? String
            
            guard let imageUrlString = photoDictionary[Constants.FlickrResponseKeys.MediumURL] as? String else {
                displayError("Cannot find key '\(Constants.FlickrResponseKeys.MediumURL)' in \(photoDictionary)")
                return
            }
            
            let imageURL = URL(string: imageUrlString)
            if let imageData = try? Data(contentsOf: imageURL!) {
                performUIUpdatesOnMain {
                    self.setUIEnabled(true)
                    self.photoImageView.image = UIImage(data: imageData)
                    self.photoTitleLabel.text = photoTitle ?? "(Untitled)"
                }
            } else {
                displayError("Image does not exist at \(imageURL!)")
            }
        }
        
        task.resume()
        */
        
    }
    
    private func escapedParameters(parameters: [String:AnyObject]) -> String {
        if parameters.isEmpty {
            return ""
        } else {
            var keyValuePairs = [String]()
            for (key,value) in parameters {
                let stringValue = "\(value)"
                let escapedValue = stringValue.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)
                keyValuePairs.append(key + "=" + "\(escapedValue!)")
                
                
            }
            return "?\(keyValuePairs.joined(separator: "&"))"
        }
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }


}

