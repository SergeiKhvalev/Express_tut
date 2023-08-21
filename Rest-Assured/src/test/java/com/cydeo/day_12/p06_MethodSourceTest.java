package com.cydeo.day_12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;

public class p06_MethodSourceTest {
    //DDT with JUNIT5 with MethodSoursTest

    @ParameterizedTest
    @MethodSource("getNames")
    public void test1(String name) {

        System.out.println("name = " + name);
    }




    // now method in the same class, but we can store that method whereever we need in our project
    public static List<String> getNames(){

        List<String> nameList= Arrays.asList("Kimberly","King","TJ","Bond");

        return nameList;

        /* we can feed that method by date from DB or API responces
          - Can we read data from database
               - Create conn / run query / store them and feed Parameterized
          - Can we get data from 3rd party APIs (that we consume to get data and use into our API )
                - use Java knowledge + RestAssured
           - This makes method source more power than other
         */

    }
}


