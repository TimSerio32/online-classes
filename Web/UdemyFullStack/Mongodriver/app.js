const MongoClient = require('mongodb').MongoClient;

const url = 'mongodb://localhost:27017';

MongoClient.connect(url, function(err, client) {
	
	if(err) {
		throw err;
	}
	var db = client.db('myproject');

	console.log('Connected to mongodb');

	/* InsertDocument(db, function() {
		client.close();
	}); 

	InsertDocuments(db, function() {
		client.close();
	}); */
	
	FindDocuments(db, function() {
		client.close();
	});

	/* QueryDocuments(db, function() {
		client.close();
	});

	UpdateDocument(db, function() {
		client.close();
	});

	RemoveDocument(db, function() {
		client.close();
	}); */

	/* db.collection('users').insert({
		name: 'Tim Serio',
		email: 'tim@test.com'
	}, function(err, result) {
		if(err) {
			throw err;
		}	
		console.log('Inserted Document');
		console.log(result);
		client.close();
	}); */
});

const InsertDocument = function(db, callback) {
	const collection = db.collection('users');

	collection.insert({
		name: 'Tim Serio',
		email: 'tim@test.com'
	}, function(err, result) {
		if(err) {
			throw err;
		}
		console.log('Insert Document');
		console.log(result);
		callback(result);
	});
}

const InsertDocuments = function(db, callback) {
	const collection = db.collection('users');

	collection.insertMany([
		{
			name: 'John Doe',
			email: 'john@test.com'
		}, {
			name: 'Sam Smith',
			email: 'ssmith@test.com'
		}, {
			name: 'Jose Gomez',
			email: 'jgomez@test.com'
		}
	], function(err, result) {
		if(err) {
			throw err;
		}
		console.log('Inserted ' + result.ops.length + ' documents');
		callback(result);
	});
}

const FindDocuments = function(db, callback) {
	const collection = db.collection('users');

	collection.find({}).toArray(function(err, docs) {
		if(err) {
			throw err;
		}
		console.log('Found the following records:');
		console.log(docs);
		callback(docs);
	});
}

const QueryDocuments = function(db, callback) {
	const collection = db.collection('users');

	collection.find({ 'name' : 'John Doe' }).toArray(function(err, docs) {
		if(err) {
			throw err;
		}
		console.log('Found the following records:');
		console.log(docs);
		callback(docs);
	});
}

const UpdateDocument = function(db, callback) {
	const collection = db.collection('users');

	// without $set, this would set the whole record to be just the email
	collection.updateOne({ name: 'John Doe' }, {$set: {email: 'john@something.com'}}, function(err, result) {
		if(err) {
			throw err;
		}
		console.log('Updated Document');
		callback(result);
	});
}

const RemoveDocument = function(db, callback) {
	const collection = db.collection('users');

	// without $set, this would set the whole record to be just the email
	collection.deleteOne({ name: 'John Doe' }, function(err, result) {
		if(err) {
			throw err;
		}
		console.log('Removed Document');
		console.log(result);
		callback(result);
	});
}