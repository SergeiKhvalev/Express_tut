package com.cydeo.day_12;

import org.junit.jupiter.api.*;

public class P01_Junit5_annotationsLyfeCycle {

    @BeforeAll
    public static void beforeAll(){ // sould be static

        System.out.println("-------Before ALL is running----------------");
    }

    @BeforeEach()// run before each @Test
    public void initEach(){
        System.out.println("-------Before EACH TEST is running----------------");


    }


    @Test
    public void test1(){

        System.out.println("-------Test 1 is running----------------");

    }


   // @ Disabled // never run that test, can use without message
    @Disabled ("this is ignored")
    @Test
    public void test2(){
        System.out.println("-------Test 2 is running----------------");


    }

    @AfterAll //
    public static void destroy(){
        System.out.println("-------AFTER ALL 2 is running----------------");


    }
    @AfterEach()// run before each @Test
    public void afterEach(){
        System.out.println("-------After EACH TEST is running----------------");


    }

}
