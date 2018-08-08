//
//  ViewController.m
//  ObjectOrientedObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "Civic.h"

@interface ViewController ()
@property (nonatomic, strong) NSString *iAmPrivate;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    Civic *civic = [[Civic alloc] init];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
