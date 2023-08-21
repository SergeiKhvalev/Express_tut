package com.cydeo.shortvideo_task;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JSONPATH_navigation_in_response_body extends SpatanTestBase {


    /*
    Given accept type - json
    And pathParam spartan id is 11
    When user send a get request to /spartans/{id}
Then status code - 200
And contentType - is Json
And "id" : 11
"name": "Nona"
"gender": "Female"
"phone": 7959094216
     */


    @Test
    public void Test1() {


        // manually passed
        Response response = RestAssured.given().accept(ContentType.JSON).and().pathParam("id", 11).when().get("/api/spartans/{id}");

        response.prettyPrint();
 // code status checking with PATH() METHOD

 int actualeCodeStatus = response.statusCode();
        System.out.println("Code status = " + actualeCodeStatus);
        Assertions.assertEquals(200, actualeCodeStatus);

        //body format verifying with PATH() METHOD
        System.out.println("Body type = " + response.contentType());
        Assertions.assertEquals("application/json", response.contentType());

        // same verification but with JSONPATH
        // FIRST need wrap our respons in jsonPath object

        JsonPath jsPath = response.jsonPath();

        int jpId = jsPath.getInt("id");
        System.out.println("Json ID = " + jpId);

        String jsName = jsPath.getString("name");
        System.out.println("JS name = " + jsName);

        String jsGender = jsPath.getString("gender");
        System.out.println("JS gender = " + jsGender);

        long jsPhone = jsPath.getLong("phone");
        System.out.println("JS phone = " + jsPhone);

        //Assertion with JsPAth same as regular
        Assertions.assertEquals(11, jpId);
        Assertions.assertEquals("Nona", jsName);
        Assertions.assertEquals("Female", jsGender);
        Assertions.assertEquals(7959094216l, jsPhone);


    }
}
