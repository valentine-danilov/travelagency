package com.epam.travelagency.web.dto;

import com.epam.travelagency.enumeration.TourType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
    String date;
    Integer duration;
    String description;
    String cost;
    String tourType;
    Integer hotel;
    Integer country;
}
