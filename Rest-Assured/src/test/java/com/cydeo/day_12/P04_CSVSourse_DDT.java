package com.cydeo.day_12;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

public class P04_CSVSourse_DDT {

    // DDT testing with @CsvSource annotation -> use when we need pass more than one set of data in our DDT testing procedures

    @ParameterizedTest
    @CsvSource({"1, 3, 4", // first set of data 1 + 3 = 4
                "2, 3, 5", // second set of data 2 + 3 = 5
                "3, 4, 7",// third set of data 3 + 4 = 7
                "5, 6, 11"})//forth set of data 4 + 6 = 11
    public void test1(int num1, int num2, int sum) {

        System.out.println(num1 + " + " + num2 + " = " + sum);

        Assertions.assertEquals(sum, (num1+num2));
    }

    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({ "NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void test2(String stateGiven, String cityGiven) {

       Response response = given()
                .pathParam("state", stateGiven)
                .pathParam("city", cityGiven)
                .when()
                .get("https://api.zippopotam.us/us/{state}/{city}");

        System.out.println("State = " + stateGiven);
        System.out.println("City = " + cityGiven);

       JsonPath jsonPath = response.jsonPath();

        List<String> listAll = jsonPath.getList("places.'place name'");
       for (String eachPlaceName : listAll){
           Assertions.assertTrue(eachPlaceName.contains(cityGiven));
       }
        System.out.println("Numbers of places = " + listAll.size());

// same result but we use HamCrest -. code more organized

            System.out.println("state = " + stateGiven);
            System.out.println("city = " + cityGiven);
            System.out.println("----------");
            int placeNumber = given().baseUri("https://api.zippopotam.us")
                    .pathParam("state", stateGiven)
                    .pathParam("city", cityGiven).
                    when().get("us/{state}/{city}").
                    then().statusCode(200)
                    .body("places.'place name'", everyItem(containsString(cityGiven)))
                    .extract().jsonPath().getList("places").size();


            System.out.println("------"+cityGiven+" has "+placeNumber+ " zipcode------");


        }

    }





