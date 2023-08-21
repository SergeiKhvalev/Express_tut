const express = require('express');
const app = express();
const { products } = require('./data.js');// we import needed json from externl file

app.get("/", (req, res) => {
    res.setHeader('Content-Type', 'application/json')
    res.json(products);
})

app.listen(5000, () =>{
    console.log("Server listening on port : 5000");
})