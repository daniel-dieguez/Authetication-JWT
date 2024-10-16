package com.auth.autheti.service.impl;

import com.auth.autheti.models.UsersModel;

import java.util.List;
import java.util.Optional;

public interface IUserImpl {

    public List<UsersModel> findAll();
    public UsersModel findById(String id_user);
    public UsersModel save(UsersModel usersModel);
    public void delete (UsersModel usersModel);
}
