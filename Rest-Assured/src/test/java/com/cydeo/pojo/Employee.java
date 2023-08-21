package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


//instead of keep writing getter/setter/toString  .....boilerplates we use lombok dependancy in POM.XML file it set up needed getter/setter/toString methods by @Getter, @Setter, @ToString annotations
/*
@Getter
@Setter
@ToString instead those three annotation we can use one @Data - which include setter, getter, toString.....another annotations
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;
    private int salary;

}
