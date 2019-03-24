package com.epam.travelagency.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TourDTO {
    private String date;
    private Integer duration;
    private String cost;
    private Short stars;
    private String country;
}
