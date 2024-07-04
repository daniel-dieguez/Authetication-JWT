package com.auth.autheti.repository;

import com.auth.autheti.models.UsersModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsersRepo extends JpaRepository<UsersModel, String> {
}
