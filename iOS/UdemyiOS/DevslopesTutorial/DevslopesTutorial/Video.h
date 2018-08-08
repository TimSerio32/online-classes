//
//  Video.h
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Video : NSObject
@property(nonatomic, strong) NSString *videoTitle;
@property(nonatomic, strong) NSString *videoDescription;
@property(nonatomic, strong) NSString *videoIFrame;
@property(nonatomic, strong) NSString *thumbnailUrl;
@end
