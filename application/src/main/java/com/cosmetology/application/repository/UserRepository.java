package com.cosmetology.application.repository;

import com.cosmetology.application.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select u from  User as u where u.id = :id ")
    Optional<User> findById(Long id);

    @Query(value = "select u from User as u where u.username = :username")
    Optional<User> findByLogin(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
