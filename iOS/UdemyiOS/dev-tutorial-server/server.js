var express = require('express');
var bodyParser = require('body-parser');

var app = express();

app.all('/*', function(req, res, next) {
        res.header("Access-Control-Allow-Origin","*");
        res.header("Access-Control-Allow-Headers", "X-Requested-With", "Content-Type, Accept");
        res.header("Access-Control-Allow-Methods", "Post, GET");
        next();
});

app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: false}));

var tutorials = [
    {
        id: 1,
        title: "Android Studio Tutorial For Beginners",
        description: "Learn how to install Android Studio and then go through this tutorial and build your first Android app.",
        iframe: '<div class="container"><iframe class="video" src="https://www.youtube.com/embed/F-k5gwz_91o" frameborder="0" allowfullscreen></iframe></div>',
        thumbnail: "https://upload.wikimedia.org/wikipedia/commons/thumb/3/34/Android_Studio_icon.svg/1200px-Android_Studio_icon.svg.png"
            
    },
    {
        id: 2,
        title: "iOS App Icon Design in Photoshop",
        description: "Learn how to design iOS app icons in Photoshop.",
        iframe: '<div class="container"><iframe class="video" src="https://www.youtube.com/embed/jpRBucuml2M" frameborder="0" allowfullscreen></iframe></div>'
        //thumbnail: "http://media.idownloadblog.com/wp-content/uploads/2014/05/WWDC-2.0-for-iOS-app-icon-small.png"
                 
    }
];

var comments = [
 {  
   username: "jackd42",
   comment: "This video was really cool. Any chance you could drop what you are doing and code my problem for me?"
 }
];

//app.put('/comments', function(req, res) {
   //var someObj = req.body;
   //var theId = someObj.uniqueId;
   //// talk to database, find the record by the Id
   //// then you replace the existing record with req.body
   //res.send("Successful Update.");
//});

app.post('/comments', function(req, res) {
  var comment = req.body;
  if(comment) {
    if(comment.username && comment.comment) {
      comments.push(comment);
    } else {
      res.send("You posted invalid data");
    }
  } else {
     res.send("Your post has no body!");
  }

  console.log(comments);
  res.send("You successfully posted a comment");
}); 

app.get('/tutorials', function(req, res) {
    console.log("GET from server");
    res.send(tutorials);
});

app.listen(6069);
