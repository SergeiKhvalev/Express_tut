package com.cydeo.day_3;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore

public class HW extends HrTestBase {
    /*
    TASK 1 :
- Given accept type is Json
- Path param value- US
- When users sends request to /countries
- Then status code is 200
- And Content - Type is Json
- And country_id is US
- And Country_name is United States of America
- And Region_id is 2
     */
    @Test
    public void Test1() {
        Response response = given().accept(ContentType.JSON).pathParam("country_id", "US").when().get("/countries/{country_id}");
        //  response.prettyPrint();

        // code status checking
        assertEquals(200, response.statusCode());
        //contentType assertion
        assertEquals("application/json", response.contentType());
        //country name checking

        String countryName = response.path("country_name");
        System.out.println("Contry name = " + countryName);
        assertEquals("United States of America", countryName);

        // region_id verification
        int region_id = response.path("region_id");
        System.out.println("Region idy = " + region_id);

        assertEquals(2, region_id);

    }

    /*

    TASK 2 :
    - Given accept type is Json
    - Query param value - q={"department_id":80}
    - When users sends request to /employees
    - Then status code is 200
    - And Content - Type is Json
    - And all job_ids start with 'SA'
    - And all department_ids are 80
    - Count is 25
         */
    @Test
    public void Test2() {
        Response response = given().accept(ContentType.JSON).queryParam("q", "{\"department_id\":80}").when().get("/employees"); // with queryParam in the end of Endpoint no any { }. that need only if we use pathParam.

        // response.prettyPrint();

        // to find how many countries returned = size of items Array.
        List<Object> list = response.path("items");
        System.out.println(list.size());

        // Assert code status
        assertEquals(200, response.statusCode());

        // Assert contentType
        assertEquals("application/json", response.contentType());

        //Assert all job_id starts with SA"
        List<String> allJob_id = response.path("items.job_id");

        for (String eachJobId : allJob_id) {
            assertTrue(eachJobId.startsWith("SA"));
        }
        // same result to assert allJobId
        boolean isAllJobId = allJob_id.stream().allMatch(eachJobId -> eachJobId.startsWith("SA"));
        assertTrue(isAllJobId);

        //All departments_id is 80

        List<Integer> allDepartmentsId = response.path("items.department_id");
        for (Integer eachDepartmentId : allDepartmentsId) {
            assertEquals(80, eachDepartmentId);
          //  System.out.println(eachDepartmentId);
        }
        // same result to assert All departments_id is 80
      boolean isAllDepId = allDepartmentsId.stream().allMatch(eachDepId -> eachDepId == 80);
        assertTrue(isAllDepId);

        // to find how many countries returned = size of items Array.
         list = response.path("items");
        System.out.println(list.size());


    }

    /*
TASK 3 :
- Given accept type is Json
-
    Query param value q= region_id 3
- When users sends request to /countries
- Then status code is 200
- And all regions_id is 3
- And count is 6
- And hasMore is false
- And Country_name are;
Australia,China,India,Japan,Malaysia,Singapore
     */
@Test
    public void Test3(){
    Response response = given().accept(ContentType.JSON).queryParam("q", "{\"region_id\":3}").when().get("/countries");
   // response.prettyPrint();

    //assert code status
    assertEquals(200, response.statusCode());

    //assertion contentType
    assertEquals("application/json", response.contentType());

    // all regions id is 2

    List<Integer> allRegionsId = response.path("items.region_id");

    boolean isThree = allRegionsId.stream().allMatch(each->each==3);
    assertTrue(isThree);
    // same result but with loop
    for (Integer eachRegionId : allRegionsId){
        System.out.println("RegionId = " + eachRegionId);
        assertEquals(3, eachRegionId);
    }

    boolean hasMore = response.path("hasMore");
    System.out.println(hasMore);
    assertFalse(hasMore);


    // Assertion all countries name
    List<String> expectedListOfCountries = new ArrayList<> (Arrays.asList("Australia","China","India","Japan","Malaysia","Singapore"));

    List <String> actualListOfCountries = response.path("items.country_name");
    assertEquals(expectedListOfCountries,actualListOfCountries);







            }

}
