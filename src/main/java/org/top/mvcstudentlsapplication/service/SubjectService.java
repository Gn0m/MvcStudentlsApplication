package org.top.mvcstudentlsapplication.service;

import org.springframework.stereotype.Service;
import org.top.mvcstudentlsapplication.db.entity.Subject;
import org.top.mvcstudentlsapplication.db.repository.SubjectRepo;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepo repo;

    public SubjectService(SubjectRepo repo) {
        this.repo = repo;
    }

    public List<Subject> listAllSubject() {
        return (List<Subject>) repo.findAll();
    }

    public Subject findSubjectById(int id) {
        return repo.findById(id).orElseGet(null);
    }

    public Subject saveSubject(Subject subject) {
        return repo.save(subject);
    }

    public void deleteSubjectById(Integer id) {

        Optional<Subject> deleted = repo.findById(id);

        deleted.ifPresent(repo::delete);
    }
}
