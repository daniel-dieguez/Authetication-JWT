package com.auth.autheti.repository;

import com.auth.autheti.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUsersRepo extends JpaRepository<UsersModel, String> {

    UsersModel findByName(String name);


}
