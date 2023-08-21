package com.cydeo.day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore

public class Practice_NegativeSpartan_test {
   // String baseURL = "http://54.167.63.56:8000";

    @BeforeAll // like @beforeclass
public static void init(){
        RestAssured.baseURI="http://54.167.63.56:8000"; // - default URL. if not defined another baseUrl. Default URL will be used

    }


/*
        Given Accept type application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8;
    */

@DisplayName("Negative_Spartan")
    @Test
    public void negativeTesting(){

       Response response = given().accept(ContentType.XML).get("/api/spartans/10"); // static import RestAssured
       assertEquals(406, response.statusCode());// static import from RestAssured

    assertEquals("application/xml;charset=UTF-8", response.contentType());

    }




}
