//
//  School.m
//  ImportantPrinciplesObjC
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "School.h"
#import "Person.h"

@implementation School

- (void)initData {
    self.student = [[Person alloc] init];
    [self.student initData];
    
    // student is still in memory (our backpack has reference to the student)
    // now you can never access that student; Backpack owns a Person and Person owns a Backpack
    // but they can now never be released. This is a retain cycle. To fix this, the Backpack
    // will have a weak reference to the Person.
    self.student = nil;
}

@end
