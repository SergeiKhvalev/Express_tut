package com.cydeo.day_6.HW;

import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class practice_with_Pojo extends HrTestBase {

    @Test
    public void Test1() {
        /*
    TASK
    Given accept is application/json
    When send request  to /regions endpoint
    Then status should be 200
            verify that region ids are 1,2,3,4
            verify that regions names Europe ,Americas , Asia, Middle East and Africa
            verify that count is 4
        -- Create Regions POJO
        -- And ignore field that you dont need
     */

        Response response = given().accept(ContentType.JSON).when().
                get("/regions").
                //    prettyPeek().
                        then().statusCode(200).contentType("application/json").extract().response(); // verified code status and body Type by hamcrest


        //  verify that region ids are 1,2,3,4
        JsonPath jsonPath = response.jsonPath();
        List<Integer> excpectedListOfRegionsId = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> actualListOfRegionsId = jsonPath.getList("items.region_id");
        System.out.println("Actual regions Id in list of String = " + actualListOfRegionsId);
        Assertions.assertEquals(excpectedListOfRegionsId, actualListOfRegionsId);

        //verify that regions names Europe ,Americas , Asia, Middle East and Africa
        List<String> expectedListOfRegionsName = new ArrayList<>(Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa"));

        List<String> actualListOfRegionsName = jsonPath.getList("items.region_name");
        System.out.println("Actual list of regions name = " + actualListOfRegionsName);

        Assertions.assertEquals(expectedListOfRegionsName, actualListOfRegionsName);

        //verify that count is 4
        List<Map<String, Object>> listOfAllJsonObjects = jsonPath.getList("items");
        System.out.println("Actual List of objects = " + listOfAllJsonObjects);
        int sizeOfActualList = listOfAllJsonObjects.size();
        System.out.println(sizeOfActualList);
        Assertions.assertEquals(4, sizeOfActualList);

    }

    @Test
    public void Test2() {

        Response response = given().accept(ContentType.JSON).when().
                get("/regions").
                // prettyPeek().
                        then().statusCode(200).contentType("application/json").extract().response(); // verified code status and body Type by hamcrest


        // Creation Object from POJO class Regions
        JsonPath jsonPath = response.jsonPath();
        Regions regionsObject = jsonPath.getObject("", Regions.class);
        System.out.println("Regions = " + regionsObject);

        System.out.println("----------------------LINKS------------------------");
      List <Link> listOfLinks =  regionsObject.getLinks();
        System.out.println("List of links = " + listOfLinks);
        System.out.println(listOfLinks.get(3));



     List<Region> list = jsonPath.getList("items", Region.class);
       System.out.println(list);


    }


}
