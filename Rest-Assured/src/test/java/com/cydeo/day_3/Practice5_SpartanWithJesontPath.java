package com.cydeo.day_3;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.AssertionSupport;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.StringJoiner;

public class Practice5_SpartanWithJesontPath extends SpatanTestBase {

    @Test
    public void Test1(){

        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 10).when().get("/api/spartans/{id}");
        response.prettyPrint();
        Assertions.assertEquals(200, response.statusCode());


        // we saved response as JSONPATH OBJECT

        System.out.println(response.path("id").toString()); // we ca not to print response.path("id") rigth away need convert if to string, but we can assign it to needed type of variable and it converts to data automaticaly
        int id1 = response.path("id");
        System.out.println(id1);


        // JsonPath - help as to convert it to needed type and get needed data
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        System.out.println(id);
        String name = jsonPath.getString("name");
        System.out.println(name);
        String gender = jsonPath.getString("gender");
        System.out.println(gender);
        long phone = jsonPath.getLong("phone");
        System.out.println(phone);

        // Asssertion

        Assertions.assertEquals(10, id);
        Assertions.assertEquals("Lorenza", name);
        Assertions.assertEquals("Female", gender);
        Assertions.assertEquals(3312820936l, phone);


    }

}
