package com.dkit.oopca5.server;


import com.dkit.oopca5.core.Student;

import java.util.List;
public interface StudentDaoInterface {
    public boolean registerstudent(Student s) throws DaoException;
    public boolean login(Student s) throws DaoException;
    public boolean isregistered(Student s) throws DaoException;
}
