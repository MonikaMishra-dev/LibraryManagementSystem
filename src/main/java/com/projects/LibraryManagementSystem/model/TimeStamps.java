package com.projects.LibraryManagementSystem.model;

import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class TimeStamps {
    protected Date createdOn;
    protected Date updatedOn;
}
