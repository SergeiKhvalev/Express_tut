package com.cydeo.day_4.HW;

import com.cydeo.utility.CydeoStudentsTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore

public class HW_students_batch_22 extends CydeoStudentsTestBase {
    //HOME WORK
    /*
     TASK
    Given accept type is application/json
    And path param is 22
    When user send request /student/batch/{batch}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is envoy
    And verify all the batch number is 22
     */

    @Test
    public void Test1() {

        Response response = given().accept(ContentType.JSON).pathParam("batch", 22).when().get("/student/batch/{batch}");
        //  response.prettyPrint();

        // code status verifying
        System.out.println(response.statusCode());
        assertEquals(200, response.statusCode());

        // contentType verification
        System.out.println(response.contentType());
        assertEquals("application/json;charset=UTF-8", response.contentType());


        // header Data exist
        System.out.println("Header Date presented = " + response.headers().hasHeaderWithName("date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));

        //And Server header is envoy
        String actualServer = response.header("server");
        System.out.println(actualServer);
        assertEquals("envoy", actualServer);

        //And verify all the batch number is 22
        JsonPath jsPath = response.jsonPath();
        List<Integer> listOfBatchNumber = jsPath.getList("students.batch");
        System.out.println(listOfBatchNumber);
        // Batch number verification
        boolean isAllPeopleFromBatch22 = true;
        int personNum = 1;
        for (Integer eachBatchNum : listOfBatchNumber) {
            if (eachBatchNum == 22) {
                System.out.println("Person number = " + personNum + "from batch 22 = " + isAllPeopleFromBatch22);
                personNum++;
            } else {
                System.out.println("Person number = " + personNum + "NOT from batch 22");
                break;
            }
        }

    }
}













