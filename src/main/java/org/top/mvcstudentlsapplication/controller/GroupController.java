package org.top.mvcstudentlsapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.top.mvcstudentlsapplication.db.entity.Group;
import org.top.mvcstudentlsapplication.service.Filter;
import org.top.mvcstudentlsapplication.service.GroupService;

import java.util.List;

@Controller
public class GroupController {

    private final GroupService service;
    private final Filter<Group> containsFilter;

    public GroupController(GroupService service, Filter<Group> containsFilter) {
        this.service = service;
        this.containsFilter = containsFilter;
    }

    @GetMapping("/groups")
    public String showAllGroup(Model model) {
        List<Group> groupsList = service.listAllGroups();
        model.addAttribute("groupsList", groupsList);
        model.addAttribute("filter", containsFilter);
        return "groups/groups-list";
    }

    @PostMapping("/groups")
    public String showFilteredGroup(Filter<Group> filter, Model model) {
        List<Group> studentsList = filter.getFiltered(service);
        model.addAttribute("groupsList", studentsList);
        model.addAttribute("filter", containsFilter);
        return "groups/groups-list";
    }

    @GetMapping("/groups/new")
    public String showNewGroupForm(Model model) {
        model.addAttribute("group", new Group());
        return "groups/group-form";
    }


    @PostMapping("/groups/new")
    public String saveNewGroup(Group group, RedirectAttributes ra) {

        Group saved = service.saveGroup(group);

        ra.addFlashAttribute("message",
                "Group " + saved + " saved successfully");

        return "redirect:/groups";
    }

    @GetMapping("/groups/delete/{id}")
    public String deleteGroup(@PathVariable("id") Integer id, RedirectAttributes ra) {
        service.deleteGroupById(id);
        ra.addFlashAttribute("message", "Group deleted");
        return "redirect:/groups";
    }

    @GetMapping("/groups/update/{id}")
    public String updateGroup(@PathVariable("id") Integer id, Model model) {
        Group group = service.findGroupById(id);
        model.addAttribute("group", group);
        return "groups/group-form";
    }

    @PostMapping("/groups/update/{id}")
    public String saveUpdateGroup(Group group, RedirectAttributes ra) {


        Group groupUpdate = service.saveGroup(group);

        ra.addFlashAttribute("message",
                "Group " + groupUpdate + " saved successfully");

        return "redirect:/groups";
    }

    @GetMapping("/groups/details/{id}")
    public String detailsGroup(@PathVariable("id") Integer id, Model model) {
        Group group = service.findGroupById(id);
        model.addAttribute("group", group);
        return "groups/group-details";
    }
}
