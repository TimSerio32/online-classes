//
//  SmashTweetTableViewController.swift
//  Smashtag
//
//  Created by Tim Serio on 6/26/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import MyTwitter
import CoreData


class SmashTweetTableViewController: TweetTableViewController {
    
    var container: NSPersistentContainer? = (UIApplication.shared.delegate as? AppDelegate)?.persistentContainer
    
    override func insertTweets(_ newTweets: [MyTwitter.Tweet]){
        super.insertTweets(newTweets)
        updateDatabase(with: newTweets)
    }
    
    private func updateDatabase(with tweets: [MyTwitter.Tweet]){
        container?.performBackgroundTask {context in
            for twitterInfo in tweets {
                _ = try? Tweet.findOrCreateTweet(matching: twitterInfo, in: context)
            }
            try? context.save()
        }
    }
    
    private func printDatabaseStatistics() {
        if let context = container?.viewContext {
            context.perform {
                let request: NSFetchRequest<Tweet> = Tweet.fetchRequest()
                if let tweetCount = (try? context.fetch(request))?.count {
                    print("\(tweetCount) tweets")
                }
                if let tweeterCount = try? context.count(for: TwitterUser.fetchRequest()) {
                    print("\(tweeterCount) Twitter users")
                }
            }
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "TweetersMentioningSearchTerm" {
            if let tweetersTVC = segue.destination as? SmashTweetersTableViewController {
                tweetersTVC.mention = searchText
                tweetersTVC.container = container
            }
        }
    }
}
