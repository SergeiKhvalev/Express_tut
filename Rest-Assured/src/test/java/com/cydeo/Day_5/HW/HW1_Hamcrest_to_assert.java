package com.cydeo.Day_5.HW;

import com.cydeo.utility.ZipCodeTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class HW1_Hamcrest_to_assert extends ZipCodeTestBase {
   /* TASK 1
    Given Accept application/json
    And path zipcode is 22031
    When I send a GET request to /us endpoint
    Then status code must be 200
    And content type must be application/json
    And Server header is cloudflare
   ??? And Report-To header exists
    And body should contains following information
    post code is 22031
    country is United States
    country abbreviation is US
    place name is Fairfax
    state is Virginia
    latitude is 38.8604
            */

    @Test
    public void Test1() {

        RestAssured.given().accept(ContentType.JSON).pathParam("id", 22031).when().get("/us/{id}").prettyPeek().
                then().statusCode(200).contentType("application/json").header("Server", "cloudflare").
                body("'post code'", Matchers.is("22031"), "country", Matchers.equalTo("United States"), "'country abbreviation'", Matchers.is("US"), "places[0].'place name'", Matchers.equalTo("Fairfax"), "places[0].state", Matchers.is("Virginia"), "places[0].latitude", Matchers.equalTo("38.8604"));

    }

}
