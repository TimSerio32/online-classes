//
//  VideoTableViewCell.m
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "VideoTableViewCell.h"
#import "Video.h"

@interface  VideoTableViewCell()
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UILabel *descLabel;
@property (weak, nonatomic) IBOutlet UIImageView *thumbImg;
@property (weak, nonatomic) IBOutlet UIView *cellView;



@end

@implementation VideoTableViewCell

- (void)awakeFromNib {
    [super awakeFromNib];
    self.layer.cornerRadius = 2.0;
    self.layer.shadowColor = [UIColor colorWithRed:157.0 / 255.0 green:157.0 / 255.0 blue:157.0 / 255.0 alpha:0.8].CGColor;
    self.layer.shadowOpacity = 0.8;
    self.layer.shadowRadius = 5.0;
    self.layer.shadowOffset = CGSizeMake(0.0, 2.00);
}

- (void)updateUI:(nonnull Video *)video {
    self.titleLabel.text = video.videoTitle;
    self.descLabel.text = video.videoDescription;
    UIImage *image = [UIImage imageWithData:[NSData dataWithContentsOfURL:[NSURL URLWithString:video.thumbnailUrl]]];
    self.thumbImg.image = image;
}

@end
