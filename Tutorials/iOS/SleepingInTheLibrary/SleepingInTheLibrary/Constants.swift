//
//  Constants.swift
//  SleepingInTheLibrary
//
//  Created by Tim Serio on 7/27/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import Foundation

struct Constants {
    
    struct Flickr {
        static let APIBaseURL = "https://api.flickr.com/services/rest/"
    }
    
    struct FlickrParameterKeys {
        static let Method = "method"
        static let APIKey = "api_key"
        static let GalleryID = "gallery_id"
        static let Extras = "extras"
        static let Format = "format"
        static let NoJSONCallback = "nojsoncallback"
    }
    
    struct FlickrParameterValues {
        static let APIKey = "33aa42574a295b97597afdac14ec5b3c"
        static let ResponseFormat = "json"
        static let DisableJSONCallback = "1"
        static let GalleryPhotosMethod = "flickr.galleries.getPhotos"
        static let GalleryID = "5704-72157622566655097"
        static let MediumURL = "url_m"
    }
    
    struct FlickrResponseKeys {
        static let Status = "stat"
        static let Photos = "photos"
        static let Photo = "photo"
        static let Title = "title"
        static let MediumURL = "url_m"
    }
    
    struct FlickrResponseValues {
        static let OKStatus = "ok"
    }
}
