package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.springboot.model.Faculty;
import java.util.List;


public interface FacultyRepository extends JpaRepository<Faculty, Integer>
{
	public Faculty findByUsernameAndPassword(String username, String password);
	
	public boolean existsByEmail(String email);
	boolean existsByEmailAndUsername(String email, String username);
}
