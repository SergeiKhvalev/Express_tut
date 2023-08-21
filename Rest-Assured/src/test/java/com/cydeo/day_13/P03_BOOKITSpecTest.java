package com.cydeo.day_13;

import com.cydeo.utility.BookIt_Utils;
import com.cydeo.utility.BookitTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class P03_BOOKITSpecTest extends BookitTestBase {

    /*
    send request /api/users/me endpoint
    verify status code - 200
    contentType - ContentType.Json
     */

    @Test
    public void test1(){

        // Old approach
        given()
                .log().all()// common for all request(ggod for request spec) // create static method for RequestSpec
                .header("Authorization", BookIt_Utils.getTokenByRole("teacher"))/// common for all request, exept role(good for request spec) // create static method for RequestSpec
                .accept(ContentType.JSON)// common for all request (good for request spec) // create static method for RequestSpec
                .when()
                .get("/api/users/me")
                .prettyPeek()
                .then()
                .statusCode(200)// common for all response, except statusCode (200) // good for response spec//create static method for ResponseCpec
                .contentType(ContentType.JSON);// common for all response// good for response spec // create static method for ResponseCpec


    }

    @Test
    public void test2(){

        // new approach with RequestSpecification and ResponseSpecification, where RequestSpesification and ResponseSpecification with common fields for all requests and responses stored in BookIt_itils as static methods

        // in the End we have more clear code in out @Test in compare with ald way!!!
        given()
                .spec(BookIt_Utils.getReqSpec("teacher"))
                .when()
                .get("/api/users/me")
                .prettyPeek()
                .then()
                .spec(BookIt_Utils.getResSpec(200))
                .body("firstName", is("Barbabas"));


    }


}
