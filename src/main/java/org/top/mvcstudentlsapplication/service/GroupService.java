package org.top.mvcstudentlsapplication.service;

import org.springframework.stereotype.Service;
import org.top.mvcstudentlsapplication.db.entity.Group;
import org.top.mvcstudentlsapplication.db.entity.Student;
import org.top.mvcstudentlsapplication.db.repository.GroupRepository;
import org.top.mvcstudentlsapplication.db.repository.StudentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GroupService {

    private final GroupRepository repository;
    private final StudentsRepository studentsRepository;

    public GroupService(GroupRepository groupRepository, StudentsRepository studentsRepository) {
        this.repository = groupRepository;
        this.studentsRepository = studentsRepository;
    }

    public List<Group> listAllGroups() {
        return (List<Group>) repository.findAll();
    }

    public Group saveGroup(Group group) {
        return repository.save(group);
    }

    public Group findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteGroupById(Integer id) {

        Optional<Group> deleted = repository.findById(id);
        List<Student> students = studentsRepository.findByGroup_Id(id);
        students.forEach(student -> student.setGroup(null));

        deleted.ifPresent(repository::delete);
    }

    public Group findGroupById(int id) {
        return repository.findById(id).orElseGet(null);
    }
}
