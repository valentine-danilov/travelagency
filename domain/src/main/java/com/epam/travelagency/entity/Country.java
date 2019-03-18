package com.epam.travelagency.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedQuery(name = "Country.findAll", query = "SELECT u FROM Country u")
@Table(name = "country", schema = "travelagency")
public class Country extends AbstractEntity {
    @NotBlank(message = "Country should have not empty name")
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
