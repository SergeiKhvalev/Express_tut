package com.cydeo.day_7;

import com.cydeo.utility.SpatanTestBase;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.LinkedHashMap;
import java.util.Map;

import  static io.restassured.RestAssured.given; // static import RestAssured


public class p03SpartanPUTPATCHDELETE extends SpatanTestBase {
    @DisplayName("PUT Spartan Single Spartan With Map ")
    @Test
    public void test1() {

        // just like we did in POST we can use other options as well (String,POJO )
        Map<String,Object> requestBody=new LinkedHashMap<>();
        requestBody.put("name","James Bond PUT");
        requestBody.put("gender","Male");
        requestBody.put("phone",1234567890);

        // PUT will update existing record so we choose one the existing ID.It may be different for you.JUST choose one of the existing
        int id=319;

        given().log().body()// since we are not getting any response we dont need it accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(requestBody).
                when().put("/api/spartans/{id}").
                then().statusCode(204); // No Content --> It says I did successfuly but no response

        // Create a GET method with same ID to see is it updated .

    }
}
