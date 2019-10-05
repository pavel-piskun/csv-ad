package com.glispa.csvad.model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

@Data
public class Ad {
    @CsvBindByPosition(position = 0)
    private int index;// – integer, row number sequence
    @CsvBindByPosition(position = 1)
    private String id;// – string, unique identifier for the ad record
    @CsvBindByPosition(position = 2)
    private String name;// – string, short descriptive name
    @CsvBindByPosition(position = 3)
    private String description;// – string, long description
    @CsvBindByPosition(position = 4)
    private String category;// – string , ad category name
    @CsvBindByPosition(position = 5)
    private String campaign;// – string, id of the related campaign
    @CsvBindByPosition(position = 6)
    private String url;// – string, url to ad resource (image, video, etc..)
    @CsvBindByPosition(position = 7)
    private String nUrl;// – string, url to execute when user clicks the ad
    @CsvBindByPosition(position = 8)
    private String customerName;// – string, name of registered customer
}
