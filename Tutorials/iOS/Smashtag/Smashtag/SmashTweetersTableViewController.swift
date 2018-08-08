//
//  SmashTweetersTableViewController.swift
//  Smashtag
//
//  Created by Tim Serio on 6/26/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit

class SmashTweetersTableViewController: FetchedResultsTableViewController {

    var mention: String? {
        didSet {
            updateUI()
        }
    }
    
    var container: NSPersistentContainer? = (UIApplication.shared.delegate as? AppDelegate?.persistentContainer) {
        didSet{
            updateUI()
        }
    }

    var fetchedResultsController: NSFetchedResultsController<TwitterUser>?
    
    private func updateUI() {
        if let context = container?.viewContext, mention != nil {
            let request: NSFetchRequest<TwitterUser> = TwitterUser.fetchRequest()
            request.sortDescriptors = [NSSortDescriptor(key: handle, ascending: true, selector: #selector(NSString.localizedCaseInsensitiveCompare(_:))]
            request.predicate = NSPredicate(format: "any Tweets.text contains[c] %@", mention!)
            fetchedResultsController = NSFetchedResultsController<TwitterUser>(
                fetchRequest: request,
                mangedObjectContext: context,
                sectionNameKeyPath: nil,
                cacheName: nil
            )
        }
        try? fetchedResultsController?.performFetch()
        tableView.reloadData()
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell{
        let cell = tableView.dequeueReusableCell(withIdentifier: "TwitterUserCell", for: indexPath)
        if let twitterUser = fetchedResultsController!.object(at: indexPath){
            cell.textLabel?.text = twitterUser.handle
            let tweetCount = tweetCountWithMentionBy(twitterUser)
            cell.detailTextLabel?.text = "\(tweetCount) tweet \(tweetCount == 1 ? "" : "s")"
        }
        return cell
    }
    
    private func tweetCountWithMentionBy(_ twitterUser: TwitterUser) -> Int {
        let request: NSFetchRequest<Tweet> = Tweet.fetchRequest()
        request.predicate = NSPredicate(format: "text contains[c] %@ and tweeter = %@", mention!, twitterUser)
        return (try? twitterUser.managedObjectContext!.count(for: request)) ?? 0
    }
}