package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Course;

import java.util.List;

public interface CourseDaoInterface {

    public List<Course> findAllCourses() throws DaoException;
    public Course findCourse(String id) throws DaoException;



}
