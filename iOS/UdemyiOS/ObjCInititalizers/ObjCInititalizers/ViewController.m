//
//  ViewController.m
//  ObjCInititalizers
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "Person.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Some people have problems with new keyword for initialization and prefer this:
    Person *person = [[Person alloc] init];
    // If you want customization in your initializers you can't use new
    // Person *person2 = [Person new];
    [person printName];
    Person *person3 = [[Person alloc] initWithFirstName:@"Petey" lastName:@"McFreedy"];
    [person3 printName];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
