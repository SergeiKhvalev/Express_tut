package com.cydeo.day_8.HW_RBAC_role_base_control_test;

import com.cydeo.pojo.Spartan_single;
import com.cydeo.shortvideo_task.POJO_deserialization.Spartan;
import com.cydeo.utility.FakerSpartan;
import com.cydeo.utility.SpartanAuthTestBase;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RBCT_Test_Editor extends SpartanAuthTestBase {


    static int idNewSpartan;
    static String newSpartanName;
    static String newSpartanGender;
    static Long newSpartanPhone;

    static Map<String, Object> putSpartan;
   static Map<String, Object> patchSpartan;

    @DisplayName("Testing valid POST editor role")
    @Order(value = 1)
    @Test
    public void TestValidPostEditorRole() {
        // Map<String, Object> newSpartan = FakerSpartan.getFakedStartan();
        //System.out.println("Newly created spartan = " + newSpartan);
        // Spartan created by instantiation POJO class

        Spartan_single newSpartan = new Spartan_single();
        newSpartan.setGender("Male");
        newSpartan.setName("Katerina");
        newSpartan.setPhone(9123456789l);
        System.out.println("Newly created spartan = " + newSpartan);

        JsonPath jsonPath = given().accept("application/json")
                .contentType("application/json")
                .auth().basic("editor", "editor")
                .when()
                .body(newSpartan)
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201).
                contentType("application/json")
                .extract().jsonPath();

        idNewSpartan = jsonPath.getInt("data.id");
        System.out.println("New Spartan id = " + idNewSpartan);

        newSpartanName = jsonPath.getString("data.name");
        System.out.println("New Spartan id = " + newSpartanName);

        newSpartanGender = jsonPath.getString("data.gender");
        System.out.println("New Spartan id = " + newSpartanGender);

        newSpartanPhone = jsonPath.getLong("data.phone");
        System.out.println("New Spartan id = " + newSpartanPhone);

    }

    @DisplayName("Testing valid GET new added Spartan editor role")
    @Order(value = 2)
    @Test
    public void getPostedSpartanEditorRole() {

        Response response = given()
                .accept("application/json")
                .pathParam("id", idNewSpartan)
                .auth().basic("editor", "editor")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        //Assertions properties of newly added Spartan
        //name verification
        System.out.println("Name of of Spartan which has been retrieved = " + response.jsonPath().getString("name"));
        Assertions.assertEquals(newSpartanName, response.path("name"));

        // gender verification
        System.out.println("Gender of of Spartan which has been retrieved = " + response.jsonPath().getString("gender"));
        Assertions.assertEquals(newSpartanGender, response.jsonPath().getString("gender"));

        // Assertion phone number
        System.out.println("Phone number of of Spartan which has been retrieved = " + response.jsonPath().getString("phone"));
        Assertions.assertEquals(newSpartanPhone, response.path("phone"));

    }


    @DisplayName("Testing valid PUT Spartan editor role")
    @Order(value = 3)
    @Test
    public void PutSpartanEditorRole() {
        // new Spartan fields genereted in Map <String, Object> type by static method, with Faker object
        putSpartan = FakerSpartan.getFakedStartan();
        System.out.println("Put Spartan body content = " + putSpartan);

        given()
                .contentType("application/json")
                .pathParam("id", idNewSpartan)
                .log().body()
                .auth().basic("editor", "editor")
                .body(putSpartan)
                // .when()
                .put("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @DisplayName("Testing valid GET after PUT execution editor role")
    @Order(value = 4)
    @Test
    public void getPutSpartanEditorRole() {

        JsonPath jsonPath = given().accept("application/json")
                .pathParam("id", idNewSpartan)
                .auth().basic("editor", "editor")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().jsonPath();

        // name assertion
        System.out.println("Actual name after put execution =" + jsonPath.getString("name"));
        Assertions.assertEquals(putSpartan.get("name"), jsonPath.getString("name"));

        // gender assertion
        System.out.println("Actual gender after put execution = " + jsonPath.getString("gender"));
        Assertions.assertEquals(putSpartan.get("gender"), jsonPath.getString("gender"));

        // phone assertion
        System.out.println("Actual phone number after put execution = " + jsonPath.getLong("phone"));
        Assertions.assertEquals(Long.parseLong((String) putSpartan.get("phone")), jsonPath.getLong("phone"));

    }

    @DisplayName("Testing valid PATCH execution editor role")
    @Order(value = 5)
    @Test
    public void patchSpartanEditorRole() {

        patchSpartan = FakerSpartan.getPartialFakedStartan();

        given()
                .pathParam("id", idNewSpartan)
                .auth().basic("editor", "editor")
                .log().body()
                .contentType("application/json")
                .body(patchSpartan)
                .when()
                .patch("/api/spartans/{id}")
                .then()
                .statusCode(204);
        System.out.println("Patch new name = " + patchSpartan.get("name"));
        System.out.println("Patch new gender = " + patchSpartan.get("gender"));

    }

    @DisplayName("Testing get PATCH  editor role")
    @Order(value = 6)
    @Test
    public void getPatchSpartanEditorRole() {

        Response response = given()
                .accept("application/json")
                .pathParam("id", idNewSpartan)
                .auth().basic("editor", "editor")
                .when()
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .extract().response();

        JsonPath jsonPath = response.jsonPath();

        //Assertion patched name
        System.out.println("Spartan patched name = " + jsonPath.getString("name") );
        Assertions.assertEquals(patchSpartan.get("name"), jsonPath.getString("name"));

        // Assertion Spartan gender
        System.out.println("Spartan patched gender = " + jsonPath.getString("gender"));
        Assertions.assertEquals(patchSpartan.get("gender"), jsonPath.getString("gender"));

    }

    @DisplayName("Testing  DELETE  editor role")
    @Order(value = 7)
    @Test
    public void deleteSpartanEditorRole() {

        given()
                .pathParam("id", idNewSpartan)
                .auth().basic("editor", "editor")
                .when()
                .delete("api/spartans/{id}")
                .then()
                .statusCode(403); // 403 - forbidden, because Editor do not authorized to Delete Spartans

    }
    @DisplayName("Testing  get info about DELETED  editor role")
    @Order(value = 8)
    @Test
    public void getDeletedSpartanEditorRole() {

      Response response =  given()
                .pathParam("id", idNewSpartan)
                .auth().basic("editor", "editor")
                .when()
                .get("api/spartans/{id}")
                .then()
                .statusCode(200)
              .extract().response(); // still can find patched Spartan, because in previous test, I as editor not authorized to delete spartan

        // id assertion
        System.out.println("Expected id = " + idNewSpartan);
        Assertions.assertEquals(idNewSpartan, response.jsonPath().getInt("id"));

        //Assertion name
        System.out.println("Last version of name = " + response.jsonPath().getString("name"));
        Assertions.assertEquals(patchSpartan.get("name"), response.jsonPath().getString("name"));

        //Assertion gender

        System.out.println("Last version of gender = " + response.jsonPath().getString("gender"));
        Assertions.assertEquals(patchSpartan.get("gender"), response.jsonPath().getString("gender"));

        // Assertion phone number
        System.out.println("Last version of phone number = " + putSpartan.get("phone"));
        Assertions.assertEquals(Long.parseLong((String)putSpartan.get("phone")), response.jsonPath().getLong("phone"));



    }



}
