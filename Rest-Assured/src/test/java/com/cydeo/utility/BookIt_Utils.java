package com.cydeo.utility;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.Assertions;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.core.IsNot.not;

public class BookIt_Utils {

    public static String getToken(String email, String password){
     String accessToken =   given().accept(ContentType.JSON).
                queryParam("email", email).
                queryParam("password", password).
                when().
                get("/sign").
                then().
                statusCode(200).
                extract().jsonPath().getString("accessToken");

        // This is a verification to see we are getting not null Value
        assertThat(accessToken,not(emptyOrNullString()));
        return "Bearer " + accessToken;
    }
        /*we can create that method base on roles:----->--->
    if we change the role of the User from teamleader to teacher can we use the same token?
        public static String getTokenByRole(String role){
            String email;
            String password;
        switch(role){
        case "team-leader":
                email=ConfigurationReader.getProperty("team-leader-email");
                password =ConfigurationReader.getProperty("team-leader-email")
                break;
         case "team-member":
         .....
         ...
         ...
        }
        email and password after switch case
        String accessToken = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password).
                when().get("/sign")
                .then().statusCode(200)
                .extract().jsonPath()
                .getString("accessToken");
        // This is a verification to see we are getting not null Value
        assertThat(accessToken,not(emptyOrNullString()));
        return "Bearer "+accessToken;
        }
     */

    public static String getTokenByRole(String role) {
        String email = "";
        String password = "";

        switch (role) {
            case "teacher":
                email = ConfigurationReader.getProperty("teacher_email");
                password = ConfigurationReader.getProperty("teacher_password");
                break;

            case "student-member":
                email = ConfigurationReader.getProperty("team_member_email");
                password = ConfigurationReader.getProperty("team_member_password");
                break;
            case "student-leader":
                email = ConfigurationReader.getProperty("team_leader_email");
                password = ConfigurationReader.getProperty("team_leader_password");
                break;
            default:

                throw new RuntimeException("Invalid Role Entry :\n>> " + role +" <<");
        }

        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);

        String accessToken = given()
                .queryParams(credentials)
                .when().get( "/sign").path("accessToken");

        return  "Bearer " + accessToken;

    }


    public static RequestSpecification getReqSpec (String role){

          RequestSpecification reqSpec = given()
                .log().all()// common for all request(good for request spec)
                .header("Authorization", getTokenByRole(role))/// common for all request, except role(good for request spec)
                .accept(ContentType.JSON);// common for all request (good for request spec)

        return reqSpec;

    }

    public static ResponseSpecification getResSpec (int statusCode){

       ResponseSpecification resSpec =  expect().statusCode(statusCode)// common for all response, except statusCode (200) // good for response spec
               .contentType(ContentType.JSON);// common

         return resSpec;

    }

}
