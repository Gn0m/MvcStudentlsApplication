package org.top.mvcstudentlsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.db.entity.Subject;
import org.top.mvcstudentlsapplication.service.AssessmentService;
import org.top.mvcstudentlsapplication.service.Filter;
import org.top.mvcstudentlsapplication.service.SubjectService;

import java.util.List;

@Controller
public class SubjectController {

    private final SubjectService service;
    private final AssessmentService assessmentService;
    private final Filter<Subject> containsFilter;

    public SubjectController(SubjectService service, AssessmentService assessmentService, Filter<Subject> containsFilter) {
        this.service = service;
        this.assessmentService = assessmentService;
        this.containsFilter = containsFilter;
    }

    @GetMapping("/subjects")
    public String showAllSubject(Model model) {
        List<Subject> subjectList = service.listAllSubject();
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("filter", containsFilter);
        return "subjects/subject-list";
    }

    @PostMapping("/subjects")
    public String showFilteredSubject(Filter<Subject> filter, Model model) {
        List<Subject> subjectList = filter.getFiltered(service);
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("filter", containsFilter);
        return "subjects/subject-list";
    }

    @GetMapping("/subjects/new")
    public String showNewSubjectForm(Model model) {
        model.addAttribute("subject", new Subject());
        return "subjects/subject-form";
    }

    @PostMapping("/subjects/new")
    public String saveNewSubject(Subject subject, RedirectAttributes ra) {

        Subject saved = service.saveSubject(subject);

        ra.addFlashAttribute("message",
                "Subject " + saved.getSubjectName() + " saved successfully");

        return "redirect:/subjects";
    }

    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(@PathVariable("id") Integer id, RedirectAttributes ra) {

        List<Assessment> assessments = assessmentService.listBySubjectId(id);

        for (Assessment assessment : assessments) {
            assessment.setSubject(null);
            assessmentService.saveAssessment(assessment);
        }

        service.deleteSubjectById(id);
        ra.addFlashAttribute("message", "Subject deleted");
        return "redirect:/subjects";
    }

    @GetMapping("/subjects/update/{id}")
    public String updateSubject(@PathVariable("id") Integer id, Model model) {
        Subject subject = service.findSubjectById(id);
        model.addAttribute("subject", subject);
        return "subjects/subject-form";
    }

    @PostMapping("/subjects/update/{id}")
    public String saveUpdateSubject(Subject subject, RedirectAttributes ra) {

        Subject saved = service.saveSubject(subject);

        ra.addFlashAttribute("message",
                "Subject " + saved.getSubjectName() + " saved successfully");

        return "redirect:/subjects";
    }

}
