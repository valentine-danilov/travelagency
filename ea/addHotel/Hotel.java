package com.epam.travelagency.entity;

import com.epam.travelagency.enumeration.HotelFeature;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Hotel.findAll", query = "SELECT u FROM Hotel u")
@Table(name = "hotel", schema = "travelagency")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSqlEnumType.class
)
public class Hotel extends AbstractEntity {
    @NotBlank(message = "Hotel should have name")
    @Size(min = 1, max = 50, message = "Hotel's name should have length from 1 to 50 symbols")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Hotel should have info about stars")
    @Min(value = 1, message = "Number of stars can't be less than 1")
    @Max(value = 5, message = "Number of stars can't be more than 5")
    @Column(name = "stars", nullable = false)
    private Short stars;

    @NotNull(message = "Hotel should have website")
    @Length(min = 4, message = "Website can't be less then 4 symbols")
    @Column(name = "website", nullable = false)
    private String website;

    @NotNull(message = "Hotel should have latitude")
    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @NotNull(message = "Hotel should have longitude")
    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @NotNull(message = "Hotel should have some features")
    @Enumerated(EnumType.STRING)
    @Column(name = "feature", nullable = false)
    @Type(type = "pgsql_enum")
    private HotelFeature features;
}
