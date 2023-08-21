package com.cydeo.utility;

import com.github.javafaker.Faker;

import java.util.HashMap;
import java.util.Map;

public class FakerSpartan {

    public static Map<String, Object> getFakedStartan(){
        Faker faker = new Faker();

        Map <String, Object> fakedSpartan = new HashMap<>();
        fakedSpartan.put("name", faker.name().firstName());
        fakedSpartan.put("gender", faker.demographic().sex());
        fakedSpartan.put("phone", faker.phoneNumber().subscriberNumber(10));

        return fakedSpartan;
    }

    public static Map<String, Object> getPartialFakedStartan(){
        Faker faker = new Faker();

        Map <String, Object> partialFakedSpartan = new HashMap<>();
        partialFakedSpartan.put("name", faker.name().firstName());
        partialFakedSpartan.put("gender", faker.demographic().sex());

        return partialFakedSpartan;
    }


}
