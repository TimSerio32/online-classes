//
//  Civic.m
//  ObjectOrientedObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "Civic.h"

@implementation Civic

- (id)init {
    if(self = [super init]) {
        
    }
    [self drive];
    return self;
}

- (void)test {
    self.make = @"Honda";
    self.model = @"Civic";
}

- (void)drive {
    NSLog(@"Drive from subclass.");
    [super drive];
}

@end
