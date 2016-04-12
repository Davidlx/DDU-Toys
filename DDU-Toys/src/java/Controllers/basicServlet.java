/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.Globals;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ugo
 */
public class basicServlet extends HttpServlet {

    
    protected HttpServletRequest retrieveCate(HttpServletRequest request) throws ServletException, IOException, ClassNotFoundException, SQLException {
        ArrayList<Bean.Category> allCategories = getAllCategories();
        request.setAttribute("cate", allCategories);
        return request;
    }
    
    private ArrayList<Bean.Category> getAllCategories() throws ClassNotFoundException, SQLException{
        ArrayList<Bean.Category> result = new ArrayList<Bean.Category>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Category]");
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                Bean.Category cat = new Bean.Category();
                cat.setId(Integer.parseInt(rs.getString("CategoryId")));
                cat.setName(rs.getString("CategoryName"));
                result.add(cat);
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return result;
    }
    
    
}
