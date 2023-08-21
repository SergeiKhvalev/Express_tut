package com.cydeo.Day_5;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.groovy.json.internal.Exceptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;


public class P4_deserialisation_To_JavaCollection extends SpatanTestBase {

    @Test
    public void Test1() {
        /*
     Given accept type is application/json
     And Path param id = 10
     When i send GET request to /api/spartans
     Then status code is 200
     And content type is json
     And spartan data matching:
     id > 10
     name>Lorenza
     gender >Female
     phone >3312820936
     */
        // manually passed


        // second way -> extract().response
        Response response = given().accept(ContentType.JSON).pathParam("id", 10).when().get("/api/spartans/{id}").
                then().statusCode(200).
                contentType("application/json").header("Content-Type", "application/json").
                extract().response(); // we convert response in Response object. In this situation we nessasery chain EXTRACT().RESPONSE() BECAUSE we use THEN() and HAMCREST assertions in our response(so we assert couple fields right in response

        // first approach with as() method, which come from response RestAssured library
//to convert JSON response API body to Java data structures, can use JSON PARSER
        //need for deserialization/ serialization. In that porpose we add one ObjectMapper like jackson-databind or GSON
        Map<String, Object> mapOfBody = response.as(Map.class);
        System.out.println("Map with help of response.as() method"+ mapOfBody);

        // Can treat given Map in regular way
       int id = (int) mapOfBody.get("id"); // need cast to int, because value of Map is Object
        System.out.println(id);
        String name = (String) mapOfBody.get("name"); // need cast to String, because value of Map is Object
        System.out.println(name);
        String gender = (String) mapOfBody.get("gender");// need cast to String, because value of Map is Object
        System.out.println(gender);


        // Second Approach -> to convert response body to Java data structure -> with help of JsonPath

        JsonPath jsonPath = response.jsonPath();
        Map<String, Object> jsMap = jsonPath.getMap("");
        System.out.println("JsonMap = " + jsMap);
        // Map we treat in regular way
        int jsid = (int)jsMap.get("id");// need cast to int, because value of Map is Object
        System.out.println("JsId = " + jsid);

        String jsName = (String) jsMap.get("name");// need cast to String, because value of Map is Object
        System.out.println("JsName = " + jsName);

        String jsGender = (String) jsMap.get("gender");// need cast to String, because value of Map is Object
        System.out.println("JsGender = " + jsGender);

        long jsPhone = (long) jsMap.get("phone");// need cast to long, because value of Map is Object
        System.out.println("jsPhone = " + jsPhone);

    }


    @DisplayName("GET All Spartans with Java Collections")
    @Test
    public  void test2() {

        Response response = given().accept(ContentType.JSON).
                when().get("/api/spartans").
                then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //Approach one ---> with Response

        List<Map<String, Object>> spartanList = response.as(List.class);


        for (Map<String, Object> eachMap : spartanList) {
            System.out.println("eachMap = " + eachMap);
        }

        // How to find first spartan info
        System.out.println("spartanList.get(0) = " + spartanList.get(0));
        // How to find first spartan name
        System.out.println("spartanList.get(0).get(\"name\") = " + spartanList.get(0).get("name"));
        // How to find first spartan id
        System.out.println("spartanList.get(0).get(\"id\") = " + spartanList.get(0).get("id"));


        // how to store first spartan info as a map with response.as() method
        // if yo uwanna convert only specific part of response with response.as() it does not have any parameters to get as path of
        // json object.That is why we can use in here response.path() method to convert this as a Object.Since we know the type of it
        // we can use and store as Map

        Map<String, Object> firstSpartanMap = response.path("[0]");
        System.out.println("firstSpartanMap = " + firstSpartanMap);


        // Approach second ---> with JSONPATH
        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Object>> allSpartanList = jsonPath.getList("");

        for (Map<String, Object> eachSpartan : allSpartanList) {
            System.out.println("eachSpartan = " + eachSpartan);
        }

        // How to find first spartan info
        System.out.println("allSpartanList.get(0) = " + allSpartanList.get(0));
        // How to find first spartan name
        System.out.println("allSpartanList.get(0).get(\"name\") = " + allSpartanList.get(0).get("name"));
        // How to find first spartan id
        System.out.println("allSpartanList.get(0).get(\"id\") = " + allSpartanList.get(0).get("id"));

    }



}
