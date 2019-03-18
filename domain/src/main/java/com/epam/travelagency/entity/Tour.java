package com.epam.travelagency.entity;

import com.epam.travelagency.enumeration.TourType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Tour.findAll",
        query = "SELECT u FROM Tour u")
@NamedNativeQuery(
        name = "Tour.findAllByUser",
        query = "SELECT * FROM travelagency.tour t JOIN travelagency.usertour u on t.id = u.tour_id WHERE u.user_id=?",
        resultClass = Tour.class)
@Table(name = "tour")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSqlEnumType.class
)
public class Tour extends AbstractEntity {
    @Column(name = "photo")
    private String photo;

    @NotNull(message = "Tour date should not be null")
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Min(value = 1, message = "Tour duration should be >= 1")
    @NotNull(message = "Tour should have duration")
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotBlank(message = "Tour should have not empty description")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "Tour should have cost")
    @Min(value = 0, message = "Tour cost should be positive")
    @Column(name = "cost", nullable = false)
    private BigDecimal cost;

    @NotNull(message = "Tour should have type")
    @Enumerated(EnumType.STRING)
    @Column(name = "tour_type", nullable = false)
    @Type(type = "pgsql_enum")
    private TourType tourType;

    @NotNull(message = "Tour should have destination country")
    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @NotNull(message = "Tour should provide hotel")
    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;
}
