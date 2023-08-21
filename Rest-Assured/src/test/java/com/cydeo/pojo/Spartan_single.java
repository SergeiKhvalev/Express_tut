package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = "id", allowSetters = true) // that annotation help ignore inizialisation of id when we create object (if we do not initialize id intendly , but it allows to getter. So id will ignored for setters, but will be allowed fow getters
public class Spartan_single {

    private int id;
    private String name;
    private String gender;
    private long phone;


    //boilerPlate - same for all classes, where we use default methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override // need if I want print it
    public String toString() {
        return "Spartan_single{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
