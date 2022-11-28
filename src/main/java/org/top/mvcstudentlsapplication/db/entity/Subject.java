package org.top.mvcstudentlsapplication.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "subject_t")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subjectName;

    public Subject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "Id=" + id +
                ", subjectName='" + subjectName;
    }
}
