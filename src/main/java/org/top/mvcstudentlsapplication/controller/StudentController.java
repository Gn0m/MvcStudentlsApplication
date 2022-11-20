package org.top.mvcstudentlsapplication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.mvcstudentlsapplication.db.entity.Assessment;
import org.top.mvcstudentlsapplication.db.entity.Group;
import org.top.mvcstudentlsapplication.db.entity.Student;
import org.top.mvcstudentlsapplication.db.entity.Subject;
import org.top.mvcstudentlsapplication.service.AssessmentService;
import org.top.mvcstudentlsapplication.service.GroupService;
import org.top.mvcstudentlsapplication.service.StudentService;
import org.top.mvcstudentlsapplication.service.SubjectService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


@Controller
public class StudentController {

    private final StudentService service;
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final AssessmentService assessmentService;

    public StudentController(StudentService service, GroupService groupService, SubjectService subjectService, AssessmentService assessmentService) {
        this.service = service;
        this.groupService = groupService;
        this.subjectService = subjectService;
        this.assessmentService = assessmentService;
    }

    @GetMapping("/students")
    public String showAllStudents(Model model) {
        List<Student> studentsList = service.listAllStudents();
        model.addAttribute("studentsList", studentsList);
        return "students/students-list";
    }

    @GetMapping("/students/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/student-form";
    }

    @PostMapping("/students/new")
    public String saveNewStudent(Integer groupId, Student student, RedirectAttributes ra) {

        return form(groupId, student, ra);
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteStudentById(id);
        ra.addFlashAttribute("message", "Student deleted");
        return "redirect:/students";
    }

    @GetMapping("/students/update/{id}")
    public String updateStudent(@PathVariable("id") Integer id, Model model) {
        Student student = service.findStudentById(id);
        model.addAttribute("student", student);
        return "students/student-form";
    }

    @PostMapping("/students/update/{id}")
    public String saveUpdateStudent(Integer groupId, Student student, RedirectAttributes ra) {

        return form(groupId, student, ra);
    }

    private String form(Integer groupId, Student student, RedirectAttributes ra) {

        if (groupId != null) {
            if (groupService.findGroupById(groupId) == null) {
                ra.addFlashAttribute("message",
                        "Student dont save, incorrect group id");
                return "redirect:/students";
            }
            Group group = groupService.findById(groupId);
            student.setGroup(group);
        }

        Student saved = service.saveStudent(student);

        ra.addFlashAttribute("message",
                "Student " + saved + " saved successfully");

        return "redirect:/students";
    }

    @GetMapping("/students/details/{id}")
    public String detailsStudent(@PathVariable("id") Integer id, Model model) {
        int AVG = 0;
        int AVGSubj;
        Map<Integer, String> avgs = new HashMap<>();

        Student student = service.findStudentById(id);
        List<Assessment> assessments = assessmentService.listByStudentId(id);
        for (Assessment assessment : assessments) {
            AVG += assessment.getAssessment();
        }
        if (AVG != 0) {
            AVG = AVG / assessments.size();
        }

        List<Subject> subjects = subjectService.listAllSubject();

        if (assessments.size() > 0) {

            for (Subject subject : subjects) {
                int count = 0;
                AVGSubj = 0;

                for (Assessment assessment : assessments) {

                    if (Objects.equals(assessment.getSubject().getSubjectName(), subject.getSubjectName())) {
                        count++;
                        AVGSubj += assessment.getAssessment();
                    }
                }

                if (AVGSubj != 0) {
                    AVGSubj = AVGSubj / count;
                }

                avgs.put(AVGSubj, subject.getSubjectName());
            }
        }
        model.addAttribute("map", avgs);
        model.addAttribute("student", student);
        model.addAttribute("avg", AVG);
        model.addAttribute("list", assessments);
        return "students/student-details";
    }


    @GetMapping("/students/grade/{id}")
    public String gradeStudent(@PathVariable String id, Model model) {
        model.addAttribute("subjects", subjectService.listAllSubject());
        return "students/student-grade-form";
    }

    @PostMapping("/students/grade/{id}")
    public String saveGradeStudent(@PathVariable("id") Integer id, Integer assessment, Integer subject, RedirectAttributes ra) {

        Student student = service.findStudentById(id);
        Subject subjectById = subjectService.findSubjectById(subject);
        Assessment assessmentDb = new Assessment();
        assessmentDb.setSubject(subjectById);
        assessmentDb.setAssessment(assessment);
        assessmentDb.setStudent(student);

        assessmentService.saveAssessment(assessmentDb);

        ra.addFlashAttribute("message",
                "Student " + student + " saved successfully");

        return "redirect:/students";
    }

}
