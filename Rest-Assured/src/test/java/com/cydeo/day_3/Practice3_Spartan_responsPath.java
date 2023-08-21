package com.cydeo.day_3;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore


public class Practice3_Spartan_responsPath extends SpatanTestBase {

    @Test
    public void Test1() {
/*
 Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
 */

        Response response = given().accept(ContentType.JSON).and().pathParam("id", 10).when().get("/api/spartans/{id}");

        // Then status code is 200
        Assertions.assertEquals(200, response.statusCode());

        // And content-type is "application/json"

        Assertions.assertEquals("application/json", response.contentType());
        // And response payload values match the following:
        // id is 10,
        //         name is "Lorenza",
        //        gender is "Female",
        //       phone is 3312820936

        Object id = response.path("id"); // we can use Parent object Object to avoid to specify variable type right away
        System.out.println("id = " + id);

        String name = response.path("name");
        System.out.println("Name = " + name);

        Object gender = response.path("gender");
        System.out.println("Gender =" + gender);

        long phone = response.path("phone");
        System.out.println("Phone =" + phone);

        String address = response.path("address");// if path key wrong - return null
        System.out.println("Address = " + address);

        //Assertions

        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936L, phone);


    }

    @DisplayName("GET all spartans")
    @Test
    public void Test3() {

        Response response = given().accept(ContentType.JSON).get("/api/spartans");

        //  response.prettyPrint();
        List<Integer> listOfId = response.path("id"); // if we get body with multiple objects, we can store it with using path("id") in list
        int countOfSpartans = listOfId.size();
        System.out.println(countOfSpartans);

        // get first spartan ID
        int firstSpartanId = response.path("[0].id");
        System.out.println("First spartan ID = " + firstSpartanId); // 1
        // same result
        int firstSpartanId1 = response.path("id[0]");
        System.out.println("First spartan ID (another syntax) = " + firstSpartanId1);//1

        // get first spartan name
        String firstSpartanName = response.path("[0]. name"); // Meade
        System.out.println("First spartan name = " + firstSpartanName);
        //same result
        String firstSpartanName1 = response.path("name[0]");// // Meade
        System.out.println("First  spartan name (another syntax) = " + firstSpartanName1);

        //get me last spartan name
        String lastSpartanName = response.path("name[-1]");// -1 get last element of Array
        System.out.println("Last spartan name = " + lastSpartanName); // Terence

        // get me second spartan name from last

        String secondLastName = response.path("name[-2]");
        System.out.println("Second last name = " + secondLastName);// Adair



        // gt me all spartans names
       List <String> allNames=  response.path("name");
        System.out.println("All names of the spartans = " + allNames);
        // to print one by one - loop it
        for (String eachName : allNames){
            System.out.println("Each name = " + eachName);
        }

        int countOfNames = allNames.size(); // 100
        System.out.println("Count of names = " + countOfNames);


    }


}
