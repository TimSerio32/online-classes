//
//  ViewController.m
//  GettersSetters
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "Vehicle.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    Vehicle *car = [[Vehicle alloc] init];
    // This is bad
    car.odometer = -100;
    
    NSLog(@"Odometer: %lu", car.odometer);
    
    car.model = @"BMW 328i";
    NSLog(@"I drive a %@", car.model);
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
