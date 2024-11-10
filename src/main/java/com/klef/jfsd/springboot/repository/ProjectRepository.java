package com.klef.jfsd.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.klef.jfsd.springboot.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>
{

}
