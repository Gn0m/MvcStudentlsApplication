package org.top.mvcstudentlsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.service.AssessmentService;

import java.util.List;

@Controller
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    @GetMapping("/assessments")
    public String showAllAssessments(Model model) {
        List<Assessment> assessmentList = service.listAllAssessment();
        model.addAttribute("assessmentList", assessmentList);

        return "assessment-list";
    }
}
