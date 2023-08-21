package com.cydeo.day_4;

import com.cydeo.utility.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given; // static import RestAssured
import static org.junit.jupiter.api.Assertions.*; // static import, no need call Assetrion anymore

public class Practice1_HrWithJesonPath extends HrTestBase {
    /*
    given() body type - JSON
    and queryParam  limit is 200
    when user send requesrt /employees
    then user should see ...... what need assert
     */


    @DisplayName("get all employees?limit=200, use JsonPath")
    @Test
    public void Test1(){

        Response response = given().accept(ContentType.JSON).and().queryParam("limit", 200).when().get("/employees");
        //response.prettyPrint();

        // status code
        Assertions.assertEquals(200, response.statusCode());

        // content type

        Assertions.assertEquals("application/json", response.contentType());


        // get all emails
        JsonPath js = response.jsonPath(); //
        List <String> allEmails = js.getList("items.email");
        System.out.println(allEmails);
        int countEmails = allEmails.size();
        System.out.println("Count emails = " + countEmails);

        System.out.println("===============================================================");
        // get all emails who working as IT_PROG
     List <String> emailsITPROG =  js.getList("items.findAll{it.job_id =='IT_PROG'}.email");// loop - with condition ->{it.salary >=10000}.first_name", where "it" - define element in jsonObject (field in array element)
        System.out.println(emailsITPROG);
int emailsITPROGCount = emailsITPROG.size();
        System.out.println(emailsITPROGCount);

        System.out.println("===============================================================");
        // get all employees first names whoes salary is more then 10000

      List <Object> emplSalaryMoreThen10 =   js.getList("items.findAll{it.salary >=10000}.first_name");// path()???????



        // loop - with condition ->{it.salary >=10000}.first_name", where "it" - define element in jsonObject (field in array element)
        System.out.println(emplSalaryMoreThen10);

        System.out.println("===================================================");
        // get all info who has max salary

       String maxSalary = js.getString("items.max{it.salary}");// "items.max{it.salary}" where "it" each salary
        System.out.println(maxSalary);

        System.out.println("===================================================");

        //get first name who max salary
        String maxSalaryname = js.getString("items.max{it.salary}.first_name");//"items.max{it.salary}.first_name" - define to gat oly name from json onject
        System.out.println(maxSalaryname);

        System.out.println("===================================================");
        //get first name who min salary
        String minSalaryname = js.getString("items.min{it.salary}.first_name");//"items.min{it.salary}.first_name")
        System.out.println(minSalaryname);

        //get first name who avg salary
    // int avgSalaryname = js.getInt("items.average{it.salary}");
    //   System.out.println(avgSalaryname);

    }


}
