package com.psl.IGA.demo.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.psl.IGA.demo.project.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail (String email);
}
