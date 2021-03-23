package com.dkit.oopca5.server;

/*
All of the database functionality should be here. You will need a DAO for each table that you are interacting with in the database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDAO
{
    public Connection getConnection() throws DaoException
    {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/oop_ca5";
        String username = "root";
        String password = "";
        Connection con = null;

        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException ex1)
        {
            System.out.println("Failed to find driver class " + ex1.getMessage());
            System.exit(1);
        }
        catch (SQLException ex2)
        {
            System.out.println("Connection failed " + ex2.getMessage());
            System.exit(2);
        }
        return con;
    }

    public void freeConnection(Connection con) throws DaoException
    {
        try
        {
            if (con != null)
            {
                con.close();
                con = null;
            }
        }
        catch (SQLException e)
        {
            System.out.println("Failed to free connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
