//
//  Person.m
//  ObjCInititalizers
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "Person.h"

@implementation Person

- (id)initWithFirstName:(NSString*)first lastName:(NSString*)last {
    self.firstName = first;
    self.lastName = last;
    return [self initWithAge:54];
}

- (id)initWithAge: (NSInteger)age {
    self = [super init];
    return self;
}

- (void)printName {
    NSLog(@"%@ %@", self.firstName, self.lastName);
}

@end
