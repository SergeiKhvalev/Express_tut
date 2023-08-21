package com.cydeo.day_15;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class P02_spartanMACK {

    //https://73890f2c-e462-4791-a645-1c8bc9fe18e5.mock.pstmn.io

    @BeforeAll
    public static void init(){
        baseURI = "https://73890f2c-e462-4791-a645-1c8bc9fe18e5.mock.pstmn.io"; // it is MOCK SERVER< WHICH HAS BEEN CREATED BY POSTMAN, so when BackEnd will be ready we can change that MOCK URI on real URI

    }

    @DisplayName("GET/api/hello")
    @Test
    public void test(){

Response response = given().log().all()
        .accept(ContentType.TEXT)
        .when()
        .get("/api/hello")
        .then()
        .statusCode(200)
        .extract().response();

//Hello from sparta expected
        Assertions.assertEquals("Hello from Sparta", response.asString());

    }

    @DisplayName("GET/api/spartans")
    @Test
    public void tes2() {

        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", everyItem(notNullValue()))
                .extract().response();


        //check status code
        // Assertions.assertEquals(200, response.statusCode());

        //check each spartan has id

        List<String> allID = response.jsonPath().getList("id");
        System.out.println(allID);
    /*for(String eachElement : allID){
        if(eachElement != null){
            System.out.println("not null");
        } else {
            System.out.println("FAIL");
            break;
        }
    }
       // Assertions.assertEquals();

    }

     */

    }

    @DisplayName("POST/api/spartans")
    @Test
    public void tes3() {

        Map<String, Object> map11 = new HashMap<>();
        map11.put("name", "Sergei1");
        map11.put("gender", "Male");
        map11.put("phone", 1234456665L);
        System.out.println(map11);

         given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(map11)
                .when()
                .post("/api/spartans")
                .prettyPeek()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("data.id", (notNullValue()))
                .body("success", is("A new Spartan is Born!"));


        // check data.id has notnullValue

        // check message is success message "A new Spartan is Born!"


        //JsonPath

    }

}
