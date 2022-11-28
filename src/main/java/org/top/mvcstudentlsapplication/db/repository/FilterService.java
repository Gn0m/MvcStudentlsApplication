package org.top.mvcstudentlsapplication.db.repository;

import java.util.List;

public interface FilterService<T> {

    List<T> findByContains(String match);
}
