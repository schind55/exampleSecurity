package edu.de.uni.passau.webeng.students.persistence.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;
import java.util.LinkedList;

@Entity
public class Course {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String title;
    private String description;

    @ManyToMany
    private Collection<Course> prerequisites = new LinkedList<>();

    public Course() {
    }

    public Course(String title, String description) {
        this.title = title;
        this.description = description;
	}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Collection<Course> prerequisites) {
        this.prerequisites = prerequisites;
    }
}