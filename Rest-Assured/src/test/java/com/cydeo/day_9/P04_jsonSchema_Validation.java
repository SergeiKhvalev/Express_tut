package com.cydeo.day_9;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import lombok.Data;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class P04_jsonSchema_Validation extends SpatanTestBase {

    @DisplayName("Get /api/spartans/{id} to validate with JsonSchema_")
    @Test
    public void test1() {

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json")); // JsonSchemaValidator.matchesJsonSchemaInClasspath () - that method came from json-schema-validator dependency. "SingleSpartanSchema.json" - pathway, where that schema stored (our resource folder)

        // it gets response from execution and we provided  structure of response in following path

        //   --JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json")

        // if structure that we are getting matches with SingleSpartanSchema it will pass.

    }

    @DisplayName("Get /api/spartans/{id} to validate with JsonSchema with matchesJasonSchema")
    @Test
    public void test2() {

        given()
                .accept(ContentType.JSON)
                .pathParam("id", 10)
                .when()
                .get("/api/spartans/{id}")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/SingleSpartanSchema.json")));

    }

    @DisplayName("Get /api/spartans/search to validate with JsonSchema with matchesJasonSchema")
    @Test
    public void test3() {

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/api/spartans/search")
                .then()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SearchSpartansSchema.json"));



    }

}
