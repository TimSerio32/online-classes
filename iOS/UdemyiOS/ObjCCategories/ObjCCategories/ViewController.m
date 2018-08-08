//
//  ViewController.m
//  ObjCCategories
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "Honda.h"
#import "Honda+SoupedUp.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    
    Honda *honda = [[Honda alloc] init];
    [honda addUglySpoiler];
    [honda addGoldPlasticSpinnerRims];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
