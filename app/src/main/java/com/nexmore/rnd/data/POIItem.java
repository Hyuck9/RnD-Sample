package com.nexmore.rnd.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class POIItem {

    private String itemName;
    private int tag;
    private double latitude;
    private double longitude;
    private int customImageResourceId;
    private int red;
    private int green;
    private int blue;
}
