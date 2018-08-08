// requires the module 'http'
var http = require('http');

// create an HTTP server
var server = http.createServer();

// handler for requests
function handler(req, res) {
	res.writeHead(200, {'Content-Type': 'text/plain'});
	res.write('hello, world');
	res.end();
	console.log(req.url);
}

// handle requests with handler
server.on('request', handler);

// listen on part 1337
server.listen(1337);
