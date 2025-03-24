package com.taxiapp.taxiapp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Admin;


public interface AdminRepository extends CrudRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
    Optional<Admin> findByEmail(String email);

}
