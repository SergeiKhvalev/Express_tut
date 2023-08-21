package com.cydeo.Day_5;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;


public class HamCrest_Spartan extends SpatanTestBase {



    @Test
    public void spartanWithHamcrest() {

// no need response object

        //and() - syntetic sugar words - use to icrease readability

 given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").
     then().statusCode(200).and().
            contentType("application/json").
         assertThat(). // can use as syntatic sugar to increase readability, but it not mandatory
            body("id", is(15), "name", is("Meta"), "gender", is("Female"), "phone", is(1938695106));


//body() - use to start checking body
        //.statusCode(is(200)).

        //response.prettyPrint();


        // log().all() after then() (print without response.pretty) -> because we do not have Response object initialy

       given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").
          then().log().all().statusCode(200).and().
                contentType("application/json").
                body("id", is(15), "name", is("Meta"), "gender", is("Female"), "phone", is(1938695106));


        //log().ifValidationFails() - show response if there any verification fail

       given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").
               then().log().ifValidationFails().statusCode(200).and().
               contentType("application/json").
               body("id", is(15), "name", is("Meta"), "gender", is("Female"), "phone", is(1938695106));
    }


    @Test
    public void spartanWithHamcrest2() {

// no need response object
        // Ho to print body


      //  given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").
           //     then().log().body().statusCode(200).and(). // log().body() - help to print body
           //     contentType("application/json").
             //   assertThat(). // can use as syntatic sugar to increase readability, but it not mandatory
             //   body("id", is(15), "name", is("Meta"), "gender", is("Female"), "phone", is(1938695106));

        // how to print response body

        given().accept(ContentType.JSON)
                .pathParam("id",15).
                when().get("/api/spartans/{id}").prettyPeek(). // give ability to print body in Response type, that why we can keep chaining
                then()
                .statusCode(200)
                // .statusCode(is(200)) --> if you wanna use with Matchers method you can use to increase readability
                .contentType("application/json")
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106));


        // HOW TO PRINT RESPONSE BODY
        /*
            - response.prettyPrint() (return String) ---> it is printing response body into screen, we can use it here because we do not call Response object
            - response.prettyPeek() (return Response) ---> it will print response into screen, returns Response   and allows us to continue chaining
         */

        // Now to extract data after doing validation with HamCreast

        //.extract().response()- helps to extract info from body
        //.extract.jsonPath() - helps to extract info from body

        // HOW TO PRINT RESPONSE BODY
        /*
            - response.prettyPrint() (String) ---> it is printing response body into screen
            - response.prettyPeek() (Response) ---> it will print response into screen, returns Response   and allows us to continue chaining
         */


        // HOW TO EXTRACT DATA AFTER DOING VALIDATION WITH HAMCREST ?
        /*
        - extract() --> method will help us to STORE data after doing verification as
                response()
                  OR
                jsonPath()
        - Why we need to extract ?
            - Assume that we are gonna do verification against UI/DB.In that case I need to get data from API after doing verification
            - SO we need to sometimes List of names / ids etc to check
            - That is why we need to initilaize as Response or JSonPAth (Since we know how to get data through this objects )
               to get realted data taht you wanna verfiy
         */




     Response response =   given().accept(ContentType.JSON)
                .pathParam("id",15).
                when().get("/api/spartans/{id}").prettyPeek(). // give ability to print body in Response type, that why we can keep chaining
                then()
                .statusCode(200)
                // .statusCode(is(200)) --> if you wanna use with Matchers method you can use to increase readability
                .contentType("application/json")
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106)).extract().response(); // extract().response() - return response object,  helps to extract info from body, but need all put in Response object

        int id = response.path("id");
        System.out.println("id = " + id);



    }


    @Test
    public void spartanWithHamcrest3() {

// extract data with JsonPath
       JsonPath jsonPath = given().accept(ContentType.JSON)
                .pathParam("id",15).
                when().get("/api/spartans/{id}").prettyPeek(). // give ability to print body in Response type, that why we can keep chaining
                then()
                .statusCode(200)
                // .statusCode(is(200)) --> if you wanna use with Matchers method you can use to increase readability
                .contentType("application/json")
                .body("id",is(15),
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106)).extract().jsonPath();

       int id = jsonPath.getInt("id");
        System.out.println("Id = " + id);




    }


}
