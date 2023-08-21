const express = require('express');
const app = express();
const logger = require("./middleware_functions.js")// we export logger function from external file => aur code cleaner


// it is best practice to keep logger function in seprate file (middleware_functions.js)
// we do not want add middleware function to each rout manually => better approach use special function for that

app.use(logger); // that method add middleware function to each rout. This is a method that is used to add middleware functions to your application's request handling pipeline. You can add multiple middleware functions using multiple app.use() calls, and they will be executed in the order they are added. app.use() best practice to place it above each API requests in order to pass it to each rout. We also can pass path in app.use("/api", logger) => in that case middleware function will be added for any routh which has /api.....

app.get("/", (req, res)=>{
    res.send("Home page");
})

app.get("/about", (req, res)=>{
    res.send("About page")
})

app.get("/api/products", (req, res)=>{
    res.send("Products page")
})

app.get("/api/contacts", (req, res)=>{
    res.send("Contacts page")
})





app.listen(5000, ()=>{
    console.log("Server listening on port : 5000")
})