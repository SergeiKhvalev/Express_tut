package com.cydeo.day_12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P05_DDT_WithCSVFileSource {

    // DDT test JUNIT5 with @CsvFileSource - good fit for dig data which need pass to test

    @ParameterizedTest
@CsvFileSource(resources = "/match.csv", numLinesToSkip = 1) // we create that file.csv usually under resources folder, put needed data to that file// path defined automatically/  numLinesToSkip = ? we can use to skip needed line from file.cvs. In our test need skip first line (there is String values, which help us organized file.cvs better)
public void test1(int num1, int num2, int total){
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("Total = " + total);

    }
/**
 *    // Write a parameterized test for this request
 *     // Get the data from csv source called as --> zipcode.csv
 *     // state , city , numberOfPlace
 *     // GET https://api.zippopotam.us/us/{state}/{city}
 *     // After getting response numberOfPlaces needs to be same
 *
 *     state , city , numberOfPlace
 *     NY,New York,166
 *     CO,Denver,76
 *     VA,Fairfax,10
 *     MA,Boston,56
 *     MD,Annapolis,9
 */
@ParameterizedTest
@CsvFileSource(resources = "/zipcode.csv", numLinesToSkip = 1) // we create that file.csv usually under resources folder, put needed data to that file// path defined automatically/  numLinesToSkip = ? we can use to skip needed line from file.cvs. In our test need skip first line (there is String values, which help us organized file.cvs better)
public void test2(String state, String city, int zipCount){
    given()
            .baseUri("https://api.zippopotam.us")
            .pathParam("state", state)
            .pathParam("city", city)
            .when()
            .get("/us/{state}/{city}")
            .then()
            .statusCode(200)
            .body("places", hasSize(zipCount)); // returned array, we define name of Array - "places", with hasSize(zipCode), we check size of Array




}


}
