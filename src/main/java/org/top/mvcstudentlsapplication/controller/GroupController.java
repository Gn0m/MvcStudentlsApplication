package org.top.mvcstudentlsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.mvcstudentlsapplication.db.entity.Group;
import org.top.mvcstudentlsapplication.service.GroupService;

import java.util.List;

@Controller
public class GroupController {

    private final GroupService service;

    public GroupController(GroupService service) {
        this.service = service;
    }

    @GetMapping("/groups")
    public String showAllStudents(Model model) {
        List<Group> groupsList = service.listAllGroups();
        model.addAttribute("groupsList", groupsList);
        return "groups-list";
    }

    @GetMapping("/groups/new")
    public String showNewStudentForm(Model model) {
        model.addAttribute("group", new Group());
        return "group-form";
    }


    @PostMapping("/groups/new")
    public String saveNewStudent(Group group, RedirectAttributes ra) {

        Group saved = service.saveGroup(group);

        ra.addFlashAttribute("message",
                "Group " + saved + " saved successfully");

        return "redirect:/groups";
    }

    @GetMapping("/groups/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteGroupById(id);
        ra.addFlashAttribute("message", "Group deleted");
        return "redirect:/groups";
    }

    @GetMapping("/groups/update/{id}")
    public String updateStudent(@PathVariable("id") Integer id, Model model) {
        Group group = service.findGroupById(id);
        model.addAttribute("group", group);
        return "group-form";
    }

    @PostMapping("/groups/update/{id}")
    public String saveUpdateStudent(Group group, RedirectAttributes ra) {


        Group groupUpdate = service.saveGroup(group);

        ra.addFlashAttribute("message",
                "Group " + groupUpdate + " saved successfully");

        return "redirect:/groups";
    }

    @GetMapping("/groups/details/{id}")
    public String detailsStudent(@PathVariable("id") Integer id, Model model) {
        Group group = service.findGroupById(id);
        model.addAttribute("group", group);
        return "group-details";
    }
}
