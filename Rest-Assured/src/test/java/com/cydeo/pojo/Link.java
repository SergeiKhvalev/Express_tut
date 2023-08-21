package com.cydeo.pojo;

//instead of keep writing getter/setter/toString  .....boilerplates we use lombok dependancy in POM.XML file it set up needed getter/setter/toString methods by @Getter, @Setter, @ToString annotations

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



//instead of keep writing getter/setter/toString  .....boilerplates we use lombok dependancy in POM.XML file it set up needed getter/setter/toString methods by @Getter, @Setter, @ToString annotations
/*
@Getter
@Setter
@ToString instead those three annotation we can use one @Data - which include setter, getter, toString.....another annotations
 */
@Data
public class Link {

    private String rel;
    private String href;


    }

