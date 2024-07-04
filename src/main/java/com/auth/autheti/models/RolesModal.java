package com.auth.autheti.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RolesModal {

    @Id
    @Column(name = "id_role")
    private int id_role;
    @Column(name = "role_name")
    private String role_name;

    @OneToMany(mappedBy = "rolesModal", fetch = FetchType.EAGER)
    private List<UsersModel> usersModels;

}
