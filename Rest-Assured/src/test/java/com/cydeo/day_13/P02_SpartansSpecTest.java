package com.cydeo.day_13;

import com.cydeo.utility.SpartanNewAuthTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P02_SpartansSpecTest  extends SpartanNewAuthTestBase{

    @Test
    public void getAllSpartan() {

        given()
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin")
                .when()
                .get("/spartans")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON);


    }

    @Test
    public void getAllWithRequestSpecSpartan() {

        // more preferable way to write API request and response
/* we move it to SpartanNewAuthTestBase to make it reusable in other class
        RequestSpecification reqSpes = given() // this is request specification
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");

        ResponseSpecification resSpec = expect().statusCode(200)
                .contentType(ContentType.JSON);// our response specification// common expectation from all Responses

 */


                 given().spec(reqSpes)
                         .when()
                .get("/spartans")
                .then().spec(resSpec);



    }
    @Test
    public void getSingleSpartan() {

        given().spec(reqSpes)
                .pathParam("id", 3)
                .when()
                .get("/spartans/{id}")
                .then().spec(resSpec)
                .body("id", is(3));



    }

    @Test
    public void getSingleSpartanAsUser() {

        given().spec(dynamicReqSpec("user", "user"))//spec() is method whit accept Request specification
                .pathParam("id", 3)
                .when()
                .get("/spartans/{id}")
                .then().spec(dynamicResSpec(200));//spec() is method whit accept Response specification

    }
    /**
     *  Create GET_RBAC.csv
     *   username,password,id,statuscode
     *    admin,admin,3,200
     *    editor,editor,3,200
     *    user,user,3,200
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */
    /**
     *  Create DELETE_RBAC.csv
     *   username,password,id,statuscode
     *
     *    editor,editor,3,403
     *    user,user,3,403
     *    admin,admin,3,204
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */


    @ParameterizedTest
    @CsvFileSource(resources = "/GET_RBAC.csv" , numLinesToSkip = 1)
    public void TestDDT(String userName, String password, int id, int statusCode) {
        given().spec(dynamicReqSpec(userName, password))//spec() is method whit accept Request specification
                .pathParam("id", id )
                .when()
                .get("/spartans/{id}")
                .prettyPeek()
                .then().spec(dynamicResSpec(statusCode));//spec() is method whit accept Response specification

    }

    /**
     *  Create DELETE_RBAC.csv
     *   username,password,id,statuscode
     *
     *    editor,editor,3,403
     *    user,user,3,403
     *    admin,admin,3,204
     *
     *  Create a parameterized test to check RBAC for GET method
     *
     *
     */

    @ParameterizedTest
    @CsvFileSource(resources = "/DeleteRBAC.csv" , numLinesToSkip = 1)
    public void TestDeleteDDT(String userName, String password, int id, int statusCode) {
        given().spec(dynamicReqSpec(userName, password))//spec() is method whit accept Request specification
                .pathParam("id", id )
                .when()
                .delete("/spartans/{id}")
                .prettyPeek()
                .then().spec(dynamicResSpec(statusCode));//spec() is method whit accept Response specification

    }




}


