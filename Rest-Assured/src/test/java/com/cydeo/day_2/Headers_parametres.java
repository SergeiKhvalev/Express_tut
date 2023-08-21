package com.cydeo.day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Headers_parametres {

    String baseURL = "http://54.167.63.56:8000";

    /**
     * Given accept ContentTypeis aplication/json
     * Where user GET request /api/spartans endpoint
     * Then status code - 200
     * And ContanteType should be application/json
     * <p>
     * Where, Then, And, Then - filler keywords in Gherkin language
     */


    @Test
    public void getAllSpartans() {

        // to store response // chaining methods
        Response response = RestAssured.given().
                accept(ContentType.JSON). // set up of response
                        when().
                get(baseURL + "/api/spartans"); // that is response which we set up (defined contentType)

        // print whole response
        //  response.prettyPrint();

        // verify status code
        int actualStatusCode = response.statusCode();

        Assertions.assertEquals(200, actualStatusCode);

        //verify contentType
        String actualContentType = response.contentType();
        Assertions.assertEquals("application/json", actualContentType);

        // get info from header of response - works as key-value format

        // how to get header info
        String connection = response.header("Connection");
        System.out.println("connection = " + connection);


        // verify ContentType  by checking header of Response. how to get header info
        String contentType = response.header("Content-type");
        System.out.println("Content Type from response header = " + contentType);

        // can we get data header

        System.out.println(" response. header - Date" + response.header("Date"));

        //how we can verify time
        boolean isDateExist = response.headers().hasHeaderWithName("Date");

        Assertions.assertTrue(isDateExist);

    }

    /*
     * Given accept ContentTypeis aplication/json
     * Where user GET request /api/spartans endpoint
     * Then status code - 200
     * And ContanteType should be application/json
     * And response body need contains name "Fidole
     * Where, Then, And, Then - filler keywords in Gherkin language
     */

    @DisplayName("GET single Spartan")
    // make it more readable on left side of console, more distinct from name of another @tests
    @Test
    public void getOneSpartan() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(baseURL + "/api/spartans/3");

        //verify status code

        int actialStatusCode = response.statusCode();

        response.prettyPrint();

        Assertions.assertEquals(200, actialStatusCode);

        // verify contentType

        String actualContenType = response.contentType();

        Assertions.assertEquals("application/json", actualContenType);// or can use response.header("ContentType)
        // same result
        // Assertions.assertEquals(ContentType.JSON.toString(), response.header("Content-Type"));


        // verify body contain name "Fidole"

        String body = response.body().asString();
        // System.out.println(body);
        Assertions.assertTrue(body.contains("Fidole"));// this is not good approach to check if name is Fedole, here Fedal can be in gender (because we convert whole body in one String, so Fedol can be everywhere within the String. Better way is cheking by key name. We need accesse name key


        // what if we have mistype (typo) while we are getting header

        System.out.println("response.headers(response.header(keep-Alive)" + response.header("KeepAlive")); // if typo - retutn NULL

    }
    /*
        Given no headers provided
        When Users send GET request to /api/hello
        Then response status code should be 200
        And Content type header should be "text/plain;charset=UTF-8"
        And header should contain Date
        And Content-Length should be 17
        And body should be "Hello from Sparta"
    */

    @DisplayName("Get Hello Spartan")
    // make it more readable on left side of console, more distinct from name of another @tests
    @Test
    public void helloSpartan() {
        Response response = RestAssured.given().accept("text/plain;charset=UTF-8").get(baseURL + "/api/hello");
        response.prettyPrint();
        int actualStatusCode = response.statusCode();

// veify status
        Assertions.assertEquals(200, actualStatusCode);

        //verify format of body (contantType)

        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());

// verify if headers contains Data header
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));// all headers check if there is exist Data header

        // verify content-length header
        Assertions.assertEquals("17", response.header("Content-Length"));// all values in String type. Header conteans only String, all numbers in String

        // verify body
        Assertions.assertTrue(response.body().asString().contains("Hello from Sparta"));


    }


}
