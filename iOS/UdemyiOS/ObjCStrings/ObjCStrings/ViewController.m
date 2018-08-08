//
//  ViewController.m
//  ObjCStrings
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"

@interface ViewController ()

@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view, typically from a nib.
    // String Literal
    NSString *firstName = @"John";
    NSString *allocatedString = [[NSString alloc] init];
    
    NSLog(@"Name: %@", firstName);
    
    NSString *fullName = [NSString stringWithFormat:@"%@ Smith", firstName, @"Charles"];
    NSString *display = [fullName stringByAppendingString:@" - Died 1448"];
    NSLog(@"Fullname: %@", fullName);
    NSLog(@"%@", display);
    
    NSString *var1 = @"Junk";
    NSString *var2 = @"Funk";
    NSString *var3 = @"junk";
    
    if(![var1 isEqualToString: var2]) {
        NSLog(@"They are not the same");
    }
    
    if(![var1 isEqualToString: var3]) {
        NSLog(@"They are not the same");
    }
    
    if([var1.lowercaseString isEqualToString:var3]) {
        NSLog(@"They are the same");
    }
    
    // caseInsensitiveCompare needs an NSComparison (NSOrderedSame)
    if([var1 caseInsensitiveCompare:var3] == NSOrderedSame) {
        NSLog(@"They are the same");
    }
}


- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


@end
