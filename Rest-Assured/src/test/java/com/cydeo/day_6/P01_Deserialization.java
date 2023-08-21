package com.cydeo.day_6;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class P01_Deserialization extends HrTestBase {
/**
 * Create a test called getLocation
 * 1. Send request to GET /locations
 * 2. log uri to see
 * 3. Get all Json as a map and print out screen all the things with the help of  map
 * System.out.println("====== GET FIRST LOCATION  ======");
 * System.out.println("====== GET FIRST LOCATION LINKS  ======");
 * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
 * System.out.println("====== FIRST LOCATION ======");
 * System.out.println("====== FIRST LOCATION ID ======");
 * System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
 * System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
 * System.out.println("====== LAST LOCATION ID ======");
 */

@Test
    public void Test1(){

  JsonPath jsonPath = given().log().uri().accept(ContentType.JSON).
            when().get("/locations").
            then().statusCode(200).extract().jsonPath();


  //  * 3. Get all Json as a map and print out screen all the things with the help of  map
    // List <Map<String, Object>> listOfMap = jsonPath.getList("items");

    //* System.out.println("====== GET FIRST LOCATION  ======");
    Map <String, Object> firstElement = jsonPath.getMap("items[0]");
    System.out.println("First location = " + firstElement);

// * System.out.println("====== GET FIRST LOCATION LINKS  ======");
    Map <String, Object> firstLocationLink = jsonPath.getMap("items[0].links[0]");
    // how to get href info
    System.out.println(firstLocationLink.get("href"));
    System.out.println("First location link = " + firstLocationLink);

// * System.out.println("====== GET ALL LOCATIONS AS LIST OF MAP======");
    List<Map<String, Object>> items = jsonPath.getList("items");
    for (Map <String, Object> each: items ){
        System.out.println(each);
    }
   // System.out.println("All locations asl List of Map = " + items);

// * System.out.println("====== FIRST LOCATION ======");
    System.out.println("First location =" + items.get(0));

// * System.out.println("====== FIRST LOCATION ID ======");
    System.out.println("id of first location = " + items.get(0).get("location_id"));

 //* System.out.println("====== FIRST LOCATION COUNTRY_ID ======");
    System.out.println("First location country id = " + items.get(0).get("country_id"));

 //* System.out.println("====== GET FIRST LOCATION FIRST LINK  ====== ");
    System.out.println("First location first link = " + items.get(0).get("links"));// but it return whole links Array - not correct complely
    //solvation of that problem
    List<Map<String, Object>> links = (List<Map<String, Object>>) items.get(0).get("links"); // so we get second object - links and convert in to List<Map<String, Object>> by downcasting. We need downcasting because initially that links (value) is Object and we need downcast it to List of Map in order to works wit it like with List of map
    System.out.println("links.get(0).get(href) = " + links.get(0).get("href")); // now we able to work as with independant list of map and retrieve neded datat

 //* System.out.println("====== LAST LOCATION ID ======");

    System.out.println("last location ID = " + items.get(items.size()-1).get("location_id"));


// */


}
}
