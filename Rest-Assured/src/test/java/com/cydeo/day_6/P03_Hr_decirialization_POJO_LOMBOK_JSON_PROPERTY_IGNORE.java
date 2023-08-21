package com.cydeo.day_6;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Region;
import com.cydeo.utility.HrTestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class P03_Hr_decirialization_POJO_LOMBOK_JSON_PROPERTY_IGNORE extends HrTestBase {

    @DisplayName("Get regions to deserilization to POJO+LOMBOK+JSON PROPERTY")
    @Test
    public void Test1(){

       JsonPath jsonPath = RestAssured.given().get("/regions").
             // prettyPeek().
               then().statusCode(200).
                extract().jsonPath();


       Region regionOne = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region one = " + regionOne);

     System.out.println("Get links info from regionOne = " + regionOne.getLinks().get(0));

      System.out.println("Get first region name = " + regionOne.getRegionName());

        System.out.println("first link from region one = " + regionOne.getLinks().get(0).getHref());




    }

    @DisplayName("Get employee to deserialization to POJO with required fields")
    @Test
    public void Test2() {

        JsonPath jsonPath = RestAssured.given().get("/employees").
                // prettyPeek().
                        then().statusCode(200).
                extract().jsonPath();

        Employee employee = jsonPath.getObject("items[0]", Employee.class);
        System.out.println(employee);




    }

}
