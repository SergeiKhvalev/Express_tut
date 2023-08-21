package com.cydeo.day_3;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore


public class Practice1_pathParam extends SpatanTestBase {

    @DisplayName("Get spartan /api/spartans/{id} with path param {id}  24")
    @Test
    public void Test1() {
/*
/*   Given accept type is Json
          And Id parameter value is 24
          When user sends GET request to /api/spartans/{id}
          Then response status code should be 200
          And response content-type: application/json
          And "Blythe" should be in response payload(body)
       */

        // Manual test passed

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 24).when().get("/api/spartans/{id}"); // No RestAssured reference because of static import/ Also pathParam("id", 24) where "id" is key, 24 - value
        response.prettyPrint();

        // pathParam("id", 24) - method which help to pass path parameter in our request, in order to get in response certain data
        // accept(ContentType.JSON) - accept() define header in response - to get body in certain format (JSON, XML, so on)

        // verify code status
        int statusCode = response.statusCode();
        System.out.println(statusCode);
        assertEquals(200, response.statusCode()); //No Assertion work because of static import
 // same result - HttpStatus.SC_OK - can use to check code status - return String
        System.out.println(HttpStatus.SC_OK);
        assertEquals(HttpStatus.SC_OK, response.statusCode());//No Assertion work because of static import

        // CHeck body
        System.out.println(response.contentType());
        assertEquals("application/json", response.contentType());//No Assertion work because of static import

        // Check body contain
        assertTrue(response.body().asString().contains("Julio"));//No Assertion work because of static import

    }
    /*
            TASK
            Given accept type is Json
            And Id parameter value is 500
            When user sends GET request to /api/spartans/{id}
            Then response status code should be 404
            And response content-type: application/json
            And "Not Found" message should be in response payload
         */

    @DisplayName("QUERY PARAM")

    @Test
    public void Test2() {
        Response response = given().accept(ContentType.JSON).pathParam("id", 500).when().get("/api/spartans/{id}");
// when(), and(), given(), then() - Syntactic sugar - to increase readability for non-technical people (Gherkin language)

        response.prettyPrint();
        // code status checking

       assertEquals(404, response.statusCode());

        // check body format
        assertEquals("application/json", response.contentType());

        // check body
        assertTrue(response.body().asString().contains("Not Found"));

        String errorValue = response.path("error");
        System.out.println(errorValue);
        assertEquals("Not Found", errorValue);

        String path = response.path("path");
        System.out.println(path);
        assertEquals("/api/spartans/500", path);

    }
    /*
    Given Accept type is Json
    And query parameter values are:
    gender|Female
    nameContains|e
    When user sends GET request to /api/spartans/search
    Then response status code should be 200
    And response content-type: application/json
    And "Female" should be in response payload
    And "Janette" should be in response payload


     */

    @DisplayName("QUERY PARAM")
    @Test
    public void Test3() {
        //manual test passed

        Response response = RestAssured.given().accept(ContentType.JSON).queryParam("gender", "Female").and().queryParam("nameContains", "e").when().get("/api/spartans/search");

        // status code
        Assertions.assertEquals(200, response.statusCode());

        // content type
        Assertions.assertEquals("application/json", response.contentType());

        //check body contain Female
        Assertions.assertTrue(response.body().asString().contains("Female"));

        //check body contain Janette
        Assertions.assertTrue(response.body().asString().contains("Janette"));


    }


    @DisplayName("QUERY PARAM with MAP AS QUERY PARAM")
    @Test
    public void Test4() {
        //manual test passed

        Map<String, Object> queryMAp = new HashMap<>();
        queryMAp.put("gender", "Female");
        queryMAp.put("nameContains", "e");

        Response response = RestAssured.given().accept(ContentType.JSON).queryParams(queryMAp).when().get("/api/spartans/search");

        // status code
        Assertions.assertEquals(200, response.statusCode());

        // content type
        Assertions.assertEquals("application/json", response.contentType());

        //check body contain Female
        Assertions.assertTrue(response.body().asString().contains("Female"));

        //check body contain Janette
        Assertions.assertTrue(response.body().asString().contains("Janette"));

    }











}
