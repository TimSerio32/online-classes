//
//  ViewController.m
//  Properties
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "Person.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    // Initalizing a class
    Person *person1 = [[Person alloc] init];
    person1.firstName = @"Sandra";
    [person1 setLastName: @"Mandra"];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
