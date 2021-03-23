package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Course;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCourseDao extends MySqlDAO implements CourseDaoInterface{

    @Override
    public List<Course> findAllCourses() throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Course> courses = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM COURSE";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {

                String courseid = rs.getString("COURSEID");
                String level = rs.getString("LEVEL");
                String title = rs.getString("TITLE");
                String institution = rs.getString("INSTITUTION");
                Course c = new Course(courseid, level, title, institution);
                courses.add(c);
            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllCourses() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllCourses() " + e.getMessage());
            }
        }
        return courses;     // may be empty
    }

    @Override
    public Course findCourse(String id) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Course c = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM COURSE WHERE COURSEID = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, id);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {

                String courseid = rs.getString("COURSEID");
                String level = rs.getString("LEVEL");
                String title = rs.getString("TITLE");
                String institution = rs.getString("INSTITUTION");
                c = new Course(courseid, level, title, institution);

            }
        } catch (SQLException e)
        {
            throw new DaoException("findAllCourses() " + e.getMessage());
        } finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    freeConnection(con);
                }
            } catch (SQLException e)
            {
                throw new DaoException("findAllCourses() " + e.getMessage());
            }
        }


        return c;
    }


}
