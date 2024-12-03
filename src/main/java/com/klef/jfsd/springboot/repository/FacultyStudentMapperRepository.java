package com.klef.jfsd.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.klef.jfsd.springboot.model.Faculty;
import com.klef.jfsd.springboot.model.FacultyStudentMapper;
import com.klef.jfsd.springboot.model.Student;

import jakarta.transaction.Transactional;

@Repository
public interface FacultyStudentMapperRepository extends JpaRepository<FacultyStudentMapper, Integer>
{
	 @Query("SELECT COUNT(fsm) FROM FacultyStudentMapper fsm where fsm.faculty = ?1 and fsm.student = ?2")
	  public long checkfacultystudentmapping(Faculty faculty,Student student);
	 
	 @Query("SELECT fsm FROM FacultyStudentMapper fsm WHERE fsm.student.id = ?1")
	 List<FacultyStudentMapper> findMappingsByStudentId(int studentId);

	 
     @Query("SELECT fsm.student FROM FacultyStudentMapper fsm WHERE fsm.faculty.id = ?1")
      List<Student> findStudentsByFacultyId(int facultyId);


     @Modifying
     @Transactional
     @Query("DELETE FROM FacultyStudentMapper WHERE student.id = ?1")
     void deleteByStudentId(int studentId);
     
     @Modifying
     @Transactional
     @Query("DELETE FROM FacultyStudentMapper WHERE faculty.id = ?1")
     void deleteByFacultyId(int facultyId);

}
