//
//  Person.m
//  ImportantPrinciplesObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "Person.h"
#import "Backpack.h"

@implementation Person

- (void)initData {
    self.backpack = [[Backpack alloc] init];
    self.backpack.owner = self;
}

@end
