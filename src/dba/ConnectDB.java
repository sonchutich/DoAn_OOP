/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dba;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDB {

 public static Connection getConnection(String strServer, String strDatabase){
    Connection conn = null;
    try {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String dbURL = "jdbc:sqlserver://"+strServer+";databaseName="+strDatabase+";user=sa;password=sa;encrypt=true;trustServerCertificate=true;";
        conn = DriverManager.getConnection(dbURL);
    }catch(SQLException e){
        System.out.println("SQL Exception: " + e.toString());
    }catch(ClassNotFoundException ex){
        System.err.println("Cannot connect database, " + ex.toString());
    }
    return conn;
}


public static Connection getConnect(){
     String strServer;
     String strDatabase;
     Connection conn = getConnection(strServer="HONGHONGKUTE", strDatabase="db_Management_System");
     return conn;
}


}