package com.cosmetology.application.repository;

import com.cosmetology.application.model.enums.AccessRole;
import com.cosmetology.application.model.role.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT r FROM Role r Where r.name = :roleName")
    Optional<Role> findByName(AccessRole roleName);

}
