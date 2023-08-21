const express = require('express');
const app = express();
const logger = require("./middleware_functions.js")// we export logger function from external file => aur code cleaner


// it is best practice to keep logger function in seprate file (middleware_functions.js)
// we do not want add middleware function to each rout manually => better approach use special function for that

app.get("/", logger, (req, res)=>{
    res.send("Home page");
})

app.get("/about", logger, (req, res)=>{
    res.send("About page")
})

app.listen(5000, ()=>{
    console.log("Server listening on port : 5000")
})