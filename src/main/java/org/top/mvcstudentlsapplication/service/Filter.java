package org.top.mvcstudentlsapplication.service;

import org.springframework.stereotype.Component;
import org.top.mvcstudentlsapplication.db.repository.FilterService;

import java.util.List;

@Component
public class Filter<T> {

    private String match = "";

    public String getMatch() {
        return match;
    }

    public void setMatch(String match) {
        this.match = match;
    }

    public List<T> getFiltered(FilterService<T> service) {
        return service.findByContains(match);
    }
}
