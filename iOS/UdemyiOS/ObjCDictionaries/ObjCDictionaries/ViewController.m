//
//  ViewController.m
//  ObjCDictionaries
//
//  Created by Tim Serio on 9/22/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

// NSDictionary is compatible with Swift Dictionaries

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    NSNumber *age = [NSNumber numberWithInt:40];
    NSNumber *age2 = [NSNumber numberWithInt:34];
    NSDictionary *dict = @{@"Jack": age, @"Jon": age2};
    NSDictionary *dict2 = [[NSDictionary alloc] init];
    int jacksAge = [[dict objectForKey:@"Jack"] intValue];
    NSNumber *age3 = [dict objectForKey:@"Jack"];
    int jacksAge2 = [age3 intValue];
    NSLog(@"Jack's age: %d", jacksAge);
    NSMutableDictionary *mut = [@{@"1": @"BMW", @"2": @"Volvo", @"3": @"Honda"} mutableCopy];
    // Most of the time this is what you want to do
    NSMutableDictionary *mut2 = [[NSMutableDictionary alloc] init];
    [mut2 setObject:@"Obj" forKey:@"Key"];
    [mut2 setObject:[NSNumber numberWithDouble:20.0000] forKey:@"Double"];
    
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
