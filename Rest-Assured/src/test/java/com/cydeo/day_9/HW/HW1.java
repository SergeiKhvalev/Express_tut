package com.cydeo.day_9.HW;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HW1 {
//http://www.omdbapi.com - base_URL

    // given apiKey 761a7bde
    // apikey=[yourkey]
    // example API: http://www.omdbapi.com/?i=tt3896198&apikey=761a7bde

    @Test
            public void test1(){
        Map<String, String> mapForParam = new HashMap<>();
        mapForParam.put("apikey", "761a7bde");
        mapForParam.put("s", "batman");
        mapForParam.put("r", "xml");
        System.out.println("Map for queryParams = " + mapForParam);

       Response response = given()
                .queryParams(mapForParam)
                .when()
                .get("http://www.omdbapi.com")
                //.prettyPeek()
                .then()
                .statusCode(200)
                .contentType("text/xml; charset=utf-8").extract().response();

        System.out.println("Content type of response = " + response.header("Content-Type"));

        XmlPath xmlPath = response.xmlPath(); // xmlPath() - method which came from response class (use to retrieve data from response in XML format) - use like JsonPath, the same methods (getInt(), getList(), getMap(), so on)

        List<String> allYearsOfMovie = response.xmlPath().getList("root.result.@year"); // if we will see response in xml/html format, where elements in body represented like html tags, attributes and values - use that "root.result.@year" to reach needed data from response, where '.' put between tags to go from parent to child and '@' - to define Attribute, which value need to get
        System.out.println("All movies years = " + allYearsOfMovie);

    }



}
