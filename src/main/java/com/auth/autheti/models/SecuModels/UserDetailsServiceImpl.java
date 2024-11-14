package com.auth.autheti.models.SecuModels;

import com.auth.autheti.models.UsersModel;
import com.auth.autheti.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {



    private IUsersRepo iUsersRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersModel usersModel = iUsersRepo.findByEmail(email);
        if (usersModel == null) {
            throw new UsernameNotFoundException("No existe el usuario");
        }
        System.out.println("Usuario encontrado: " + usersModel.getEmail()); // <-- Agrega este log
        return new SecurityUser(usersModel);
    }
}
