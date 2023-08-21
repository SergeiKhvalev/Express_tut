package com.cydeo.day_4;

import com.cydeo.utility.CydeoStudentsTestBase;
import groovyjarjarantlr.ASdebug.ASDebugStream;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore

public class Practice2_cydeoStudents extends CydeoStudentsTestBase {


    /*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is enjoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222
     */

    @DisplayName("Get student with id 2")
    @Test
    public void Test(){

       Response response = given().accept(ContentType.JSON).and().pathParam("id", 2).when().get("/student/{id}");
// log().all() - return all info which we send to our API and what we expect from API response
// log().body() -  return all info which we send to response and what we expect from body
//


response.prettyPrint();


        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8", response.contentType());


        // Data header verifying
      Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));

        // Server header verifying
        Assertions.assertEquals("envoy", response.header("server"));

        //create JSON PAth object
        JsonPath js = response.jsonPath();

        assertEquals("Mark",js.getString("students[0].firstName"));

    //    assertEquals("Mark", js.getString("students[0].firstName"));

        assertEquals(13, js.getInt("students[0].batch"));

        assertEquals("math", js.getString("students[0].major"));

        assertEquals("mark@email.com", js.getString("students[0].contact.emailAddress"));

        assertEquals("Cydeo", js.getString("students[0].company.companyName"));
        assertEquals("777 5th Ave",js.get("students[0].company.address.street"));

        assertEquals(33222, js.getInt("students[0].company.address.zipCode"));

    }






}
