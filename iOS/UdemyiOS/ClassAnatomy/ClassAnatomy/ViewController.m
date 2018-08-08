//
//  ViewController.m
//  ClassAnatomy
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

// Lets you declare local variables that you only want available in your class
// Optional
@interface ViewController ()

//local variable
@property (nonatomic, strong) NSString *vehicle;

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    
    
    self.name = @"Jack";
    // %@ in a string means string interpolation
    NSLog(@"Name: %@", self.name);
    // Underscore before a property name is another way to access the property
    _name = @"Peter";
    NSLog(@"Name: %@", _name);
    [self setName:@"Rufus"];
    NSLog(@"Name: %@", [self name]);
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
