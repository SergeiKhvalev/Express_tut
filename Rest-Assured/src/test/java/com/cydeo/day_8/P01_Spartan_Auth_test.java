package com.cydeo.day_8;

import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class P01_Spartan_Auth_test extends SpartanAuthTestBase {

    @DisplayName("Get/spartans as GUEST USER--> expected code Status 401, because GUEST not authorized")
    @Test
    public void Test() {
// it is negative testing
        given().accept(ContentType.JSON).
                when().get("/api/spartans").
                // prettyPeek(). to print all
                        then().
                //log().ifValidationFails(). //to print if failes
                        statusCode(401).
                body("error", Matchers.is("Unauthorized"));

    }


    @DisplayName("Get/spartans as  USER--> expected code Status 401, because GUEST not authorized")
    @Test
    public void Test2() {

// in this situation need to provide credantials to login
        given().accept(ContentType.JSON).
                auth().basic("user", "user").// this is way to send credentials in order to log in as User role
                //  header("Authorization", "Basic YWRtaW46YWRtaW4="). // we not do this way
                        when().get("/api/spartans").
                //prettyPeek().// to see result after we successfully logged in
                        then().
                log().ifValidationFails(). //to print if fails
                statusCode(200);

    }

    @DisplayName("DELETE/spartans{id} as  EDITOR--> expected code Status 403-FORBIDDEN, because editor can not delete")
    @Test
    public void Test3() {

// in this situation need to provide credantials to login
        given().pathParam("id", 53). // define which Spartan need delete
                auth().basic("editor", "editor").// this is way to send credentials in order to log in as User role
                when().delete("api/spartans/{id}").
                then().statusCode(403).
                body("error", Matchers.is("Forbidden"));

    }

    @DisplayName("DELETE/spartans{id} as  admin--> expected code Status 204-success, because admin able  delete")
    @Test
    public void Test4() {

// in this situation need to provide credantials to login
        given().pathParam("id", 52). // define which Spartan need delete
                auth().basic("admin", "admin").// this is way to send credentials in order to log in as User role
                when().delete("api/spartans/{id}").
                then().statusCode(204);


    }

}
