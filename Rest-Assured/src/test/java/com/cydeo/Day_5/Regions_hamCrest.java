package com.cydeo.Day_5;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class Regions_hamCrest extends HrTestBase {


    @Test
    public void Test3(){
// manualy passed
        /*
      Given
               accept type is application/json
       When
               user sends get request to /regions
       Then
               response status code must be 200
               verify Date has values
               first region name is Europe
               first region id is 1
               four regions we have
               region names are not null
               Regions name should be same order as "Europe","Americas","Asia","Middle East and Africa"
               region ids needs to be 1,2,3,4

               print all the regions names
               ...
               ..
               .
         */

        given().accept(ContentType.JSON).when().get("/regions").then().statusCode(200).contentType("application/json").headers("Date", notNullValue()).body("items[0].region_name", is("Europe"),
                "items[0].region_id", is(1),
                "items", hasSize(4),
                "items.region_name", everyItem(notNullValue()),
                "items.region_name", containsInRelativeOrder("Europe","Americas","Asia","Middle East and Africa"), "items.region_id", containsInRelativeOrder(1,2,3,4));

        // How print all regions


       List<String> allRegionNames = given().accept(ContentType.JSON).when().get("/regions").then().statusCode(200).contentType("application/json").headers("Date", notNullValue()).body("items[0].region_name", is("Europe"),
                "items[0].region_id", is(1),
                "items", hasSize(4),
                "items.region_name", everyItem(notNullValue()),
                "items.region_name", containsInRelativeOrder("Europe","Americas","Asia","Middle East and Africa"), "items.region_id", containsInRelativeOrder(1,2,3,4)).extract().response().path("items.region_name");
        System.out.println("path() rigth away = " + allRegionNames);

        // same result byt by JsonPath

        JsonPath jsonPath = given().accept(ContentType.JSON).when().get("/regions").then().statusCode(200).contentType("application/json").headers("Date", notNullValue()).body("items[0].region_name", is("Europe"),
                "items[0].region_id", is(1),
                "items", hasSize(4),
                "items.region_name", everyItem(notNullValue()),
                "items.region_name", containsInRelativeOrder("Europe","Americas","Asia","Middle East and Africa"), "items.region_id", containsInRelativeOrder(1,2,3,4)).extract().response().jsonPath();

        List <String> listOfAllName2 = jsonPath.getList("items.region_name");
        System.out.println("JsonPath = " + listOfAllName2);


    }
}
