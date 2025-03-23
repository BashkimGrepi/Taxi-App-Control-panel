package com.taxiapp.taxiapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.taxiapp.taxiapp.domain.Admin;


public interface AdminRepository extends CrudRepository<Admin, Long> {
    Admin findByUsername(String username);

}
