//
//  Person.h
//  Properties
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Person : NSObject {
    // This is where you define instance variables
    BOOL isInsecure;
}

@property (nonatomic, strong) NSString *firstName;
@property (nonatomic, strong) NSString *lastName;

@end
