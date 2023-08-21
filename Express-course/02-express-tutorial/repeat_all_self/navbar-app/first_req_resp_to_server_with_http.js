const http = require('http');


// general idea of http request-response operation with server
const server = http.createServer((req, resp) =>{ // resp is object which we use to describe what response (what we should see in browser, when some body send request) => we work on server responses
    console.log('user hit the server');
    //we describe request below
    //console.log(req.method);// see it will be GET method by default
    const url = req.url;// request GET to homePage
    //console.log(req.url)// path to resource => / means home page
    if (url === '/'){
        //we deal witn response below
        resp.writeHead(200, {'content-type': 'text/html'});// we add status code to let browser know what happening // we write headers for response. Just tell server what we expect in request
        resp.write('<h1> home page </h1>'); // that type of body we send body inside response
        resp.end(); // or we can use that syntax resp.end(<h1> home page </h1>) must be include in each request// This method signals to the server that all of the response headers and body have been sent; that server should consider this message complete. The method, response.end(), MUST be called on each response.// because we ask specific content-type => server will return html
    }
    else if (url ==="/about"){
        resp.writeHead(200, {'content-type': 'text/html'});
        resp.write('<h1> ABOUT PAGE </h1>');
        resp.end();
    }
    else {
        resp.writeHead(404, {'content-type': 'text/html'});
        resp.write("<h2>Oppss...page goes not exist....</h2>");
        resp.end();
    }
});

server.listen(5000);

