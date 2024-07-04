package com.auth.autheti.repository;

import com.auth.autheti.models.RolesModal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<RolesModal, String> {
}
