package com.glispa.csvad.model;

import lombok.Data;

@Data
public class Ad {
    private int index;// – integer, row number sequence
    private String id;// – string, unique identifier for the ad record
    private String name;// – string, short descriptive name
    private String description;// – string, long description
    private String category;// – string , ad category name
    private String campaign;// – string, id of the related campaign
    private String url;// – string, url to ad resource (image, video, etc..)
    private String nUrl;// – string, url to execute when user clicks the ad
    private String customerName;// – string, name of registered customer
}
