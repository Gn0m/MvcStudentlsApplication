package org.top.mvcstudentlsapplication.db.entity;

import javax.persistence.*;



@Entity
@Table(name="student_t")
public class Student {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 200)
    private String firstName;

    @Column(nullable = false, length = 200)
    private String lastName;

    @ManyToOne()
    @JoinColumn(name="group_id")
    private Group group;


    public Student() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return id + " - " + firstName + " - " + lastName;
    }
}
