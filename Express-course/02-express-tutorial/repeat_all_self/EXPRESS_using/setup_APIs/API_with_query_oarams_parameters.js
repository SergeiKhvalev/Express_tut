const express = require('express');
const app = express();
const { products } = require('./data.js');// we import needed json from externl file

app.get("/", (req, res) => {
    res.send('<h1> Home Page</h1> <a href="/api/products">Product</a>');// we create link to another page on our Home page
})
app.get("/api/products", (req, res) => { // we do not want put all info about all products on that page. So we iterate tghrough array of objects and collect data we need present on that page
    const newProduct = products.map((product)=> {
        return {id:product.id, name:product.name, image :product.image};// we just map given array of objects => extract certain ptoperties to display on that page
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

//to select single product

//first approach - works, but not effective, because if we  have thousends items => hard to setup rout for each item
// app.get("/api/products/1", (req, res)=>{
//     const productIdOne = products.find((product) => product.id === 1);
//     res.json(productIdOne)
// })

//dynamic approach to get single item by id (rout parameter)
// this is API with params parameters > which using to get single data
app.get("/api/products/:ASSIDSUCA", (req, res) => { // place :anyName to define item (rout parameters)
    // console.log(req);
    // console.log(req.params)// now we can find :ASSIDSUCA in oour req object
    const {ASSIDSUCA} = req.params; // but remember it return as string => need convert in to Number If id in object setup as number
    const singleProduct = products.find((product) => product.id === Number(ASSIDSUCA))
    //if user try to find produnct which not exist (product id not exist) => handle that situation with if statment

    if(!singleProduct){
        return res.status(404).send("Product whith provided ID not exist")
    } else{
        return res.json(singleProduct);
    }
})

app.get("/api/products/:ASSIDSUCA/reviews/:reviewIDNOW", (req, res)=>{// we also add /:reviewIDNOW to params of req object
    console.log(req.params);
    res.send("Hello Blyat");

})

//query parameters (also called url parameters) => usully used to filter data base, results
// http://localhost:5000/api/v1/query?name=Sergey&id=99 (example of search url for server)
app.get("/api/v1/query", (req, res)=>{// in URL after query need use ?
    console.log(req.query);// will see in console { name: 'Sergey', id: '99' } or other search parameters and base on that we can create our filter for data
    const {search, limit} =req.query;// to work with query params// if user will not prowide one of query param => return all products
    let sortedProducts = [...products];// we create new array of products
    if(search){// we filter by search query param
        sortedProducts = sortedProducts.filter((product) =>{
            return product.name.startsWith(search)
        })
    }
    if(limit){
        sortedProducts = sortedProducts.slice(0, Number(limit));
    }
    if(sortedProducts.length < 1){// that iff need if search was correct, but there is no such kind of product
        // res.status(200).send("no products matched your request")
        // that approach also using
        return res.status(200).json({sucess: true, data:[]});
    }
    return res.status(200).json(sortedProducts)

})



app.listen(5000, () =>{
    console.log("Server listening on port : 5000");
})