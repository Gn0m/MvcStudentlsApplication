package org.top.mvcstudentlsapplication.service;


import org.springframework.stereotype.Service;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.db.entity.Student;
import org.top.mvcstudentlsapplication.db.repository.FilterService;
import org.top.mvcstudentlsapplication.db.repository.StudentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements FilterService<Student> {

    private final StudentsRepository repository;
    private final AssessmentService assessmentService;

    public StudentService(StudentsRepository repository, AssessmentService assessmentService) {
        this.repository = repository;
        this.assessmentService = assessmentService;

    }

    public List<Student> listAllStudents() {
        return (List<Student>) repository.findAll();
    }

    public Student saveStudent(Student student) {
        return repository.save(student);
    }

    public void deleteStudentById(Integer id) {

        List<Assessment> assessments = assessmentService.listByStudentId(id);

        for (Assessment assessment : assessments) {

            assessment.setSubject(null);
            assessment.setStudent(null);
            assessmentService.saveAssessment(assessment);
            assessmentService.deleteAssessmentById(assessment.getId());

        }

        Optional<Student> deleted = repository.findById(id);

        deleted.ifPresent(repository::delete);
    }

    public Student findStudentById(int id) {
        return repository.findById(id).orElseGet(null);
    }

    @Override
    public List<Student> findByContains(String match) {
        if (match == null || match.equals(""))
            return (List<Student>) repository.findAll();
        return ((List<Student>) repository.findAll()).stream().filter(s -> s.getFirstName().contains(match)
                || s.getLastName().contains(match)).toList();
    }
}
