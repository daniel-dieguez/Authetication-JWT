package com.auth.autheti.repository;

import com.auth.autheti.models.RolesModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<RolesModal,Integer> {

}
