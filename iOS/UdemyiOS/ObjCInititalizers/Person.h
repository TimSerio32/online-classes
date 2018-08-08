//
//  Person.h
//  ObjCInititalizers
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Person : NSObject

@property(nonatomic,strong) NSString *firstName;
@property(nonatomic,strong) NSString *lastName;

-(id)initWithFirstName:(NSString*)first lastName:(NSString*)last;
-(void)printName;

@end
