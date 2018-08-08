//
//  ViewController.m
//  ObjCLoops
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    NSArray *cars = @[@"BMW", @"Massaratti", @"Porsche"];
    for(int x = 0; x < cars.count; x++) {
        NSString *car = [cars objectAtIndex:x];
        NSLog(@"Car: %@", car);
    }
    
    for(NSString *car in cars) {
        NSLog(@"Car: %@", car);
    }
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
