package com.cydeo.utility;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;


public abstract class SpartanNewAuthTestBase {

  public static RequestSpecification reqSpes;
  public static ResponseSpecification resSpec;

    @BeforeAll
    public static void init(){

// we split URI + ad basePath - which common for all endPoint
        baseURI="http://54.167.63.56"; // - default URL. if not defined another baseUrl. Default URL will be used
        port = 7000;
        basePath = "/api";

        // baseUri+port+basePath

         reqSpes = given() // this is request specification
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic("admin", "admin");

         resSpec = expect().statusCode(200)
                .contentType(ContentType.JSON);// our response specification// common expectation from all Responses



    }

    public static RequestSpecification dynamicReqSpec (String  userName, String password){

     return    given() // this is request specification
                .log().all()
                .accept(ContentType.JSON)
                .auth().basic(userName, password);

    }

    public static ResponseSpecification dynamicResSpec (int  statusCode){

        return   expect().statusCode(statusCode)
                .contentType(ContentType.JSON);// our response specification// common expectation from all Responses;

    }


}
