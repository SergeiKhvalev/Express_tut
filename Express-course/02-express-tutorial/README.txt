EXPRESS module build on top of NODEJS library and especially on its http module

Express.js:

Express.js is a popular web application framework for Node.js, a runtime environment that allows you to run JavaScript on the server side. Express.js simplifies the process of building web applications and APIs by providing a set of tools and utilities for handling routes, middleware, templates, and more. It's often used to create server-side applications, RESTful APIs, and other types of web services.
Key features of Express.js include:
Routing: Express allows you to define routes for different URL paths and HTTP methods (GET, POST, PUT, DELETE, etc.), making it easy to handle different types of requests.
Middleware: Middleware functions can be used to perform tasks like request preprocessing, authentication, logging, and more. Express allows you to define and use middleware in a modular way.
Templates: Although not built into Express itself, it's common to use template engines like EJS, Pug (formerly known as Jade), or Handlebars to generate dynamic HTML content.
Static File Serving: Express makes it simple to serve static files (HTML, CSS, JavaScript, images, etc.) from a designated directory.
Error Handling: Express provides mechanisms for handling errors and responding with appropriate error messages to clients.
Integration with Other Libraries: Express can be combined with various libraries and tools to enhance functionality, such as database libraries (MongoDB, MySQL, etc.), authentication libraries (Passport.js), and more.

to install EXPRESS packege to ptoject
npm install express --save
//to install specific express version
npm install express@4.17.1 --save

Using Express we have 2 oprions:
API(setup API to interact with data)          VS          SSR
API-JSON                                                  SSR-TEMPLATE
SEND DATA(to send data use JSON)                          SEND TEMPLATE
RES.JSON()(to send back response)                         RES.RENDER()


EXPRESS meddleware - are functions Middleware in Express.js refers to a series of functions that are executed in the order they are defined when an HTTP request is made to your Express application. Middleware functions have access to the request and response objects as well as a special function called next(). They can perform various tasks like modifying request or response objects, performing authentication, logging, error handling, and more.

 Middleware functions are typically used to add functionality to your Express application without cluttering up your route handlers with repetitive code. They allow you to modularize your application's logic and separate concerns.