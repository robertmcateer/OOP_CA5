package com.dkit.oopca5.server;

import java.util.List;

public interface StudentCoursesDaoInterface {
    
    public List<String> findCoursesForUser(int caoNumber) throws DaoException;
    public void updateCoursesForUser(int caoNumber, List<String> courses) throws DaoException;

}
