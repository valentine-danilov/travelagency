package com.epam.travelagency.web.dto;

import com.epam.travelagency.enumeration.HotelFeature;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDTO {
    private String name;

    private String stars;

    private String website;

    private String latitude;

    private String longitude;

    private String features;
}
