//
//  Honda.h
//  ObjCCategories
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Honda : NSObject

@property(nonatomic, strong) NSString *model;
@property(nonatomic, strong) NSNumber *miles;
- (void)increaseMilesToOdometer;
- (void)drive;

@end
