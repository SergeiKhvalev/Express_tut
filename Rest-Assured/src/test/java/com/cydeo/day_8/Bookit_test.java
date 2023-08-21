package com.cydeo.day_8;

import com.cydeo.utility.BookIt_Utils;
import com.cydeo.utility.BookitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;


public class Bookit_test extends BookitTestBase {

    String email = "lfinnisz@yolasite.com";
    String password = "lissiefinnis";
    String accessToken = BookIt_Utils.getToken(email, password); // used util method to generate Token and pass it to code

    @DisplayName("Get/api/campuses")
    @Test
    public void Test1() {
        System.out.println(accessToken);
        given().accept(ContentType.JSON)
                //.log().all()
                .header("Authorization", accessToken).
                when().get("/api/campuses")
                // . prettyPeek()// to see response body
                .then().statusCode(200);

// create util class which will generate token base on Email and password
        //BookITUtils.getToken(String username

    }

    @DisplayName("Get/api/users/me")
    @Test
    public void Test2() {
        System.out.println(accessToken);

        given().accept(ContentType.JSON).
                header("Authorization", accessToken). //
                when().
                get("/api/users/me").
                prettyPeek().
                then().statusCode(200);

    }

    @DisplayName("Get/api/users/me")
    @Test
    public void Test3() {
        System.out.println(accessToken);
      Response response =  given().accept(ContentType.JSON).
                auth().oauth2(accessToken). // with auth().oauth2 authorization
                when().
                get("/api/users/me").
                prettyPeek().
                then().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();

    }
}
