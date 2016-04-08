/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 *
 * @author Dennis T
 */
public class Globals {
    public static String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static String connString = "jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad072_db";
    public static String loginDB = "aiad072";
    public static String passwordDB = "aiad072";
    public static Connection con;
    public static Logger beanLog;
    public static void openConn() throws ClassNotFoundException, SQLException {
        Class.forName(sqlDriver);
        con = DriverManager.getConnection(connString, loginDB, passwordDB);
    }
    public static void closeConn() throws SQLException {
        if(con != null) {
            con.close();
        }
    }
}
