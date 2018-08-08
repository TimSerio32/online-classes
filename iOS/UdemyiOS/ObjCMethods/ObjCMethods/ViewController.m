//
//  ViewController.m
//  ObjCMethods
//
//  Created by Tim Serio on 9/21/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "Person.h"

@interface ViewController ()

@property (nonatomic) double bankAccount;
@property (nonatomic) double itemAmount;

@end

@implementation ViewController

// - means method of your object
// Static methods (only one in memory at any given time among all instances of your class) are declared with +
// (type) declares the return type of the function

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.bankAccount = 500.50;
    self.itemAmount = 400.00;
}

- (BOOL)canPurchase:(double)amount {
    if(self.bankAccount >= amount) {
        return YES;
    }
    return NO;
}

- (void)declareWinnerWithPlayerAScore: (NSInteger)scoreA playerAScore: (NSInteger)scoreB {
    if(scoreA > scoreB) {
        NSLog(@"Player A Wins");
    } else if(scoreB > scoreA) {
        NSLog(@"Player B Wins");
    } else {
        NSLog(@"Tie");
    }
}

- (void) playground {
    
    if ([self canPurchase:self.itemAmount]) {
        NSLog(@"We can buy!");
    }
    
    [self declareWinnerWithPlayerAScore:55 playerAScore: 66];
    
    Person *person = [[Person alloc] init];
    [person speakName];
    [Person stateSpecies];
    
    UIImage *image1 = [UIImage imageWithData: [NSData dataWithContentsOfURL: [NSURL URLWithString:@"http://google.com"]]];
    
    NSString *urlString = @"http://google.com";
    NSURL *url = [NSURL URLWithString:urlString];
    NSData *data = [NSData dataWithContentsOfURL:url];
    
    UIImage *image2 = [UIImage imageWithData:data];
    
    NSNumber *number = [NSNumber numberWithInt:55];
    // Property Accessors and Setters
    NSLog(@"Number: %@", number.stringValue);
    // Functions
    NSLog(@"Number: %@", [number stringValue]);
}


@end
