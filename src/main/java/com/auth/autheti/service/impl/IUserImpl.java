package com.auth.autheti.service.impl;

import com.auth.autheti.models.UsersModel;

import java.util.List;

public interface IUserImpl {

    public List<UsersModel> findAll();
    public UsersModel save(UsersModel usersModel);
    public void delete (UsersModel usersModel);
}
