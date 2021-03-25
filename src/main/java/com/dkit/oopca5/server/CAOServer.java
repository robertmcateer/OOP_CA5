package com.dkit.oopca5.server;

/* The server package should contain all code to run the server. The server uses TCP sockets and thread per client.
 The server should connect to a MySql database to register clients, allow them to login and choose courses
 The server should listen for connections and once a connection is accepted it should spawn a new CAOClientHandler thread to deal with that connection. The server then returns to listening
 */

import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;

import java.util.List;

public class CAOServer
{
    public static void main(String[] args) {
        CourseDaoInterface ICourseDAO = new MySqlCourseDao();
        StudentDaoInterface IStudentDAO = new MySqlStudentDao();
        try{
            System.out.println("find all courses");
            List<Course> courses = ICourseDAO.findAllCourses();

            if(courses.isEmpty())
                System.out.println("there is no courses");
            else
                displayCourses(courses);

        }catch (DaoException e){
            e.printStackTrace();
        }

        System.out.println("**********");
        try{
            System.out.println("find course");
            Course c = ICourseDAO.findCourse("DK845");

            if(c == null)
                System.out.println("there is no courses");
            else
                System.out.println(c);

        }catch (DaoException e){
            e.printStackTrace();
        }

        System.out.println("**********");
        try{
            System.out.println("login");
            Student s = new Student(80910958,"2001-03-2","Password123");
            boolean c = IStudentDAO.login(s);

            if(c == false)
                System.out.println("login failed");
            else
                System.out.println("login successful");

        }catch (DaoException e){
            e.printStackTrace();
        }

        System.out.println("**********");
        try{
            System.out.println("register");
            Student s = new Student(66666666,"2000-06-111","robert");
            boolean c = IStudentDAO.login(s);

            if(c == false)
                System.out.println("Student added");
            else
                System.out.println("student not added");

        }catch (DaoException e){
            e.printStackTrace();
        }

        System.out.println("**********");
        try{
            System.out.println("loggin");
            Student s = new Student(80910958,"2001-03-2","Password123");
            boolean c = IStudentDAO.login(s);

            if(c == false)
                System.out.println("Student logged in");
            else
                System.out.println("Student loggin failed");;
        }catch (DaoException e){
            e.printStackTrace();
        }












    }

    static void displayCourses(List<Course> courses){
        for(Course c : courses){
            System.out.println(c);
        }
    }

}
