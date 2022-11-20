package org.top.mvcstudentlsapplication.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.mvcstudentlsapplication.db.entity.Group;
import org.top.mvcstudentlsapplication.db.entity.Student;
import org.top.mvcstudentlsapplication.service.GroupService;
import org.top.mvcstudentlsapplication.service.StudentService;

import java.util.List;


@Controller
public class StudentController {

    private final StudentService service;
    private final GroupService groupService;

    public StudentController(StudentService service, GroupService groupService) {
        this.service = service;
        this.groupService = groupService;
    }

    @GetMapping("/students")
    public String showAllStudents(Model model) {
        List<Student> studentsList = service.listAllStudents();
        model.addAttribute("studentsList", studentsList);
        return "students-list";
    }

    @GetMapping("/students/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "student-form";
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
        return "student-form";
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
        Student student = service.findStudentById(id);
        model.addAttribute("student", student);
        return "student-details";
    }

}
