package com.cydeo.day_3;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore


public class Hr4_HrwithResponsPath extends HrTestBase {

    @DisplayName("Get request to countries with using Response path")
    @Test
    public void Test(){

        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":2}").when().get("/countries");

      //  response.prettyPrint();
        //print limit
      String limitInfo =  response.path("limit").toString();
        System.out.println("limitInfo = " + limitInfo);
        //print hasMore
        boolean hasMore = response.path("hasMore");
        System.out.println("hasMore = " + hasMore);
        //print second country_id
        String secondCountrYId = response.path("items[1].country_id");
        System.out.println("Second country id = " + secondCountrYId);

        // print 4th element country name
        String fourthCountryName = response.path("items[3].country_name");
        System.out.println("4th country name = " + fourthCountryName);

        // return 4th element href

        Object fouthElementHref = response.path("items[3].links[0].href"); // where links - is one more Array inside 4th element of itemsArray
        System.out.println("fouthElementHref = " + fouthElementHref);


        // All countries name
        List <String> allNames = response.path("items.country_name");
        System.out.println("All country name = " + allNames);



        //another way
        List<Map<String, Object>> allCountriesName = response.path("items");
        for (Map<String, Object> eachMap : allCountriesName){
            System.out.println(eachMap.get("country_name"));
        }
     //   System.out.println("All countries name = " + allCountriesName);

        // Verify all region_id == 2

        List<Integer> listOfAllId = response.path("items.region_id");
        boolean isTwo = true;
        for (Integer eachID : listOfAllId){
            if(eachID !=2){
                isTwo = false;
                break;
            }
        }
        System.out.println(isTwo);

        // same result
        for(Integer eachId : listOfAllId) {
            System.out.println("Id = " + eachId);
            Assertions.assertEquals(2, eachId); // if test passed that mean each of id = 2
        }

// same result with STREAM
        //stream() method has allMatch(lambda expression) method which we can use to check needed condition, if all elements of List is match certain condition

     boolean isAllIdEqualTwo = listOfAllId.stream().allMatch(eachElement -> eachElement == 2);
        Assertions.assertTrue(isAllIdEqualTwo);

    }


}
