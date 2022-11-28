package org.top.mvcstudentlsapplication.service;

import org.springframework.stereotype.Service;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.db.repository.AssessmentRepo;

import java.util.List;
import java.util.Optional;

@Service
public class AssessmentService {

    private final AssessmentRepo repo;

    public AssessmentService(AssessmentRepo repo) {
        this.repo = repo;
    }

    public List<Assessment> listAllAssessment() {
        return (List<Assessment>) repo.findAll();
    }

    public Assessment saveAssessment(Assessment assessment) {
        return repo.save(assessment);
    }

    public List<Assessment> listByStudentId(Integer id) {
        return repo.findAssessmentByStudentId(id);
    }

    public List<Assessment> listBySubjectId(Integer id) {
        return repo.findAssessmentBySubjectId(id);
    }

    public Assessment findById(Integer id) {
        return repo.findById(id).orElseGet(null);
    }

    public void deleteAssessmentById(Integer id) {

        Optional<Assessment> deleted = repo.findById(id);

        Assessment assessment = deleted.orElseGet(null);
        assessment.setSubject(null);
        assessment.setStudent(null);
        repo.save(assessment);

        repo.delete(assessment);
    }
}
