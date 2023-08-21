const express = require('express');
const app = express();
const path = require('path');

//set up static and middleware
app.use(express.static('./repeat_all_self/navbar-app/public'))// static(path_to_directory_where_static_resourses_stored) help to establish connection between static resoursec (logo, img, script_logig.js) with html

// app.get("/", (req, res) => {
//     res.status(264).sendFile(path.resolve(__dirname, './navbar-app/index.html'));// we present response as html page, which in our project, and path to index.html file we define with help of path module+resolve() method
// })
//since out index.html is static as well we can just copy file to public folder and all will be works as well. index.html is ALWAYS A ROOT, SERVER SERVE IT AS ROOT BY DEFAULT. SO WHEN USER HIT SERVER INDEX.HTML FILE WILL LOAD BY DEFAULT => SINCE INDEX.HTML HAS ALL THE PATHS other public assets will bu put in their place

app.all('*', (req, res) =>{// for rest of requests we use 404 status code with relevanr response
    res.status(404).send("Resource not FOUND")
})

app.listen(5000, ()=> {
    console.log("Server listening on port : 5000...")
});


