package org.top.mvcstudentlsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.mvcstudentlsapplication.db.entity.Student;

import java.util.List;

public interface StudentsRepository extends CrudRepository<Student, Integer> {

    List<Student> findByGroup_Id(Integer id);

}
