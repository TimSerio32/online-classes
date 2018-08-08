//
//  HTTPService.h
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef void (^onComplete)(NSArray * _Nullable dataArray, NSString * _Nullable errMessage);

@interface HTTPService : NSObject

+ (id)instance;
- (void)getTutorials:(nullable onComplete)completionHandler;

@end
