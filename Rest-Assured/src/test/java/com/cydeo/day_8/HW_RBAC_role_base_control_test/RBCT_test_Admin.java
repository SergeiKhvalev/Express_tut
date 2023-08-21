package com.cydeo.day_8.HW_RBAC_role_base_control_test;

import com.cydeo.utility.FakerSpartan;
import com.cydeo.utility.SpartanAuthTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RBCT_test_Admin extends SpartanAuthTestBase {
    /*
    SPARTAN AUTH
	YOUR IP:7000 --> It is SPARTAN application with atuhorization
	Roles
				     USERNAME           PASSWORD
		ADMIN  -->    admin               admin
		EDITOR -->    editor              editor
		USER   -->    user                user
     */


    static int expectedIdNewSpartan;
    static String expectedName;
    static String expectedGender;
    static long expectedPhone;


    @DisplayName("Testing valid POST admin role")
    @Order(value = 1)
    @Test
    public void TestValidPostAdminRole() {

        JsonPath jsonPath = given().accept(ContentType.JSON).
                //.log().body().
                        contentType("application/json"). // to define format of requested body
                auth().basic("admin", "admin"). // for authorization purposes
                body(FakerSpartan.getFakedStartan()). // define content of body POST   request
                when().
                //.log().ifValidationFails(). // to show reasons of failure
                        post("/api/spartans"). // endpoint of post request
                //  prettyPeek().
                        then().
                statusCode(201).contentType("application/json").extract().jsonPath(); // HamCrest verification + extraction of jsonPath object


        // just to have JavaFaker generated data + assigning global variables in order to pass them to next testes, especially expectedIdNewSpartan
        expectedIdNewSpartan = jsonPath.getInt("data.id");
        System.out.println("id newly added Spartan = " + expectedIdNewSpartan);

        expectedName = jsonPath.getString("data.name");
        System.out.println("Name of newly added Spartan = " + expectedName);

        expectedGender = jsonPath.getString("data.gender");
        System.out.println("Gender newly added Spartan = " + expectedGender);

        expectedPhone = jsonPath.getLong("data.phone");
        System.out.println("Phone newly added Spartan = " + expectedPhone);

    }

    @DisplayName("Testing valid GET recently POSTed admin role")
    @Order(value = 2)
    @Test
    public void getPostedSpartanAdminRole() {

        System.out.println("EXPECTED ID = " + expectedIdNewSpartan);
        System.out.println("Name of newly added Spartan = " + expectedName);
        System.out.println("Gender newly added Spartan = " + expectedGender);
        System.out.println("Phone newly added Spartan = " + expectedPhone);


        Response response = given().
                accept(ContentType.JSON).// define format of body of the accepted response
                pathParam("id", expectedIdNewSpartan).// pathParam with global defined id of new generated Spartan
                auth().basic("admin", "admin").//do not forget authorization
                        when().log().all().// to see all log info (all info obout request
                get("/api/spartans/{id}").// get request with needed endpoint
                then().statusCode(200). // HamCrest verification of statusCode
                contentType("application/json").//HamCrest verification of format of the response body
                extract().response();// extract response in order to have chance to make assertion by path() method
        response.prettyPrint();

        // single way to assert get response as actual with values, defined in Global Map
        Assertions.assertEquals(expectedName, response.path("name"));
        Assertions.assertEquals(expectedGender, response.path("gender"));
        Assertions.assertEquals(expectedPhone, (Long) response.path("phone"));
    }

    @DisplayName("Testing valid PUT admin role")
    @Order(value = 3)
    @Test
    public void TestValidPUTAdminRole() {

        Map<String, Object> newSpartan = FakerSpartan.getFakedStartan();
        System.out.println(newSpartan);


        given().
                contentType("application/json").// to define contentType of body which we sent
                // log().all(). // to print request body
                        pathParam("id", expectedIdNewSpartan).
                auth().basic("admin", "admin").// to authorization
                body(newSpartan).
                when().
                put("/api/spartans/{id}").
                // to print sent request body
                        then().
                statusCode(204);// status code cheking - // No Content --> It says I did successfuly but no response

// put changes verification
        JsonPath jsonPath = given().accept(ContentType.JSON).
                pathParam("id", expectedIdNewSpartan).
                auth().basic("admin", "admin").
                when().
                get("/api/spartans/{id}").
                then().
                statusCode(200).
                contentType("application/json").
                extract().jsonPath();
        // new name verification

        Assertions.assertEquals(newSpartan.get("name"), jsonPath.getString("name"));
        System.out.println(jsonPath.getString("name"));

        Assertions.assertEquals(newSpartan.get("gender"), jsonPath.getString("gender"));
        System.out.println(jsonPath.getString("gender"));

        Assertions.assertEquals(newSpartan.get("phone"), jsonPath.getString("phone"));
        System.out.println(jsonPath.getString("phone"));
    }

    @DisplayName("Testing valid PATCH admin role")
    @Order(value = 4)
    @Test
    public void TestValidPatchAdminRole() {

        Map<String, Object> fakedSpartan = FakerSpartan.getPartialFakedStartan();
        System.out.println("Spartan new name (expected) = " + fakedSpartan.get("name"));
        System.out.println("Spartan new gender (expected) = " + fakedSpartan.get("gender"));

        given().contentType("application/json").
                log().body().
                pathParam("id", expectedIdNewSpartan).
                auth().basic("admin", "admin").
                body(fakedSpartan).
                when().
                patch("/api/spartans/{id}").
                then().statusCode(204);


        // patch changes verification

        JsonPath jsonPath = given().
                accept("application/json").
                pathParam("id", expectedIdNewSpartan).
                auth().basic("admin", "admin").
                when().
                get("/api/spartans/{id}").
                then().statusCode(200).
                contentType("application/json").
                extract().jsonPath();

        // name verification
        System.out.println("Spartan actual name  = " + jsonPath.get("name"));

        Assertions.assertEquals(fakedSpartan.get("name"), jsonPath.getString("name"));

        // gender assertion
        System.out.println("Spartan actual gender = " + jsonPath.get("gender"));
        Assertions.assertEquals(fakedSpartan.get("gender"), jsonPath.getString("gender"));
    }

    @DisplayName("Testing valid DELETE admin role")
    @Order(value = 5)
    @Test
    public void TestValidDeleteAdminRole() {

        given().pathParam("id", expectedIdNewSpartan).
                auth().basic("admin", "admin").when().
                delete("api/spartans/{id}").
                then().
                statusCode(204);

        // deletion verification

        given().accept("application/json").
                pathParam("id", expectedIdNewSpartan).
                auth().basic("admin", "admin").
                when().
                get("api/spartans/{id}").
                then().
                statusCode(404);


    }


}
