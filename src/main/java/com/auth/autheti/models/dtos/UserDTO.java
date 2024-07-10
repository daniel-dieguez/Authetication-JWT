package com.auth.autheti.models.dtos;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserDTO {

    private String name;
    private String email;
    private String password;
    private int roleId;
}
