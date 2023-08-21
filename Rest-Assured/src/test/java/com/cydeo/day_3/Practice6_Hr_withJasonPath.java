package com.cydeo.day_3;

import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Practice6_Hr_withJasonPath extends HrTestBase {

    @Test
    public void Test1(){

        // Practice with special object - JsonPath
        // we saved response as JSONPATH OBJECT


        Response response = RestAssured.given().get("/countries");

        //response.prettyPrint();

        Assertions.assertEquals(200, response.statusCode());

        // create jsonpath object

      JsonPath jsonPath = response.jsonPath();

      //get 3 th country name

        String thirdCountry = jsonPath.getString("items[2].country_name");
        System.out.println(thirdCountry);

        // get me third country info

        System.out.println(jsonPath.getString("items[2]"));

        // Strore all info as map? Yes - later

        // Get 3 and 4 country name

        String twoNames = jsonPath.getString("items[2, 3].country_name");
        System.out.println(twoNames);

        // Get 3 and 4, 5,  country name
        String threeNames = jsonPath.getString("items[2, 3, 4].country_name");
        System.out.println(threeNames);



        // Get all country name where region_id = 2
      List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name"); // findAll {it.region_id==2} - help filtering data
        System.out.println("List countries filtered if region_id is 2 = " + list);




    }
}
