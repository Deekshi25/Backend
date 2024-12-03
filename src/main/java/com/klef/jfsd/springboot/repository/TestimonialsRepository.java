package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Testimonials;

@Repository
public interface TestimonialsRepository extends JpaRepository<Testimonials, Integer>
{
	List<Testimonials> findByStudentId(int studentId);
	void deleteByStudentId(int studentId);
}
