//
//  ViewController.m
//  ObjCPost
//
//  Created by Tim Serio on 9/25/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    
    NSDictionary *comment = @{@"username":@"devslopes", @"comment": @"This video is frikken sick dawg!"};
    
    NSError *error;
    
    NSURLSession *session = [NSURLSession sharedSession];
    NSURL *url = [NSURL URLWithString:@"http://localhost:6069/comments"];
    NSMutableURLRequest *request = [NSMutableURLRequest requestWithURL:url];
    [request addValue:@"application/json" forHTTPHeaderField:@"Content-Type"];
    [request addValue:@"application/json" forHTTPHeaderField:@"Accept"];
    
    [request setHTTPMethod:@"Post"];
    
    NSData *postData = [NSJSONSerialization dataWithJSONObject:comment options:	0 error:&error];
    
    [request setHTTPBody:postData];
    
    NSURLSessionDataTask *postDataTask = [session dataTaskWithRequest:request completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        //handle errors
    }];
    
    [postDataTask resume];
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
