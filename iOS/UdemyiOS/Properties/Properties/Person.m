//
//  Person.m
//  Properties
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//
// When you create a property it is doing 3 things for you:
// 1. Creating an instance variable under the hood (Called _firstName)
// 2. Creates a getter for you automatically
// 3. Creates a setter for you automatically

#import "Person.h"

@implementation Person

-(void)test {
    self.firstName = @"Blah";
    
    // Accessing automatically created instance variable
    _firstName = @"Jack";
    
    // When you explicitly declare an instance variable, you access it without _
    isInsecure = YES;
    
    // Automatically created setter
    [self setLastName:@"Davis"];
    
    // Automatically created getter
    NSString *name = [self firstName];
}

@end
