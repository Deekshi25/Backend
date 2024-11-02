package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Milestone;

@Repository
public interface MilestoneRepository extends JpaRepository<Milestone, Integer>
{
	public Milestone findByName(String name);
	
}
