const express = require('express');
const app = express();
/* express methods which using most
app.get
app.post
app.put
app.delete
app.all
app.use
app.listen
 */
// main idea and generall approach in express and http module same, syntax also very similar

app.get("/", (req, res) =>{
    res.status(200).send("This is home page, guys")
})

app.get("/about", (req, res) =>{
    res.status(200).send("This is our history")
})

app.listen(5000,()=>{
    console.log("Server is listening on port: 5000")
})
// for else => resourses which not exit we can use default message: Cannot GET /about/ss/ 404code
//or
app.all('*', (req, res)=>{ // app.all => help handle rest of requestes, which not described above
    res.status(404).send('<h1> resourse not found </h1>')
})