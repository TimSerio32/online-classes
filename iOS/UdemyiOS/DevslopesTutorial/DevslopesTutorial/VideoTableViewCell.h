//
//  VideoTableViewCell.h
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <UIKit/UIKit.h>
@class Video;

@interface VideoTableViewCell : UITableViewCell
- (void)updateUI:(nonnull Video *)video;
@end
