package com.epam.travelagency.entity;

import com.epam.travelagency.enumeration.Role;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
@NamedQuery(name = "User.findOneByLogin", query = "SELECT u FROM User u WHERE u.login=:login")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@NamedNativeQuery(name = "User.buyTour", query = "INSERT INTO travelagency.usertour (user_id, tour_id) VALUES (?,?)")
@TypeDef(
        name = "pgsql_enum",
        typeClass = PostgreSqlEnumType.class
)
public class User extends AbstractEntity {
    @NotBlank(message = "Login should not be empty")
    @Length(min = 3, max = 45,
            message = "Login should have length from 3 to 45 symbols")
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @NotBlank(message = "Password should not be empty")
    @Length(min = 4, max = 35, message =
            "Password should have length from 6 to 35 symbols")
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @Column(name = "\"role\"", nullable = false)
    @Enumerated(EnumType.STRING)
    @Type(type = "pgsql_enum")
    private Role role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Review> reviewList;
}
