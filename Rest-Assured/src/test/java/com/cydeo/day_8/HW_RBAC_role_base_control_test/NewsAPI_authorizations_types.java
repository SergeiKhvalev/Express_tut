package com.cydeo.day_8.HW_RBAC_role_base_control_test;

import com.cydeo.utility.NewApiTestBase;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.restassured.RestAssured;
import io.restassured.internal.mapping.Jackson1Mapper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class NewsAPI_authorizations_types extends NewApiTestBase {

    static String accessToken = "6ba7a23ce1e64bb388f6f1c53efa9d84";

    @DisplayName("Authorization with QUERY param")
    @Test
    public void Test1() {
/*
TASK 1 —> QUERY PARAM
- Given query param is q=“bitcoin”
 - And query param is apiKey=“yourKey”
 - When user sent request / everything endpoint
- Then status code should be 200
- And each articles contains “bitcoin”
 */


        //NOT PASSED
        String accessToken = "6ba7a23ce1e64bb388f6f1c53efa9d84";
        Response response = given()
                .accept("application/json")
                .queryParam("q", "bitcoin")
                .queryParam("apiKey", accessToken)
                // .log().all()
                .when()
                .get("/everything")
                // .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json").extract().response();


        //assertion each article contains "bitcoin"

        List<String> listOfContent = response.path("articles.content");
        // System.out.println("List of descriptions = " + listOfContent);
        System.out.println("Number of titles = " + listOfContent.size());


        for (String eachContent : listOfContent) {
            Assertions.assertTrue(eachContent.toLowerCase().contains("bitcoin"));
            System.out.println(eachContent);
            System.out.println("=======================");
        }
    }

    @DisplayName("KEY - X-Api-Key in HEADER")
    @Test
    public void Test2() {
       /* TASK 2 —> X-Api-Key in HEADER
        - Given query param is q=“bitcoin”
        - And header is X-Api-Key=“yourKey”
        - When user sent request / everything endpoint
                - Then status code should be 200
                - And each articles contains “bitcoin”
        */

        Response response = given().accept("application/json")
                .log().uri()
                .header("X-Api-Key", accessToken)
                .queryParam("q", "bitcoin")
                .when()
                .get("/everything")
                .then()
                .statusCode(200)
                .contentType("application/json").extract().response();

        // response.prettyPeek();
        List<Object> list = response.jsonPath().getList("articles.");
        System.out.println("numbers of articles = " + list.size());
    }

    @DisplayName("with help of key Authorization in header")
    @Test
    public void Test3() {
/*
TASK 3 —> Authorization in HEADER
- Given query param is q=“bitcoin”
 - And header is Authorization=“yourKey”
 - When user sent request / everything endpoint
- Then status code should be 200
- And each articles contains “bitcoin”

 */
     Response response =   given()
                .accept("Application/json")
                .queryParam("q", "bitcoin")
                .header("Authorization", accessToken)
                .when()
                .get("/everything")
                .then()
                .contentType("application/json")
                .statusCode(200)
                .extract().response();
    }

    @DisplayName("with help of key Authorization in header")
    @Test
    public void Test4() {
/*
TASK 4 —> Authorization in HEADER
- Given query param is country=“us”
 - And header is Authorization=“yourKey”
 - When user sent request / top-headlines endpoint
- Then status code should be 200
- And print out all sources names
 */

        JsonPath jsonPath = given()
                .accept("application/json")
                .queryParam("country" , "us")
                .header("X-Api-Key", accessToken)// instead "X-Api-Key" we can put "Authorization" will works as well
                .when()
                .get("/top-headlines")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath();

        List<String> allNames = jsonPath.getList("articles.source.name");
        int numOfNames = allNames.size();
        System.out.println("Total numbers of names = " + numOfNames);

        int num = 1;
        for (String eachName : allNames){
            System.out.println(num + " Name = " + eachName);
            num++;

        }



    }

}

