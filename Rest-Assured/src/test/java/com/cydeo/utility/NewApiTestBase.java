package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


import static io.restassured.RestAssured.given;

public abstract class NewApiTestBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI="https://newsapi.org/v2"; // - default URL. if not defined another baseUrl. Default URL will be used

    }
}
