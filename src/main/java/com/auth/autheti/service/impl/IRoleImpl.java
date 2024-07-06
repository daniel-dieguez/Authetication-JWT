package com.auth.autheti.service.impl;

import com.auth.autheti.models.RolesModal;

import java.util.List;

public interface IRoleImpl {

    public List<RolesModal>findAll();
    public RolesModal findById (int id_role);
    public RolesModal save(RolesModal rolesModal);
    public void delete(RolesModal rolesModal);

}
