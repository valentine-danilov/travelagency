package com.epam.travelagency.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    @Pattern(regexp = "[a-zA-Z0-9_.]{3,45}",
            message = "Login should contain only a-z,A-Z,'.','_'")
    private String login;
    @Pattern(regexp = "[a-zA-Z0-9_.]{4,35}",
            message = "Password should contain only a-z,A-Z,'.','_'")
    private String password;
}
