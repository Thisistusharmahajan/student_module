package com.user.name.school.dao;
import java.sql.*;
import com.user.name.school.dao.exceptions.*;

public class DAOConnection {
    private DAOConnection() {
    }

    public static Connection getConnection() throws DAOException
    {
        Connection connection = null;
        try
        {
            //if possible both connection String and username/password
            //should be loaded from school_db.cfg
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/t8821844329_db?useSSL=false&allowPublicKeyRetrieval=true", "t8821844329","tush1511");
        }catch(ClassNotFoundException classNotFoundException)
        {
            throw new DAOException(classNotFoundException.getMessage());
        }
        catch(SQLException sqlException)
        {
            throw new DAOException(sqlException.getMessage());
        }
        return connection;
    }
}

