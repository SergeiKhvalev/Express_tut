package com.cydeo.day_6;

import com.cydeo.pojo.Search;
import com.cydeo.pojo.Spartan_single;
import com.cydeo.shortvideo_task.POJO_deserialization.Spartan;
import com.cydeo.utility.SpatanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class deserialization_with_POJO extends SpatanTestBase {
    @Test
    public void Test1() {

        Response response = given().accept(ContentType.JSON).pathParam("id", 15).when().
                get("/api/spartans/{id}").prettyPeek().then().statusCode(200).extract().response();

// with response
//we csn do POJO because help of Jackson-bind library
        Spartan_single spartanOne = response.as(Spartan_single.class);
        System.out.println("spartan one = " + spartanOne);

        System.out.println(spartanOne.getId());
        System.out.println(spartanOne.getName());
        System.out.println(spartanOne.getGender());
        System.out.println(spartanOne.getPhone());


        //with jsonPath

        JsonPath jsonPath = response.jsonPath();

        Spartan_single spartanWithJsonPath = jsonPath.getObject("", Spartan_single.class); // getObject() is Parent of all types and we specify that Object type by puting - Spartan_single (so Object type in this situation is type Spartan_single!!!)
        System.out.println("sp = " + spartanWithJsonPath);
        System.out.println(spartanWithJsonPath.getId());
        System.out.println(spartanWithJsonPath.getName());
        System.out.println(spartanWithJsonPath.getGender());
        System.out.println(spartanWithJsonPath.getPhone());


    }
@DisplayName("when response body consist from many objects how to put one object from response to POJO")
    @Test
    public void Test2() {

        Response response = given().accept(ContentType.JSON).
                get("/api/spartans/search").
               // prettyPeek().
                then().statusCode(200).extract().response(); // return multiple spartans -> response body will have many objects



        // RESPONSE
        System.out.println(" ----- RESPONSE - GET FIRST SPARTAN  -----");
        // response.as() --> Since we can not put path in here to get specific part of Response
        // we are no gonna do it

        // JSONPATH
        System.out.println(" ----- JSON - GET FIRST SPARTAN-----");
        JsonPath jsonPath = response.jsonPath();

        Spartan spartan = jsonPath.getObject("content[0]", Spartan.class);
        System.out.println("spartan = " + spartan);

    }

    @DisplayName("when response body consist from many objects how to store all objects in POJO")
    @Test
    public void Test3() {
        Response response = given().accept(ContentType.JSON).
                get("/api/spartans/search").
             // prettyPeek().
                        then().statusCode(200).extract().response(); // return multiple spartans -> response body will have many objects

        // JSONPATH STORE WHOLE RESPONSE WITH MULTIPLE OBJECTS IN POJO

        JsonPath jsonPath = response.jsonPath();

        // Searach Class has relation with Spartan Class
        // It is called as HAS-A RelationShip
      Search search =  jsonPath.getObject("", Search.class);// we put our response to custom POJO class, where We create List<Spartan> variable and int totalNum;

        System.out.println(search);
        System.out.println("Get total element num = " +search.getTotalElement()); // return one variable
        System.out.println("List of Spartans(content) = " + search.getContent());
        System.out.println("Get name of first spartan = " + search.getContent().get(0).getName()); // search.getContent() - getter from search object let us work with list<Spartan_single>
                                                                                                    // get(0) - define index of element of list <Spartan_single>
                                                                                                    // getName() - getter from Class Single spartan, which allow us to get value of name of the needed Spartan


// same with response.path()
        // since we are not providing path for response still we can use response.as() to make deserialization

        Search search1 = response.as(Search.class);


    }

    @DisplayName("deserialization to Put in list only Spartans from search endpoint (where 2 fields - content(all spartans) and totalElement field)  ")
    @Test
    public void Test4() {
        Response response = given().accept(ContentType.JSON).
                get("/api/spartans/search").
                // prettyPeek().
                        then().statusCode(200).extract().response(); // return multiple spartans -> response body will have many objects

             JsonPath jsonPath = response.jsonPath();

        List<Spartan> allSpartans= jsonPath.getList("content",Spartan.class);
        for (Spartan eachSpartan : allSpartans) {
            System.out.println("eachSpartan = " + eachSpartan);
        }

        System.out.println("allSpartans.get(0) = " + allSpartans.get(0));
        System.out.println("allSpartans.get(0).getId() = " + allSpartans.get(0).getId());
    }



    }




