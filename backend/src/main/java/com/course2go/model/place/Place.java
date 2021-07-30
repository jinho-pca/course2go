package com.course2go.model.place;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Place {
    @Id
    private Integer pid;
    
    private String placeName;
    private String placeAddress;
    private float placeLat;
    private float placeLng;
    private LocalDate placeDataDate;
    private String placeType;
}
