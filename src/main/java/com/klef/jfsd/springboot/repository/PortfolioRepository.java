package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Portfolio;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer>
{
	List<Portfolio> findByStudentId(int studentID);
	 void deleteByStudentId(int studentId);
}
