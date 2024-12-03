package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Education;
import java.util.List;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer>
{
	List<Education> findByStudentId(int studentId);
	 void deleteByStudentId(int studentId);
}
