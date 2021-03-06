package com.dkit.oopca5.core;

public class Course {


    private String courseId;   // e.g. DK821
    private String level;      // e.g. 7, 8, 9, 10
    private String title;      // e.g. BSc in Computing in Software Development
    private String institution; // Dundalk Institute of Technology

    //copy constructor
    public Course (Course course){
        this.courseId = course.courseId;
        this.level = course.level;
        this.title = course.title;
        this.institution = course.institution;
    }


    // Constructor
    public Course(String courseId, String level,String title, String institution) {
        this.courseId = courseId;
        this.level = level;
        this.title = title;
        this.institution = institution;
    }


    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getTitle() { return title;  }

    public void setTitle(String title) { this.title = title; }

    @Override
    public String toString() {
        return "{" +
                "courseId='" + courseId + '\'' +
                ", level='" + level + '\'' +
                ", Title='" + title + '\'' +
                ", Institution='" + institution + '\'' +
                '}';
    }
}
