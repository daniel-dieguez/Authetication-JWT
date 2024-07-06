package com.auth.autheti.service;

import com.auth.autheti.models.RolesModal;
import com.auth.autheti.repository.IRoleRepository;
import com.auth.autheti.service.impl.IRoleImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesService implements IRoleImpl {

    @Autowired
    private IRoleRepository iRoleRepository;


    @Override
    public List<RolesModal> findAll() {
        return this.iRoleRepository.findAll();
    }

    @Override
    public RolesModal findById(int id_role) {
        return this.iRoleRepository.findById(id_role).orElse(null);
    }

    @Override
    public RolesModal save(RolesModal rolesModal) {
        return this.iRoleRepository.save(rolesModal);
    }

    @Override
    public void delete(RolesModal rolesModal) {
        this.iRoleRepository.delete(rolesModal);

    }
}
