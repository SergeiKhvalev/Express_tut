const express = require('express');
const app = express();

app.get("/", (req, res) => { // get("/") we can consider it as basic request to server and what within callback function it is res. ... it is response on browser side, which came from server
    res.json([{name: 'Sergei Khvalev'}, {name: 'Eva Khvaleva'}, {name: 'Ekaterina Starovoitova'}]);// we present data as json. And res.json() - will send back that respovse (display it on browser)
})

app.listen(5000, () =>{
    console.log("Server listening on port : 5000");
})