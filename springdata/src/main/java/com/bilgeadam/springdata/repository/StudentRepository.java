package com.bilgeadam.springdata.repository;

import com.bilgeadam.springdata.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findStudentsByName(String name);
    List<Student> findStudentsByIsActive(Boolean isActive);


}
