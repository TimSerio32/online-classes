//
//  Person.h
//  ImportantPrinciplesObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>
@class Backpack;

@interface Person : NSObject
@property (nonatomic, strong) Backpack *backpack;
- (void)initData;
@end
