//
//  ViewController.h
//  BOOOOL
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

// You cant set strong on a BOOL property because BOOL isn't an object
@property (nonatomic, assign) BOOL isItSunny;

@end

