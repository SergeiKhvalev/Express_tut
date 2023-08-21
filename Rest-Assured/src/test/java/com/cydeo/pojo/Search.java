package com.cydeo.pojo;

import java.util.List;

public class Search {
    // that class we create to demonstrate how to store response body, which is List/Array of objects
    // Searach Class has relation with Spartan Class
    // It is called as HAS-A RelationShip


    private List<Spartan_single> content; // we create privat list<> - which type of object is Spartan_single (custom object type, which we create ourself

    private  int totalElement;// this is to store num of Elements - it came from response as wel

    public List<Spartan_single> getContent() {
        return content;
    }

    public void setContent(List<Spartan_single> content) {
        this.content = content;
    }

    public int getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(int totalElement) {
        this.totalElement = totalElement;
    }

    @Override
    public String toString() {
        return "Search{" +
                "content=" + content +
                ", totalElementsNum=" + totalElement +
                '}';
    }
}
