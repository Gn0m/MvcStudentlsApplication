package org.top.mvcstudentlsapplication.service;

import org.springframework.stereotype.Service;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.db.repository.AssessmentRepo;

import java.util.List;

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

    public List<Assessment> listByStudentId(Integer id){
        return repo.findAssessmentByStudentId(id);
    }
}
