package com.cydeo.day_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class HW1 {
  @BeforeAll
    public static void baseUrlSetUp(){

     RestAssured.baseURI = "http://54.167.63.56:1000/ords/hr";// - default URL. if not defined another baseUrl. Default URL will be used

     }

    /*
    - Given accept type is Json
- When users sends request to /countries/US
- Then status code is 200
- And Content - Type is application/json
- And response contains United States of America

     */

    @DisplayName("Retrieve one country")
    @Test
    public void Q1() {
        // Pre-condition manual test passed

        Response response = RestAssured.given().accept(ContentType.JSON).get("/countries/US"); // base url provided by default by baseURI from @BeforeAll

// Verify status code
        int statusCode = response.statusCode();
        System.out.println(statusCode);
        Assertions.assertEquals(200, statusCode);

        //Verification of contentType
        String expectedContentType = "application/json";

        String actualContentType = response.contentType();
        System.out.println("actual CONTENT TYPE = " + actualContentType);

        Assertions.assertEquals(expectedContentType, actualContentType);
    }


    /*
Q2:
- Given accept type is Json
- When users sends request to /employees/1
- Then status code is 404
     */

    @DisplayName("Retrieve one employee")
    @Test
    public void Q2() {
        //Manually test passed
        Response response = RestAssured.given().accept(ContentType.JSON).get("/employees/1");// base url provided by default by baseURI from @BeforeAll
        // code status verification
        int actualCodeStatus = response.getStatusCode();
        System.out.println("Coda status = " + actualCodeStatus);
        Assertions.assertEquals(404, actualCodeStatus);
    }
    /*
    Q3:
    - Given accept type is Json
    - When users sends request to /regions/1
    - Then status code is 200
    - And Content - Type is application/json
    - And response contains Europe
    - And header should contains Date
    - And Transfer-Encoding should be chunked
     */

    @DisplayName("Retrieve one region")
    @Test
    public void Q3() {
//Manually test passed

        Response response = RestAssured.get("/regions/1");// base url provided by default by baseURI from @BeforeAll

        //Code Status Verification
        int actualCodeStatus = response.statusCode();
        Assertions.assertEquals(200, actualCodeStatus);

        //Body type Verification
        String actualBodyType = response.contentType();
        Assertions.assertTrue(actualBodyType.contains("json"));

        //Verification if body contains "Europe"
        String actualBodyContent = response.body().asString();
        System.out.println("Body content = " + actualBodyContent);
        Assertions.assertTrue(actualBodyContent.contains("Europe"));

        // Verification if response header contains Data
      Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        String actualValueOfTransferHeader = response.header("Transfer-Encoding");// return value of Transfer-Encoding key from Headers section of response

        Assertions.assertEquals("chunked", actualValueOfTransferHeader);

        // contentType assertion by headers

        Assertions.assertEquals("application/json", response.header("Content-Type"));

        // Etag assertion by header
        String expectedEtag ="\"r4ySErEFLU1PEvLgn1dl3Rio7OHoLXMahAl8BD4gyTi/QCJiOyE/2eepREtNHpHrBLLNUhbQE9kPjc+lmRT0Ew==\"";
        Assertions.assertEquals(expectedEtag, response.header("Etag"));



    }

}
