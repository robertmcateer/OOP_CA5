package com.dkit.oopca5.server;
import com.dkit.oopca5.core.Course;
import com.dkit.oopca5.core.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlStudentDao extends MySqlDAO implements StudentDaoInterface {


    @Override
    public boolean login(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student g = null;
        boolean login = false;

        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "SELECT PASSWORD FROM STUDENT WHERE caoNumber = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, s.getCaoNumber());


            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next())
            {


                String password = rs.getString("PASSWORD");

                g = new Student(s.getCaoNumber(), s.getDayOfBirth(), password);

            }

            if (s.equals(g))
            {
                login = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("login() " + e.getMessage());
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
                throw new DaoException("login() " + e.getMessage());
            }
        }


        return login;
    }


    @Override
    public boolean registerstudent(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student g = null;
        boolean reg = false;


        try
        {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            con = this.getConnection();

            String query = "INSERT INTO STUDENT VALUES (?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, s.getCaoNumber());
            ps.setString(2, s.getDayOfBirth());
            ps.setString(3, s.getPassword());
            ps.executeUpdate();

            String query1 = "SELECT * FROM STUDENT WHERE CAONUMBER = ?";
            ps = con.prepareStatement(query1);
            ps.setInt(1, s.getCaoNumber());
            rs = ps.executeQuery();

            while (rs.next())
            {

                int cao = rs.getInt("CAONUMBER");
                String DOB = rs.getString("DOB");
                String password = rs.getString("PASSWORD");

                g = new Student(cao, DOB, password);

            }

            if (s.equals(g)){
                reg = true;
            }




        } catch (SQLException e)
        {
            throw new DaoException("Register() " + e.getMessage());
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
                throw new DaoException("register) " + e.getMessage());
            }
        }
        return reg;
    }

    @Override
    public boolean isregistered(Student s) throws DaoException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Student g = null;
        boolean reg = false;

        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM STUDENT WHERE CAONUMBER = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, s.getCaoNumber());
            rs = ps.executeQuery();

            while (rs.next())
            {
                int cao = rs.getInt("CAONUMBER");
                String DOB = rs.getString("DOB");
                String password = rs.getString("PASSWORD");
                g = new Student(cao,DOB,password);
            }

            if(s.equals(g)){
                reg = true;
            }
        } catch (SQLException e)
        {
            throw new DaoException("Register() " + e.getMessage());
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
                throw new DaoException("register) " + e.getMessage());
            }
        }
        return reg;
    }




}
