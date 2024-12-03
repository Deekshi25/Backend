package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Internships;

@Repository
public interface InternshipsRepository extends JpaRepository<Internships, Integer>
{
	List<Internships> findByStudentId(int studentId);
	 void deleteByStudentId(int studentId);
	
}
