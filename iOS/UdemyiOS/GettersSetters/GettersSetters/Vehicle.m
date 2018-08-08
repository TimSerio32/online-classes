//
//  Vehicle.m
//  GettersSetters
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "Vehicle.h"

@implementation Vehicle

// Explicitly overriding setter
-(void)setOdometer:(long)odometer {
    if(odometer > _odometer) {
        // Set instance variable, not property
        _odometer = odometer;
    }
}

// Explicitly overriding getter
-(NSString*)model {
    if([_model isEqualToString:@"Honda Civic"]) {
        return @"great car";
    } else {
        return _model;
    }
}

@end
