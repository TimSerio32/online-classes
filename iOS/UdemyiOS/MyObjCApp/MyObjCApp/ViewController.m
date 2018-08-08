//
//  ViewController.m
//  MyObjCApp
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "MyObjCApp-Swift.h"

@interface ViewController ()
@property(nonatomic,strong) SecondViewController *secondVC;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
}
- (IBAction)loadSecondVC:(id)sender {
    _secondVC = [[SecondViewController alloc] init];
    _secondVC.view.backgroundColor = [UIColor purpleColor];
    [self presentViewController:_secondVC animated:YES completion:nil];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
