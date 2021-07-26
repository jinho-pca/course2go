package com.course2go.model.place;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
    private Integer pid;
    private String placeName;
    private String placeAddress;
    private float placeLat;
    private float placeLng;
    private LocalDate placeDataDate;
    private String placeType;
}
