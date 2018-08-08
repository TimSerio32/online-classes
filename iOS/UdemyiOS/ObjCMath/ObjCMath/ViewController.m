//
//  ViewController.m
//  ObjCMath
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
    
    // When storing numbers use NSNumber
    // When you're ready to do operations use native c types
    // You grab values out of NSNumbers, run those operations and put them back into NSNumbers
    int imAnInt = 5;
    float imAFloat = 3.6664;
    double imADouble = 3.55555554;
    
    NSNumber *numInt = [NSNumber numberWithInt:5];
    NSNumber *numInt2 = [NSNumber numberWithInt:6];
    
    // Getting value out of NSNumber
    // Can also use numInt.intValue
    int val = [numInt intValue];
    int val2 = numInt2.intValue;
    
    int sum = val + val2;
    
    NSLog(@"Sum: %d", sum);
    
    NSNumber *numSum = [NSNumber numberWithInt:sum];
    
    NSArray *arr = @[numInt,numInt2,numSum];
    
    NSString *str = numSum.stringValue;
    
    // NSInteger is safer to use
    NSInteger someInt = 55;
    
    NSNumber *sumNum = [NSNumber numberWithInt:[numInt intValue] * [numInt2 intValue]];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
