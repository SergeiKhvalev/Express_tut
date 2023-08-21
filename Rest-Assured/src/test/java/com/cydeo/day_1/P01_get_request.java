package com.cydeo.day_1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore


public class P01_get_request {

    String URL = "http://54.167.63.56:8000/api/spartans";

    /**
     * When users end request to /api/spartans endpoint
     * Then user should be able see status code is 200
     * And Print out response body into screen
     */


    @Test
    public void simpleGetRequest() {
        //send request to URL and get response as Response interface
        //Respons interface from restassured, whit keep response from API
        //get(url) help to store response object
        Response response = RestAssured.get(URL);

        //Both same no difference -> gonna use for assertion
        System.out.println("Status code = " + response.getStatusCode());
        System.out.println("Status code = " + response.statusCode());


        //it gives all status line
        System.out.println("response status line = " + response.statusLine());


        int actualStatusCode = response.statusCode();


        // veify code Status
        Assertions.assertEquals(200, actualStatusCode);

        // how to display whole response in console
        System.out.println(response.prettyPrint());


        System.out.println("----------------asString--------------------------------");
        System.out.println("body of response as String = " + response.body().asString());

        // find total number of spartans


    }

    String baseUrl = "http://54.167.63.56:8000";


    // http://54.167.63.56:8000/api/spartans"

    @Test
    public void Test5() {
        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 500).when().get(baseUrl + "/api/spartans/{id}");

        System.out.println(response.statusCode());

        // check status code
        Assertions.assertEquals(404, response.statusCode());


        // check body type
        Assertions.assertEquals("application/json", response.contentType());

        System.out.println(response.contentType());

        //check "Allen" name in body

        Assertions.assertTrue(response.body().asString().contains("Not Found"));

response.prettyPrint();
    }

}
