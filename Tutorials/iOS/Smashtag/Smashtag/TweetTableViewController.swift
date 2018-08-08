//
//  TweetTableViewController.swift
//  Smashtag
//
//  Created by Tim Serio on 6/19/17.
//  Copyright © 2017 Tim Serio. All rights reserved.
//

import UIKit
import MyTwitter

class TweetTableViewController: UITableViewController, UITextFieldDelegate {

   /* private var tweets = [Array<Tweet>](){
        didSet{
            print(tweets)
        }
    }*/
    
    private var tweets: [[MyTwitter.Tweet]] = [[]]
    
    var searchText: String? {
        //they set the search text, I will show the text
        didSet {
            searchTextField?.text = searchText
            searchTextField?.resignFirstResponder()
            lastTwitterRequest = nil
            tweets.removeAll()
            tableView.reloadData()
            searchForTweets()
            title = searchText
        }
        
    }
    
    private var lastTwitterRequest: MyTwitter.Request?
    
    /*private func twitterRequest() -> MyTwitter.Request? {
        if let query = searchText, !query.isEmpty {
            return MyTwitter.Request(search: query, count: 100)
        }
        return nil
    }*/
    
    func insertTweets(_ newTweets: [MyTwitter.Tweet]){
        self.tweets.insert(newTweets, at:0)
        self.tableView.insertSections([0], with: .fade)
    }
    
    private func twitterRequest() -> MyTwitter.Request? {
        if let query = searchText, !query.isEmpty {
            return MyTwitter.Request(search: "\(query) -filter:safe -filter:retweets", count: 100)
        }
        return nil
    }
    
    private var lastTwitterRquest: MyTwitter.Request?
    
    /*private func searchForTweets() {
        if let request = twitterRequest() {
            lastTwitterRequest = request
            request.fetchTweets { [weak self] newTweets in
                DispatchQueue.main.async {
                    if request == self?.lastTwitterRequest {
                        self?.tweets.insert(newTweets, at: 0)
                        self?.tableView.insertSections([0], with: .fade)
                    }
                }
            }
        }
    }*/
    
    private func searchForTweets() {
        if let request = lastTwitterRequest?.newer ?? twitterRequest() {
            lastTwitterRequest = request
            request.fetchTweets { [weak self] newTweets in
                DispatchQueue.main.async {
                    if request == self?.lastTwitterRequest {
                        self?.insertTweets(newTweets)
                    }
                    self?.refreshControl?.endRefreshing()
                }
            }
        } else {
            //self?.refreshControl?.endRefreshing()
            self.refreshControl?.endRefreshing()
        }
    }
    
    @IBAction func refresh(_ sender: Any) {
        searchForTweets()
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.estimatedRowHeight = tableView.rowHeight
        tableView.rowHeight = UITableViewAutomaticDimension
    }
    
    @IBOutlet var searchTextField: UITextField! {
        didSet {
            searchTextField.delegate = self
        }
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        if textField == searchTextField {
            searchText = searchTextField.text
        }
        return true
    }
    
    // MARK: - UITableViewDataSource

    override func numberOfSections(in tableView: UITableView) -> Int {
        return tweets.count
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return tweets[section].count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Tweet", for: indexPath)

        // Configure the cell...
        let tweet: MyTwitter.Tweet = tweets[indexPath.section][indexPath.row]
        //cell.textLabel?.text = tweet.text
        //cell.detailTextLabel?.text = tweet.user.name
        if let tweetCell = cell as? TweetTableViewCell {
            tweetCell.tweet = tweet
        }

        return cell
    }
    
    override func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "\(tweets.count - section)"
    }


}
