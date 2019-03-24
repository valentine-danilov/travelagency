package com.epam.travelagency.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review", schema = "travelagency")
@Entity
@NamedQuery(name = "Review.findAll", query = "select r from Review r")
public class Review extends AbstractEntity {
    @NotNull(message = "Review should have publication day")
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotBlank(message = "Review's text should not be empty!")
    @Column(name = "text", nullable = false)
    private String text;

    @NotNull(message = "Review should have user who wrote it")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull(message = "Review should have tour it is about")
    @ManyToOne
    @JoinColumn(name = "tour_id", nullable = false)
    private Tour tour;
}
