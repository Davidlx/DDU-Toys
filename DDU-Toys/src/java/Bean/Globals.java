/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public static int getNumOfToysRows() throws ClassNotFoundException, SQLException
    {
        // Input has to be same name as in db
        openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Toys]");
        
        int numRow = 0;
        if(rs != null) {
            rs.last();
            numRow = rs.getRow();
        }
        
        return numRow;    
    }
    
    public static Integer tryParse(String text) {
    try {
        return Integer.parseInt(text);
    } catch (NumberFormatException e) {
        return 0;
    }
}
}
