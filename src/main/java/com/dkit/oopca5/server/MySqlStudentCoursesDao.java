package com.dkit.oopca5.server;

import com.dkit.oopca5.core.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentCoursesDao extends MySqlDAO implements StudentCoursesDaoInterface
{


    @Override
    public List<String> findCoursesForUser(int caoNumber)throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> courses = new ArrayList<>();

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT COURSEID FROM STUDENT_COURSES WHERE CAONUMBER = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {

                String courseid = rs.getString("COURSEID");
                courses.add(courseid);

            }
        } catch (SQLException e)
        {
            throw new DaoException("findCoursesForUser() " + e.getMessage());
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
                throw new DaoException("findCoursesForUser() " + e.getMessage());
            }
        }
        return courses;     // may be empty
    }

    @Override
    public void updateCoursesForUser(int caoNumber, List<String> courses)throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();
            String query = "DELETE * FROM STUDENT_COURSES WHERE CAONUMBER = ?  ;";
            ps = con.prepareStatement(query);
            ps.setInt(1, caoNumber);

            for(int i = 0; i < courses.size(); i ++)
            {
                String query1 = "INSERT INTO STUDENT_COURSES VALUES (?, ?,);";
                ps = con.prepareStatement(query1);
                ps.setInt(1, caoNumber);
                ps.setString(2, courses.get(i));


            }



            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {

                String courseid = rs.getString("COURSEID");
                courses.add(courseid);

            }
        } catch (SQLException e)
        {
            throw new DaoException("UpdateChoices() " + e.getMessage());
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
                throw new DaoException("UpdateChoices() " + e.getMessage());
            }
        }

    }

}
