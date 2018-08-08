//
//  ViewController.swift
//  PokeFinder
//
//  Created by Tim Serio on 9/25/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import MapKit
import FirebaseDatabase

class ViewController: UIViewController, MKMapViewDelegate, CLLocationManagerDelegate {

    @IBOutlet weak var mapView: MKMapView!
    var geoFire: GeoFire!
    var geoFireRef: DatabaseReference!
    let locationManager = CLLocationManager()
    var mapHasCenteredOnce = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        mapView.delegate = self
        // The map will move with your location if you are moving
        mapView.userTrackingMode = MKUserTrackingMode.follow
        // initializing Database reference
        geoFireRef = Database.database().reference()
        // initializing GeoFire with a firebase reference
        geoFire = GeoFire(firebaseRef: geoFireRef)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        locationAuthStatus()
    }
    
    func locationAuthStatus() {
        // .always will drain the users battery
        if(CLLocationManager.authorizationStatus() == .authorizedWhenInUse) {
            // if the user already specified authorizationStatus as authorizedWhenInUse, show user location
            mapView.showsUserLocation = true
        } else {
            // if the user didn't already specify authorizationStatus as authorizedWhenInUse, request authorization
            locationManager.requestWhenInUseAuthorization()
        }
    }
    
    // This lets us know when the user has given authorization
    func locationManager(_ manager: CLLocationManager, didChangeAuthorization status: CLAuthorizationStatus) {
        if(status == CLAuthorizationStatus.authorizedWhenInUse) {
            mapView.showsUserLocation = true
        }
    }
    
    func centerMapOnLocation(location: CLLocation) {
        let coordinateRegion = MKCoordinateRegionMakeWithDistance(location.coordinate, 2000, 2000)
        mapView.setRegion(coordinateRegion, animated: true)
    }
    
    func mapView(_ mapView: MKMapView, didUpdate userLocation: MKUserLocation) {
        if let loc = userLocation.location {
            if(!mapHasCenteredOnce) {
                // if the map hasn't centered yet center the map based on the users location
                centerMapOnLocation(location: loc)
                mapHasCenteredOnce = true
            }
        }
    }
    
    // Makes a custom annotation on map
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        let annoIdentifier = "Pokemon"
        var annotationView: MKAnnotationView?
        if(annotation.isKind(of: MKUserLocation.self)) {
            annotationView = MKAnnotationView(annotation: annotation, reuseIdentifier: "User")
            annotationView?.image = UIImage(named: "ash")
        } else if let deqAnno = mapView.dequeueReusableAnnotationView(withIdentifier: annoIdentifier) {
            annotationView = deqAnno
            annotationView?.annotation = annotation
        } else {
            let av = MKAnnotationView(annotation: annotation, reuseIdentifier: annoIdentifier)
            av.rightCalloutAccessoryView = UIButton(type: .detailDisclosure)
            annotationView = av
        }
        
        if let annotationView = annotationView, let anno = annotation as? PokeAnnotation {
           // If you show a callout on an annotation, you have to set the title
           annotationView.canShowCallout = true
            annotationView.image = UIImage(named: "\(anno.pokemonNumber)")
            let btn = UIButton()
            btn.frame = CGRect(x: 0, y: 0, width: 30, height: 30)
            btn.setImage(UIImage(named: "map"), for: .normal)
            annotationView.rightCalloutAccessoryView = btn
        }
        
        
        return annotationView
    }
    
    func createSighting(forLocation location: CLLocation, withPokemon pokeId: Int) {
        geoFire.setLocation(location, forKey: "\(pokeId)")
    }
    
    func showSightingsOnMap(location: CLLocation) {
        let circleQuery = geoFire!.query(at: location, withRadius: 2.5)
        _ = circleQuery?.observe(GFEventType.keyEntered, with: { (key, location) in
            if let key = key, let location = location {
                let anno = PokeAnnotation(coordinate: location.coordinate, pokemonNumber: Int(key)!)
                self.mapView.addAnnotation(anno)
            }
        })
    }
    
    func mapView(_ mapView: MKMapView, regionWillChangeAnimated animated: Bool) {
        let loc = CLLocation(latitude: mapView.centerCoordinate.latitude, longitude: mapView.centerCoordinate.longitude)
        showSightingsOnMap(location: loc)
    }

    func mapView(_ mapView: MKMapView, annotationView view: MKAnnotationView, calloutAccessoryControlTapped control: UIControl) {
        if let anno = view.annotation as? PokeAnnotation {
            var place: MKPlacemark!
            if #available(iOS 10.0, *) {
                place = MKPlacemark(coordinate: anno.coordinate)
            } else {
                place = MKPlacemark(coordinate: anno.coordinate, addressDictionary: nil)
            }
            
            let destination = MKMapItem(placemark: place)
            destination.name = "Pokemon Sighting"
            let regionDistance: CLLocationDistance = 1000
            let regionSpan = MKCoordinateRegionMakeWithDistance(anno.coordinate, regionDistance, regionDistance)
            let options = [
                MKLaunchOptionsMapCenterKey: NSValue(mkCoordinate: regionSpan.center),
                MKLaunchOptionsMapSpanKey: NSValue(mkCoordinateSpan: regionSpan.span),
                MKLaunchOptionsDirectionsModeKey: MKLaunchOptionsDirectionsModeDriving
            ] as [String : Any]
            MKMapItem.openMaps(with: [destination], launchOptions: options)
        }
    }
    
    @IBAction func spotRandomPokemon(_ sender: UIButton) {
        let loc = CLLocation(latitude: mapView.centerCoordinate.latitude, longitude: mapView.centerCoordinate.longitude)
        
        let rand = arc4random_uniform(150) + 1
        createSighting(forLocation: loc, withPokemon: Int(rand))
    }

}

