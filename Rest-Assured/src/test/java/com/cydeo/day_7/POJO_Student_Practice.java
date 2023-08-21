package com.cydeo.day_7;

import com.cydeo.pojo.Student;
import com.cydeo.utility.CydeoStudentsTestBase;
import io.restassured.http.ContentType;
import io.restassured.internal.mapping.Jackson1Mapper;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class POJO_Student_Practice extends CydeoStudentsTestBase {
/*
    Given accept type is application/json
    And path param is 2
    When user send request /student/{id}
    Then status code should be 200
    And content type is application/json;charset=UTF-8
    And Date header is exist
    And Server header is enjoy
    And verify following
                firstName Mark
                batch 13
                major math
                emailAddress mark@email.com
                companyName Cydeo
                street 777 5th Ave
                zipCode 33222

       ignore all unecessery fields
     */
@DisplayName("GET /student/{id} 2 ")
@Test
public void test1() {

    JsonPath jsonPath = given().log().all().accept(ContentType.JSON)
            .and()
            .pathParam("id", 2).
            when().get("/student/{id}").
            then()
            .statusCode(200)
            .extract().jsonPath();

Student student = jsonPath.getObject("students[0]", Student.class); // because we try to create one Student object we define [)].  [0] need to define what we need one student to deserialize from POJO, if we will put "students" without index -> java will try to create list of Students, because students Java consider like Array - data structure, and by default JAVA try to convert it in list even if in Given Array only one element -> if we want to deserialize to single POJO object need to define index [0] or index needed element in array. Otherwise we will get ---- MismatchedInputException

    System.out.println(student);


    assertEquals("Mark",student.getFirstName());
    assertEquals(13,student.getBatch());
    assertEquals("math",student.getMajor());

    assertEquals("mark@email.com",student.getContact().getEmailAddress());
    assertEquals("Cydeo",student.getCompany().getCompanyName());

    assertEquals("777 5th Ave",student.getCompany().getAddress().getStreet());
    assertEquals(33222,student.getCompany().getAddress().getZipCode());









}


}
