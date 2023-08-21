package com.cydeo.shortvideo_task;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

public class path_body_search_by_key_and_value {

    @BeforeAll
    public static void setUpURL() {

        RestAssured.baseURI = "http://54.167.63.56:8000";
    }

    /*
    Given accept type is json
    And path paran - 10
    When user sends a get request to "api/spartans/{10}
    Then status code - 200
    And content type - "application/json"
    And respons payload values match rhe following
        id - 10
        name - Lorenza
        gender - Female
        phone - 33128220936
     */

    @Test
    public void Test1() {

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("i", 10).when().get("/api/spartans/{i}");

        //verify status code
        Assertions.assertEquals(200, response.statusCode());

        //verify content Type
        Assertions.assertEquals("application/json", response.contentType());

        // now need assert bode in kay and value format and check key value one by one (no body().asString - it is not good approach)
// PRINT BODY KEYS VALUES
        System.out.println("Id  = " + response.body().path("id").toString());
        System.out.println("Name  = " + response.body().path("name").toString());
        System.out.println("gender  = " + response.path("gender").toString());// it works without body() as well
        System.out.println("phone  = " + response.path("phone").toString());// it works without body() as well


        //response.path("") - return type T, but this T type automatically converts to relevant type
        int id = response.path("id");
        System.out.println("ID = " + id);

        String name = response.path("name");
        System.out.println("name = " + name);

        String gender = response.path("gender");
        System.out.println("gender = " + gender);

        long phone = response.path("phone");
        System.out.println("phone = " + phone);


        Assertions.assertEquals(10, id);
        Assertions.assertEquals("Lorenza", name);
        Assertions.assertEquals("Female", gender);
        Assertions.assertEquals(3312820936L, phone);

    }

    @Test
    public void Test2() {
        // What returned multiple spartans, how to get info from body by path() byt only certain spartan
        Response response = RestAssured.get("/api/spartans");

        // how retrieve data from body of response of certain person-> we can treat response body as Array or ArrayList, where each person info is element of data structure, so just provide index response.path("id[0]"), response.path("id[33]"), so on

        int firstPersonId = response.path("id[0]");
        System.out.println("Id of first person = " + firstPersonId);
        String nameOfFirstPerson = response.path("name[0]");
        System.out.println("Name of first spartan = " + nameOfFirstPerson);

        // how to get last person inf

        int lastPersonId = response.path("id[-1]");
        System.out.println("Id of first person = " + lastPersonId);
        String nameOfLastPerson = response.path("name[-1]");
        System.out.println("Name of first spartan = " + nameOfLastPerson);

        // get all first names and print them

        List<String> listOfAllnames = response.path("name");
        System.out.println(listOfAllnames);
        int nameCount = listOfAllnames.size();
        System.out.println("Count of names = " + nameCount);
    }



}
