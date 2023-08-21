package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

//instead of keep writing getter/setter/toString  .....boilerplates we use lombok dependancy in POM.XML file it set up needed getter/setter/toString methods by @Getter, @Setter, @ToString annotations

@Getter
@Setter
@ToString
/*
@Getter
@Setter
@ToString instead those three annotation we can use one @Data - which include setter, getter, toString.....another annotations

 */

public class Region {

    @JsonProperty("region_id") // @JsonProperty - help us to help JsonPath find POJO class field - where need to assign data from response - it is can be very important when name of data in response has white space, but name of Java variable can has name with space (so @JsonProperty (name of data from jsonPath response which need to connect with name of POJO field)
    private int regionId;

    @JsonProperty("region_name")// @JsonProperty - help us to help JsonPath find POJO class field - where need to assign data from response - it is can be very important when name of data in response has white space, but name of Java variable can has name with space (so @JsonProperty (name of data from jsonPath response which need to connect with name of POJO field)
    private String regionName;
    private List<Link> links;
    public List<Link> list2 = new ArrayList<>();




}
