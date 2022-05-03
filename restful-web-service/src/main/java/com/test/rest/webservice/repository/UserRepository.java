package com.test.rest.webservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.rest.webservice.bean.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
