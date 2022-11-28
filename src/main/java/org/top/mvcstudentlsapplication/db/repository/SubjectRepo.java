package org.top.mvcstudentlsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.mvcstudentlsapplication.db.entity.Subject;

public interface SubjectRepo extends CrudRepository<Subject, Integer> {


}
