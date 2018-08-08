//
//  VideoViewController.m
//  DevslopesTutorial
//
//  Created by Tim Serio on 9/25/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

#import "VideoViewController.h"
#import "Video.h"

@interface VideoViewController ()
@property (weak, nonatomic) IBOutlet UIWebView *webView;
@property (weak, nonatomic) IBOutlet UILabel *titleLabel;
@property (weak, nonatomic) IBOutlet UILabel *descLabel;

@end

@implementation VideoViewController
- (IBAction)donePressed:(id)sender {
    [self dismissViewControllerAnimated:true completion:nil];
}

- (void)viewDidLoad {
    [super viewDidLoad];
    
    self.webView.delegate = self;
    self.titleLabel.text = self.video.videoTitle;
    self.descLabel.text = self.video.videoDescription;
    [self.webView loadHTMLString:self.video.videoIFrame baseURL:nil];
}

- (void)webViewDidFinishLoad:(UIWebView *)webView {
    NSString *css = @".container { position: relative; width: 100%; height: 0; padding-bottom: 56.25%;} .video { position: absolute; top: 0; left: 0; width: 100%; height: 100%;}";
    // \ in a long string with multiple quotation marks is used to escape the string
    NSString* js = [NSString stringWithFormat:
                    @"var styleNode = document.createElement('style');\n"
                    "styleNode.type = \"text/css\";\n"
                    "var styleText = document.createTextNode('%@');\n"
                    "styleNode.appendChild(styleText);\n"
                    "document.getElementsByTagName('head')[0].appendChild(styleNode);\n",css];
    NSLog(@"js:\n%@",js);
    [self.webView stringByEvaluatingJavaScriptFromString:js];
}


@end
