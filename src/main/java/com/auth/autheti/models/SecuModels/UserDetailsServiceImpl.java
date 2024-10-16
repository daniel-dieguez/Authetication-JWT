package com.auth.autheti.models.SecuModels;

import com.auth.autheti.models.UsersModel;
import com.auth.autheti.repository.IUsersRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {



    private IUsersRepo iUsersRepo;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        UsersModel usersModel = iUsersRepo.findByName(name);
        if (usersModel == null) {
            throw new UsernameNotFoundException("No existe el usuario");
        }
        System.out.println("Usuario encontrado: " + usersModel.getName()); // <-- Agrega este log
        return new SecurityUser(usersModel);
    }
}
