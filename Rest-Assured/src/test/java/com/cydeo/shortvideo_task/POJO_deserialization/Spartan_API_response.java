package com.cydeo.shortvideo_task.POJO_deserialization;

import com.cydeo.utility.SpatanTestBase;
import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.mapping.Jackson1Mapper;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Spartan_API_response extends SpatanTestBase {

    @Test
    public void Test1() {

        // create request and store response body in response
        Response response = RestAssured.given().accept(ContentType.JSON).pathParam("id", 15).when().get("/api/spartans/{id}");

        response.prettyPrint();

        //MAKE POJO class -> Call object from Spartan class and initialize response body there

        Spartan spartan1 = response.body().as(Spartan.class); // we assign our response body to fields in our POJO custom Spartan class
        System.out.println(spartan1.toString());

        // make assertions all needed data
        Assertions.assertEquals(spartan1.getId(), 15);// we call needed field from POJO custom class by colling getter of needed variable
        Assertions.assertEquals(spartan1.getName(), "Meta");// we call needed field from POJO custom class by colling getter of needed variable
        Assertions.assertEquals(spartan1.getGender(), "Female");// we call needed field from POJO custom class by colling getter of needed variable
        Assertions.assertEquals(spartan1.getPhone(), 1938695106);// we call needed field from POJO custom class by colling getter of needed variable

    }

    @Test
    public void Test2() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonBody = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

Spartan spartanMeta = objectMapper.readValue(jsonBody, Spartan.class);
        System.out.println(spartanMeta);

    }
}