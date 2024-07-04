package com.auth.autheti.service;

import com.auth.autheti.models.UsersModel;
import com.auth.autheti.repository.IUsersRepo;
import com.auth.autheti.service.impl.IUserImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserImpl {

    @Autowired
    private IUsersRepo iUsersRepo;

    @Override
    public List<UsersModel> findAll() {
        return this.iUsersRepo.findAll();
    }

    @Override
    public UsersModel save(UsersModel usersModel) {
        return this.iUsersRepo.save(usersModel);
    }

    @Override
    public void delete(UsersModel usersModel) {
        this.iUsersRepo.delete(usersModel);

    }
}
