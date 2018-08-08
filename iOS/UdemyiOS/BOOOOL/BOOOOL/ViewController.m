//
//  ViewController.m
//  BOOOOL
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    BOOL amICool = YES;
    BOOL isTheOthrPersonCool = NO;
    
    if(amICool) {
        NSLog(@"You are cool.");
    } else {
        NSLog(@"We shouldn't get here.");
    }
    
    NSString *name = nil;
    
    if(name) {
        NSLog(@"Name: %@", name);
    }
    
    if(!name) {
        name = @"Sandra";
    }
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
