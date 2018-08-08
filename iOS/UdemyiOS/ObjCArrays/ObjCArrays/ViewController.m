//
//  ViewController.m
//  ObjCArrays
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
    
    NSArray *arr = [NSArray arrayWithObjects:@"My", @"mother", @"told", @"me", nil];
    NSArray *arr2 = @[@"Donkey", @"Kong", @"Mario", @"Luigi"];
    
    NSString *str = [arr2 objectAtIndex: 2];
    
    arr = @[@"Yoshi", @"Peach"];
    
    NSMutableArray *mut = [NSMutableArray arrayWithObjects: @"Boom", @"Jam", @"Slam", @"Pam", nil];
    NSLog(@"MUT: %@", mut.debugDescription);
    
    [mut removeObjectAtIndex:2];
    [mut addObject: @"Beast"];
    
    NSLog(@"MUT 2: %@", mut.debugDescription);
    
    NSArray *arr3 = [NSArray arrayWithArray:arr];
    
    // You can put an NSMutableArray into an NSArray.
    // You can't put an NSArray into an NSMutableArray.
    NSArray *arr4 = mut;
    // NSMutableArray *arr4 = arr;
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
