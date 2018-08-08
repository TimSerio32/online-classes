//
//  VideoViewController.h
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/25/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
@class Video;

@interface VideoViewController : ViewController<UIWebViewDelegate>
@property(nonatomic,strong) Video *video;
@end
