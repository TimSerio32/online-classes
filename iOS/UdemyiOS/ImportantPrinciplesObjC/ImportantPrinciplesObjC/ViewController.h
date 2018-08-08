//
//  ViewController.h
//  ImportantPrinciplesObjC
//
//  Created by Tim Serio on 9/23/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController

// Property Attributes
// nonatomic: means that the object being referenced is not thread safe
// This means that the object is not able to deal with multiple requests at the same time.
// strong: means you are holding a strong reference to that variable
// This means that as long as we're hanging onto the variable, it will never be nil.
// weak: you arent holding a strong reference to that variable
// This means a person who's holding it somewhere else, whenever that goes nil, the variable will go nil
// 1. What is a retain cycle? 2. How can you prevent it?
// 1. Retain cycle: when two objects are holding a strong reference to each other
// This is a memory leak: if I have a class called school that holds a reference to Person
// and it made the person nil, the person wouldn't disappear in memory (Backpack stills owns
// Person, it has a strong reference to it. It will never go nil. The school lost reference to the Person and you can never access it again from the Person or anywhere else)
// However the Backpack and the Person are still referenceing each other in memory
// You have memory taken up by these two objects on your iPhone and they are holding references
// to each other, but now you can't even access them.
// 2. You can prevent a retain cycle by making one of the references weak.
// Make the one that makes the most sense. For instance, make the Backpack reference weak
// (Person is more important than a Backpack)

@property (nonatomic, strong) NSString *name;
@property (nonatomic) int age;

@end

