package com.taxiapp.taxiapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.User;
import java.util.List;


public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByUserId(Long userId);
    List<User> findByUsername(String username);
}
