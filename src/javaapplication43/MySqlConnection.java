/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication43;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ardina
 */
public class MySqlConnection {
    public static Connection getConnection() throws Exception{
        String dbroot = "jdbc:mysql://";
        String hostName = "localhost:3306/"; 
        String dbName = "culinary";
        String dbUrl = dbroot + hostName + dbName;
        
        String hostUsername = "root";
        String hostPassword = "";
        
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = (Connection)DriverManager.getConnection(dbUrl, hostUsername, hostPassword);
        
        return con;
    }  
}
