package com.cydeo.day_9;


import com.cydeo.shortvideo_task.POJO_deserialization.Spartan;
import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

/**
 * Given accept type is application/xml
 * When send the request /api/spartans
 * Then status code is 200
 * And content type is application/xml
 *   print firstname
 *   .....
 *   ...
 */

public class P01_SpartanXMLTest extends SpartanAuthTestBase {
    @Test
    public void test1(){

        given()
                .accept(ContentType.XML)// expect body type is XML
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans")
                ///.prettyPeek() // to see response
                .then()
                .statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", is("Mayrapatc1h"))
                .body("List.item[0].gender", is("Male"))
        ;






    }
    @DisplayName("GET /api/spartans with using XMLPath")
    @Test
    public void test2() {

       Response response=  given()
                .accept(ContentType.XML)// expect body type is XML
                .auth().basic("admin", "admin")
                .when()
                .get("/api/spartans");


       //Get response as XML format and save into XPMPath (same with JsonPath)
       XmlPath xmlPath = response.xmlPath(); // we can call needed method to store needed data from body in necessary data type

       // get first spartan name

      String name = xmlPath.getString("List.item[0].name");
        System.out.println("Name first spartan = " + name);


        //get 3rd spartan name

        String nameOfThirdSpartan2 = xmlPath.getString("List.item[2].name");

        System.out.println("Name second spartan = " + nameOfThirdSpartan2);
        System.out.println("Name 3 spartan = " + nameOfThirdSpartan2);

        // get last spartan name
        String nameOflastSpartan = xmlPath.getString("List.item[-1].name");
        System.out.println("Name last spartan = " + nameOflastSpartan);
        //same
      // String nameOfThirdSpartan = xmlPath.getString("List.item["+ xmlPath.getList("list").size() -1 +"].name");


        // get all spartan names
        List<String> listOfNames = xmlPath.getList("List.item.name");
        System.out.println("List of spartan`s name = " + listOfNames);


        //how many spartans

       int numberOfSpartannames = xmlPath.getList("List.item.name").size();
        System.out.println(numberOfSpartannames);
        int numberOfSpartans = xmlPath.getList("List.item").size();
        System.out.println(numberOfSpartans);


        // how many spartans we have
        List<Spartan> allSpartans = xmlPath.getList("List.item");
        // Deserilization still possbile to do it.We should use another dependencies or use some Java logic to store into POJO
        // We are not gonna touch this

        System.out.println("allSpartans.size() = " + allSpartans.size());

        /**
         * Do we know how many spartan we have ?
         *   - Can we create Loop ?
         *       - yes
         *      String spartanName=xmlPath.getString("List.item[i].name")
         *
         */




    }


    }
