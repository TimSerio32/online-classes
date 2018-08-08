//
//  ViewController.h
//  Pointers
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

// Use dereference on objects; Native c types like int dont need dereferencing
@property (nonatomic, strong) NSString *name;
@property (nonatomic) int age;
@property (nonatomic) NSInteger debt;
@property (nonatomic) NSNumber *bankBalance;

@end

