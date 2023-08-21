package com.cydeo.day_12;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class P03_DDT_with_ValueSourceTest {

    // type of DDT in JUNIT5

    @ParameterizedTest // need remove @Test
    @ValueSource(ints = {10, 20, 30, 40, 50})// single array
    // @Test
    // @Test
    public void test1(int numbers) {
        Assertions.assertTrue(numbers < 40);
    }

    @ParameterizedTest // need remove @Test
    @ValueSource(strings = {"Mike", "Sergei", "Katy", "Kimberly", "TJ", "Eva", "King"})// single array
    // @Test
    // @Test
    public void test2(String name) {
        System.out.println("name = " + name); // every execution will print one name (six time)
        // Assertions.assertTrue();
    }


    @ParameterizedTest(name = "{index} name is {0}")
// can give parameter with name. {index} - just number of execution. {0} - return value of given index in Array - strings ={"Mike", "Sergei", "Katy", "Kimberly", "TJ", "Eva", "King"}
    @ValueSource(strings = {"Mike", "Sergei", "Katy", "Kimberly", "TJ", "Eva", "King"})// single array
    // @Test
    public void test3(String name) {
        System.out.println("name = " + name); // every execution will print one name (six time)
        // Assertions.assertTrue();
    }



    // SEND GET REQUEST TO https://api.zippopotam.us/us/{zipcode}
    // with these zipcodes 22030,22031, 22032, 22033 , 22034, 22035, 22036
    // check status code 200
    @ParameterizedTest
    @ValueSource(ints = {22030, 22031, 22032, 22033, 22034, 22035, 22036}) // single array
    // @Test
    public void test5(int zipCode) {

        System.out.println("ZipCode = " + zipCode);
        given()
                .accept(ContentType.JSON)
                .when()
                //.baseUri("https://api.zippopotam.us/us")
                .get(" https://api.zippopotam.us/us/" + zipCode)
                .then()
                .statusCode(200);

        // another way (better way)
        /*
        given()
                .accept(ContentType.JSON)
                .when()
                //.baseUri("https://api.zippopotam.us")
                .pathParam("zipCode",zipCode)
                .get("/us/{zipCode}")
                .then()
                .statusCode(200);

                //same result
                given()
                .accept(ContentType.JSON)
                .when()
                //.baseUri("https://api.zippopotam.us") // same URL for all requests
                .basePath("/us") // same part of path for all endpoints
                .pathParam("zipCode",zipCode)
                .get("{zipCode}")
                .then()
                .statusCode(200);


         */




    }


}
