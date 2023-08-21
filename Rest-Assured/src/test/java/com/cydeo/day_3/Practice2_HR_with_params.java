package com.cydeo.day_3;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*; // static import RestAssured


public class Practice2_HR_with_params extends HrTestBase {

/*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */


    @DisplayName("Get request/countries with regionID")
    @Test
    public void Test1() {

        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}").when().get("/countries");

        // response.prettyPrint();

        // status code
        Assertions.assertEquals(200, response.statusCode());
        // check body format
        Assertions.assertEquals("application/json", response.contentType());

        // check if body contains "United States of America"
        Assertions.assertTrue(response.body().asString().contains("United States of America"));


    }
}
