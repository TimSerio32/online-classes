//
//  Backpack.h
//  ImportantPrinciplesObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>
@class Person;

@interface Backpack : NSObject
// This means that when the school class makes a reference to Person nil, this reference also
// becomes nil
@property (nonatomic, weak) Person *owner;
@end
