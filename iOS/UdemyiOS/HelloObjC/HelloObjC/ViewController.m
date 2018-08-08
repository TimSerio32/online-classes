//
//  ViewController.m
//  HelloObjC
//
//  Created by Tim Serio on 9/20/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)clickMe:(UIButton *)sender {
    UIAlertController *alert = [UIAlertController alertControllerWithTitle: @"Welcome" message:@"Welcome to the world of Objective-C" preferredStyle:UIAlertControllerStyleAlert];
    UIAlertAction *closeAction = [UIAlertAction actionWithTitle: @"Close" style:UIAlertActionStyleCancel handler:nil];
    [alert addAction:closeAction];
    [self presentViewController:alert animated:YES completion:nil];
}




@end
