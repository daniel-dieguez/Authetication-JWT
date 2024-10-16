package com.auth.autheti.models.SecuModels;

import com.auth.autheti.models.UsersModel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@AllArgsConstructor
@NoArgsConstructor
public class SecurityUser implements UserDetails {

    private UsersModel usersModel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = "ROLE_" + usersModel.getRolesModal().getRole_name().toUpperCase();
        return Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }



    @Override
    public String getPassword() {
        return usersModel.getPassword();
    }

    @Override
    public String getUsername() {
        return usersModel.getName();
    }


    /*no usar*/
    @Override
    public boolean isAccountNonExpired() {
        return true; //debe de estar en true
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
