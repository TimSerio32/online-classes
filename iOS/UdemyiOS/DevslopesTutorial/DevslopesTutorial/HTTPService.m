//
//  HTTPService.m
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "HTTPService.h"

#define URL_BASE "http://localhost:6069"
#define URL_TUTORIALS "/tutorials"

@implementation HTTPService

+ (id)instance {
    static HTTPService *sharedInstance = nil;
    @synchronized (self) {
        if(sharedInstance == nil) {
            sharedInstance = [[self alloc] init];
        }
    }
    return sharedInstance;
}

- (void)getTutorials:(nullable onComplete)completionHandler {
    
    NSURL *url = [NSURL URLWithString:[NSString stringWithFormat:@"%s%s", URL_BASE, URL_TUTORIALS]];
    NSURLSession *session = [NSURLSession sharedSession];
    
    [[session dataTaskWithURL:url completionHandler:^(NSData * _Nullable data, NSURLResponse * _Nullable response, NSError * _Nullable error) {
        if(data != nil) {
            NSError *err;
            NSArray *json = [NSJSONSerialization JSONObjectWithData:data options:0 error:&err];
            
            if(err == nil) {
                completionHandler(json, nil);
            } else {
                completionHandler(nil,@"Error! Please try again.");
            }
        } else {
            NSLog(@"Err: %@", error.debugDescription);
            completionHandler(nil, @"Problem connecting to the server.");
        }
    }] resume];
    
}

@end
