const express = require('express');
const app = express();
const { products } = require('./data.js');// we import needed json from externl file

app.get("/", (req, res) => {
    res.send('<h1> Home Page</h1> <a href="/api/products">Product</a>');// we create link to another page on our Home page
})
app.get("/api/products", (req, res) => { // we do not want put all info about all products on that page. So we iterate tghrough array of objects and collect data we need present on that page
    const newProduct = products.map((product)=> {
        return [product.id, product.name, product.image];// we just map given array of objects => extract certain ptoperties to display on that page
        /*
valid syntax as well
const newProduct = products.map((product)=>{
    const {id, name, image} = product // using destructiring
    return {id, name, image}
    })
 */
    })
    res.json(newProduct)// response on browser will be presented in that shape
})

app.listen(5000, () =>{
    console.log("Server listening on port : 5000");
})