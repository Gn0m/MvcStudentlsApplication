package org.top.mvcstudentlsapplication.db.repository;

import org.springframework.data.repository.CrudRepository;
import org.top.mvcstudentlsapplication.db.entity.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {
}
