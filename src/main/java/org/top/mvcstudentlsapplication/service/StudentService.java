package org.top.mvcstudentlsapplication.service;


import org.springframework.stereotype.Service;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.db.entity.Student;
import org.top.mvcstudentlsapplication.db.repository.StudentsRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

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

        for (Assessment assessment: assessments) {

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
}
