package org.top.mvcstudentlsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.mvcstudentlsapplication.db.entity.Assessment;

import java.util.List;

public interface AssessmentRepo extends CrudRepository<Assessment, Integer> {

    List<Assessment> findAssessmentByStudentId(Integer id);

    List<Assessment> findAssessmentBySubjectId(Integer id);
}
