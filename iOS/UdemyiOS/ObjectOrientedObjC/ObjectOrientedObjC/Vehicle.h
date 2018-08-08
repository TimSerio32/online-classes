//
//  Vehicle.h
//  ObjectOrientedObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Vehicle : NSObject
@property (nonatomic,strong) NSString *make;
@property (nonatomic,strong) NSString *model;
@property (nonatomic,strong) NSString *engineSize;
- (void)drive;
@end
