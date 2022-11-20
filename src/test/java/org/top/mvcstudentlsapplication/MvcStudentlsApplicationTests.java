package org.top.mvcstudentlsapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.top.mvcstudentlsapplication.db.entity.Group;
import org.top.mvcstudentlsapplication.db.entity.Student;
import org.top.mvcstudentlsapplication.db.repository.GroupRepository;
import org.top.mvcstudentlsapplication.db.repository.StudentsRepository;

@SpringBootTest
class MvcStudentlsApplicationTests {

    @Autowired
    private StudentsRepository studentsRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Test
    public void testCreateStudent() {
        // 1. созадим группу
        Group group = new Group();
        group.setGroupName("TEST GROUP");
        Group newGroup = groupRepository.save(group);
        System.out.println(newGroup);

        // 2. создадим студента
        Student newStudent = new Student();
        newStudent.setFirstName("TEST");
        newStudent.setLastName("STUDENT");
        newStudent.setGroup(group);
        Student saved = studentsRepository.save(newStudent);
        System.out.println(saved);
    }

}
