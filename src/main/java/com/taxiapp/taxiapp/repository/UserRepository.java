package com.taxiapp.taxiapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.User;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUserId(Long userId);

    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
