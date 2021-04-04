package com.dkit.oopca5.core.johnloane;

import static org.junit.Assert.assertTrue;

import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import com.dkit.oopca5.server.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public  void findCourseByIDTest()
    {
        System.out.println("findCourseByIDTest");
        CourseDaoInterface ICourseDAO = new MySqlCourseDao();
        try{
            Course courses = ICourseDAO.findCourse("DK845");
            String result = courses.getCourseId();
            String expResult = "DK845";
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public  void getAllCoursesTest()
    {
        CourseDaoInterface ICourseDAO = new MySqlCourseDao();
        try{
            List<Course> courses = ICourseDAO.findAllCourses();
            boolean result = false;
            if(!courses.isEmpty()){
                result = true;
            }
            boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void registerStudent()
    {
        System.out.println("registerStudent");

        StudentDaoInterface IStudentDAO = new MySqlStudentDao();
        try{
            Student s = new Student(12345448,"2001-11-20","robert2");
            Boolean result = false;
            if(IStudentDAO.registerstudent(s)) {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }



    @Test
    public void loginTest()
    {
        StudentDaoInterface IStudentDAO = new MySqlStudentDao();
        try{
            Student s = new Student(12349678,"1998-11-29","Password1");
            Boolean result = false;
            if(IStudentDAO.login(s)) {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void isRegisteredTest()
    {
        StudentDaoInterface IStudentDAO = new MySqlStudentDao();
        try{
            Student s = new Student(12349678,"1998-11-29","Password1");
            Boolean result = false;
            if(IStudentDAO.isregistered(s)) {
                result = true;
            }
            Boolean expResult = true;
            Assert.assertEquals(expResult,result);
        }catch (DaoException e)
        {
            e.printStackTrace();
        }

    }


}
