const http = require('http');
const {readFileSync} = require('fs')

//const homePageName = readFileSync("./repeat_all_self/navbar-app/index.html"); // we read external file index.html => to pass in in our response
const homePageName = readFileSync("./navbar-app/index.html");
const homePageStyle = readFileSync("./navbar-app/styles.css");
const homePageImage = readFileSync("./navbar-app/logo.svg");
const homePageLogic = readFileSync("./navbar-app/browser-app.js");


// general idea of http request-response operation with server
const server = http.createServer((req, resp) =>{ // resp is object which we use to describe what response (what we should see in browser, when some body send request) => we work on server responses
    console.log('user hit the server');
    const url = req.url;
    console.log(url);
    if (url === '/'){
        //we deal witn response below
        resp.writeHead(200, {'content-type': 'text/html'});
        resp.write(homePageName); //we read content of index.html => pass ti to web page
        resp.end();
    }
    else if (url ==="/about"){
        resp.writeHead(200, {'content-type': 'text/html'});
        resp.write('<h1> ABOUT PAGE </h1>');
        resp.end();
    }
    // for styles.css
    else if (url ==="/styles.css"){
        resp.writeHead(200, {'content-type': 'text/css'});
        resp.write(homePageStyle);
        resp.end();
    }
    // for logo/images
    else if (url ==="/logo.svg"){
        resp.writeHead(200, {'content-type': 'image/svg+xml'});
        resp.write(homePageImage);
        resp.end();
    }
    // js logic
    else if (url ==="/browser-app.js"){
        resp.writeHead(200, {'content-type': 'text/javascript'});
        resp.write(homePageLogic);
        resp.end();
    }
    else {
        resp.writeHead(404, {'content-type': 'text/html'});
        resp.write("<h2>Oppss...page goes not exist....</h2>");
        resp.end();
    }
});

server.listen(5000);

