package com.human.userchat.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionFactory {


    private DatabaseConnectionFactory() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionFactory getInstance() {
        return Helper.FACTORY;
    }

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "arsen");
    }

    private static class Helper {
        private static final DatabaseConnectionFactory FACTORY = new DatabaseConnectionFactory();
    }
}
