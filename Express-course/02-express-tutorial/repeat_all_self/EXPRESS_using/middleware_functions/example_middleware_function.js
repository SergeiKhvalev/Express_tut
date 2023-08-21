v/*EXPRESS meddleware - are functions Middleware in Express.js refers to a series of functions that are executed in the order they are defined when an HTTP request is made to your Express application. Middleware functions have access to the request and response objects as well as a special function called next(). They can perform various tasks like modifying request or response objects, performing authentication, logging, error handling, and more.

    Middleware functions are typically used to add functionality to your Express application without cluttering up your route handlers with repetitive code. They allow you to modularize your application's logic and separate concerns.

 */

// basic structure of middleware express functions: request => middleware logic (functions) => response
const express = require('express');
const app = express();

//in example below we use middleware function to write into logger some basic info
// example of middleware function
const logger = (req, res, next)=>{// as we see middleware function has access to req and res objects// express automatically pass it in middleware function
    const methodAPI = req.method;
    const url = req.url;
    const timeOfReq = new Date().getFullYear();
    console.log(`Logger: method ${methodAPI}, URL: ${url}, date of req: ${timeOfReq}`);
    //res.send("Middleware")// this way we terminate cicle and send that responce
    next(); // if we pass middleware in APIrequests we must use next() function in middleware
}

app.get('/', logger, (req, res) => {// we pass referance to middleware function (logger) in API req
    res.send("Hello there. This is home page, and I am listening you");
})

app.get('/about',logger, (req, res) => {
    res.send("You read about us. Wonderfully story, isn`t it?")
})

app.listen(5000, ()=>{
    console.log("Server listening on port : 5000....")
});

// not good approach because if we have 1000 of API we have to repeat loger in each res. => better to set up middleware function