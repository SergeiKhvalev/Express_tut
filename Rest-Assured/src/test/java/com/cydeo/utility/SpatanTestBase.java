package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class SpatanTestBase {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI="http://54.167.63.56:8000"; // - default URL. if not defined another baseUrl. Default URL will be used

    }


}
