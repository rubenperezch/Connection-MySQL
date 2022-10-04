package com.greenfoxacademy.connectionmysql.Models;

import javax.persistence.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Boolean urgent;
    private Boolean done;

    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @ManyToOne
    @JoinColumn (name = "assignee_id")
    private Assignee assignee;

    public Todo(String title) {
        this.title = title;
        this.urgent = false;
        this.done = false;
        this.dateCreated = new Date();
    }

    public Todo(String title, Boolean urgent, Boolean done) {
        this.title = title;
        this.urgent = urgent;
        this.done = done;
        this.dateCreated = new Date();
    }

    public Todo(Long id, String title, Boolean urgent, Boolean done) {
        this.id = id;
        this.title = title;
        this.urgent = urgent;
        this.done = done;
        this.dateCreated = new Date();
    }

    public Todo(Long id, String title, Boolean urgent, Boolean done, Assignee assignee) {
        this.id = id;
        this.title = title;
        this.urgent = urgent;
        this.done = done;
        this.assignee = assignee;
        this.dateCreated = new Date();
    }

    public Todo(Long id, String title, Boolean urgent, Boolean done, String description, Date dateCreated, Assignee assignee) {
        this.id = id;
        this.title = title;
        this.urgent = urgent;
        this.done = done;
        this.description = description;
        this.dateCreated = dateCreated;
        this.assignee = assignee;
        this.dateCreated = new Date();
        this.description = description;
    }

    public Todo() {
        this.urgent = false;
        this.done = false;
        this.dateCreated = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getUrgent() {
        return urgent;
    }

    public void setUrgent(Boolean urgent) {
        this.urgent = urgent;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Assignee getAssignee() {
        return assignee;
    }

    public void setAssignee(Assignee assignee) {
        this.assignee = assignee;
    }

}

