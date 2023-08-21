const express = require('express');
const app = express();

const logger = (req, res, next) =>{
    const APImethod = req.method;
    const url = req.url;
    const dateOfReq = new Date().getFullYear()
    console.log(`Logger: method ${APImethod}, URL: ${url}, date of req: ${dateOfReq}`);
    next();
}

module.exports = logger