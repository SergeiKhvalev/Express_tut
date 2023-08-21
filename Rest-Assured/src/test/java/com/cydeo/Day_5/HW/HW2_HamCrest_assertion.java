package com.cydeo.Day_5.HW;

import com.cydeo.utility.ZipCodeTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.IconUIResource;

import static org.hamcrest.Matchers.*;

public class HW2_HamCrest_assertion extends ZipCodeTestBase {
    /*
TASK 3
Given Accept application/json
And path state is va
And path city is fairfax
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And payload should contains following information
 country abbreviation is US
 country United States
 place name Fairfax
 each places must contains fairfax as a value
 each post code must start with 22
 */
    @Test
            public void Test1(){
     Response response = RestAssured.given().accept(ContentType.JSON).pathParam("state", "va").and().pathParam("city", "fairfax").when().get("/us/{state}/{city}").
                then().
                statusCode(200).
                contentType("application/json").
                body("'country abbreviation'", Matchers.equalTo("US"), "country", Matchers.is("United States"),"'place name'", Matchers.equalTo("Fairfax"), "'place name'", Matchers.is("Fairfax"), "places.'place name'", Matchers.everyItem(containsString("Fairfax")), "places.'post code'", Matchers.everyItem(startsWith("22"))).extract().response();

        System.out.println("List of place names = " + response.path("places.'place name'"));


    }



}
