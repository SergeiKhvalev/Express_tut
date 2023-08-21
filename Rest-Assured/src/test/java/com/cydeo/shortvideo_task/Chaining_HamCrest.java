package com.cydeo.shortvideo_task;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class Chaining_HamCrest extends SpatanTestBase {

    /*
   Given accept type - json
   And pathParam spartan id is 15
   When user send a get request to /spartans/{id}
Then status code - 200
And contentType - is Json
And "id" : 15
"name": "Meta"
"gender": "Female"
"phone": 1938695106
    */
    @Test
    public void Test1() {
        // manually passed
        //Hamcrest
        // we can use in this situation Response object
 RestAssured.given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").then().statusCode(200).assertThat().contentType("application/json");
        //response.prettyPrint();


    }

    @Test
    public void Test2() {
        // manually passed
        // hamcrest
        // we can use in this situation Response object
//No response object
        RestAssured.given().accept(ContentType.JSON).and().pathParam("id", 15).when().get("/api/spartans/{id}").then(). assertThat().statusCode(200).assertThat().contentType("application/json").assertThat().body("id", Matchers.equalTo(15), "name", Matchers.equalTo("Meta"),"gender", Matchers.equalTo("Female"), "phone", Matchers.equalTo(1938695106));
        //response.prettyPrint();
        // in order to assert body parts -> body("key from body", Matchers.equelTo(expected value from body), "key from body", Matchers.equalTp("expected value from body")


    }
}
