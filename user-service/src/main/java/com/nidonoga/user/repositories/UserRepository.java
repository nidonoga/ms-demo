package com.nidonoga.user.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nidonoga.user.models.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {
	
	boolean existsByEmail(String email);
	
	boolean existsByLogin(String email);

}
