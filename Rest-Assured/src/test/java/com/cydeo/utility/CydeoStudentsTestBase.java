package com.cydeo.utility;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public abstract class CydeoStudentsTestBase {
    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "https://api.training.cydeo.com"; // - default URL. if not defined another baseUrl. Default URL will be used                  https://api.training.cydeo.com

    }
}