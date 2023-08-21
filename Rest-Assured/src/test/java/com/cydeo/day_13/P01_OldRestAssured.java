package com.cydeo.day_13;

import com.cydeo.utility.SpartanNewAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_OldRestAssured extends SpartanNewAuthTestBase {

    @Test
    public void oldWay() {

        //regular approach to get needed info
        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/spartans")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id[0]", is(101)) // hard assertion, if it will fail next hamcrest assertion not run
                .body("id[1]", is(102));
    }

    @Test
    public void newWay() {

        given()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")// request specification (we can put here queryPara, Accept, auth.basic, header, log
                .expect().statusCode(200).contentType(ContentType.JSON)// response specification (expectation from response), we can put here header, contentType, statusCode, body,
                .body("id[0]", is(101))  //response specification (expectation from response) // soft assertion, because if it fails next expectation will still work
                .body("id[1]", is(102)) //response specification (expectation from response)
                .when()
                .get("/spartans")
                .prettyPeek();


    }
 /*
    OLD WAY --> EXPECT()
        - it works like soft assertion
    NEW WAY --> then()  (This is the that we are gonna use now also in the future if they will not release new version )
        - it works like hard assertion
     */

}
