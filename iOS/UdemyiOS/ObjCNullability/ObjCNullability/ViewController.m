//
//  ViewController.m
//  ObjCNullability
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

// nonnull before type or _Nonnull after type:
// same as func updateName(name: String) in swift
// nullable before type or _Nullable after type:
// same as func updateName(name: String?) in swift

- (void)updateName: (nullable NSString*)name {
    
}

- (int)sum:(nonnull NSNumber*)numOne :(nonnull NSNumber*)numTwo {
    int theSum = numOne.intValue + numTwo.intValue;
    return theSum;
}

- (void)idiot:(nonnull NSString*)grr {
    
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self updateName:nil];
    NSNumber *numOne;
    NSNumber *numTwo;
    
    int sum = [self sum:numOne :numTwo];
    NSLog(@"Sum: %d", sum);
    
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
