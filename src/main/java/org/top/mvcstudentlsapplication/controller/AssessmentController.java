package org.top.mvcstudentlsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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

        return "assessments/assessment-list";
    }

    @GetMapping("/assessments/delete/{id}")
    public String deleteAssessment(@PathVariable("id") Integer id, RedirectAttributes ra) {

        service.deleteAssessmentById(id);

        ra.addFlashAttribute("message", "Subject deleted");

        return "redirect:/assessments";
    }

    @GetMapping("/assessments/update/{id}")
    public String updateAssessment(@PathVariable("id") Integer id, Model model) {
        Assessment assessment = service.findById(id);
        model.addAttribute("assessment", assessment);
        return "assessments/assessment-form";
    }

    @PostMapping("/assessments/update/{id}")
    public String saveUpdateAssessment(Assessment assessment, RedirectAttributes ra) {

        service.saveAssessment(assessment);

        ra.addFlashAttribute("message",
                "Assessment " + assessment.getAssessment() + " saved successfully");

        return "redirect:/assessments";
    }

}
