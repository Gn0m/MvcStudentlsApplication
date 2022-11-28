package org.top.mvcstudentlsapplication.db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assessment_t")
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime localDateTime;
    private Integer assessment;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;

    public Assessment() {
        localDateTime = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return localDateTime;
    }

    public void setDate(LocalDateTime date) {
        this.localDateTime = date;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getAssessment() {
        return assessment;
    }

    public void setAssessment(Integer assessment) {
        this.assessment = assessment;
    }

    @Override
    public String toString() {
        return "Id=" + id +
                ", date=" + localDateTime +
                ", assessment=" + assessment +
                ", subject=" + subject +
                ", student=" + student;
    }
}
