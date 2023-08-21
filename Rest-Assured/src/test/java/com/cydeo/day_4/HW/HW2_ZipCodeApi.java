package com.cydeo.day_4.HW;

import com.cydeo.utility.ZipCodeTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore

public class HW2_ZipCodeApi extends ZipCodeTestBase {
    /*
   TASK 1
Given Accept application/json
And path zipcode is 22031
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
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
        Response response = given().accept(ContentType.JSON).pathParam("zipcode", 22031).when().get("/us/{zipcode}");
        response.prettyPrint();

        //   Then status code must be 200
        assertEquals(200, response.statusCode());

        // And content type must be application/json
        String actualContentType = response.contentType();
        String actualContentTypeByHeader = response.header("Content-Type");
        System.out.println(actualContentTypeByHeader);
        System.out.println(actualContentType);
        assertEquals("application/json", actualContentType);

        //And Server header is cloudflare
        String actualServerHeaderInfo = response.header("Server");
        System.out.println(actualServerHeaderInfo);
        assertEquals("cloudflare", actualServerHeaderInfo);

        //And Report-To header exists
        System.out.println("Report-To header presented = " + response.headers().hasHeaderWithName("Report-To"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        // And body should contains following information
        // post code is 22031
        JsonPath jsonPath = response.jsonPath(); // we put whole response to JsonPath object to easier manipulate data
        System.out.println("Post code = " + jsonPath.getInt("'post code'"));
        assertEquals("22031", jsonPath.getString("'post code'"));

        //country is United States
        System.out.println("Country = " + jsonPath.getString("country"));
        assertEquals("United States", jsonPath.getString("country"));
        // country abbreviation is US
        System.out.println("Country abbreviation = " + jsonPath.getString("'country abbreviation'"));
        assertEquals("US", jsonPath.getString("'country abbreviation'"));
        // place name is Fairfax
        System.out.println("Place name = " + jsonPath.getString("places[0].'place name'"));
        assertEquals("Fairfax", jsonPath.getString("places[0].'place name'"));
        //state is Virginia
        System.out.println("State = " + jsonPath.getString("places[0].'state'"));
        //latitude is 38.8604
        System.out.println("Latitude = " + jsonPath.getString("places[0].'latitude'"));
        assertEquals("38.8604", jsonPath.getString("places[0].'latitude'"));
    }

    @Test
    public void Test2() {
        /*
        TASK 2
Given Accept application/json
And path zipcode is 50000
When I send a GET request to /us endpoint
Then status code must be 404
And content type must be application/jso
         */
        Response response = given().accept(ContentType.JSON).and().pathParam("zipCode", 50000).when().get("/us/{zipCode}");
        response.prettyPrint();

        //Then status code must be 404
        System.out.println(response.statusCode());
        assertEquals(404, response.statusCode());

        //And content type must be application/json
        System.out.println("body format" + response.contentType());
        assertEquals("application/json", response.contentType());

    }


    @Test
    public void Test3(){
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
        Response response = given().accept(ContentType.JSON).pathParam("state", "va").and().pathParam("place", "fairfax").when().get("/us/{state}/{place}");
       // response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();


        List<Map<String, Object>> listOfObjects = jsonPath.getList("places");
        System.out.println(listOfObjects);
        System.out.println("Post code of first Element = " + listOfObjects.get(0).get("post code")); // 22030
        int numberOfPostCodes = listOfObjects.size();
        System.out.println(numberOfPostCodes);

        //Then status code must be 200
        Assertions.assertEquals(200, response.statusCode());
        System.out.println("Code status + " + response.statusCode());

      //  And content type must be application/json

        assertEquals("application/json", response.contentType());
        System.out.println("Content type = " + response.contentType());
        //And payload should contains following information
        //country abbreviation is US
String countryAbb = jsonPath.getString("'country abbreviation'");
        System.out.println("Country abbriviation = " + countryAbb);
assertEquals("US", countryAbb);
       // country United States
        String countryName = jsonPath.getString("country");
        assertEquals("United States", jsonPath.getString("country"));
       // place name Fairfax
        String placeName = jsonPath.getString("'place name'");
        System.out.println("Place name = " + placeName);
        assertEquals("Fairfax", placeName);

        //each places must contains fairfax as a value
        List <String> listOfPlaceName = jsonPath.getList("places.'place name'");
        System.out.println("Each place name = " + listOfPlaceName);
        int eachPlaceNum = 1;
        for (String eachPlaceName : listOfPlaceName){
            System.out.println(eachPlaceNum +" " + eachPlaceName);
            eachPlaceNum++;
            assertTrue(eachPlaceName.contains("Fairfax"));
        }

        //each post code must start with 22
        List<String> lisOfPostCode = response.path("places.'post code'");
        int eachPostCodeNum = 1;
        for (String eachPostCode : lisOfPostCode){
            System.out.println(eachPostCodeNum + " " + eachPostCode);
            eachPostCodeNum++;
            assertTrue(eachPostCode.startsWith("22"));

        }



    }




}
