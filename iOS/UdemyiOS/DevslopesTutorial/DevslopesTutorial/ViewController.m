//
//  ViewController.m
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/24/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "ViewController.h"
#import "HTTPService.h"
#import "Video.h"
#import "VideoTableViewCell.h"
#import "VideoViewController.h"

@interface ViewController ()
@property (weak, nonatomic) IBOutlet UITableView *tableView;
@property (nonatomic, strong) NSArray *videoList;
@end

@implementation ViewController

- (void)viewDidLoad {
    [super viewDidLoad];
    self.tableView.dataSource = self;
    self.tableView.delegate = self;
    
    self.videoList = [[NSArray alloc] init];
    
    [[HTTPService instance] getTutorials:^(NSArray * _Nullable dataArray, NSString * _Nullable errMessage) {
        if(dataArray) {
            NSMutableArray *arr = [[NSMutableArray alloc] init];
            for(NSDictionary *d in dataArray) {
                Video *vid = [[Video alloc] init];
                vid.videoTitle = [d objectForKey:@"title"];
                vid.videoDescription = [d objectForKey:@"description"];
                vid.thumbnailUrl = [d objectForKey:@"thumbnail"];
                vid.videoIFrame = [d objectForKey:@"iframe"];
                
                [arr addObject:vid];
            }
            
            self.videoList = arr;
            [self updateTableData];
            
        } else if(errMessage) {
            //Display alert to user
        }
    }];
}

- (void) updateTableData {
    dispatch_async(dispatch_get_main_queue(), ^{
        [self.tableView reloadData];
    });
}

- (UITableViewCell*)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    VideoTableViewCell *cell = (VideoTableViewCell*)[tableView dequeueReusableCellWithIdentifier:@"main"];
    if(!cell) {
        cell = [[VideoTableViewCell alloc] init];
    }
    return nil;
}

- (void)tableView:(UITableView *)tableView willDisplayCell:(UITableViewCell *)cell forRowAtIndexPath:(NSIndexPath *)indexPath {
    Video *video = [self.videoList objectAtIndex:indexPath.row];
    VideoTableViewCell *vidCell = (VideoTableViewCell*)cell;
    [vidCell updateUI:video];
}

- (void)tableView: (UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    Video *video = [self.videoList objectAtIndex:indexPath.row];
    
    [self performSegueWithIdentifier:@"videoVC" sender:video];
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return self.videoList.count;
}

- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    VideoViewController *vc = (VideoViewController*)segue.destinationViewController;
    Video *video = (Video*)sender;
    
    vc.video = video;
}


@end
