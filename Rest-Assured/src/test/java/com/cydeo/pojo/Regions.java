package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Regions {

    private List <Region> items;
  // private List<Link> links1;
  private boolean hasMore;
    private int limit;
    private int offset;
    private int count;
    private List<Link> links;





}
