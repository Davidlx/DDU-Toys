/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.Category;
import Bean.Globals;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.Supplier;

/**
 *
 * @author David Liu
 */
public class indexServlet extends basicServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request=super.retrieveCate(request);
        
        // Add featured toys to request
        ArrayList<Bean.Toy> featuredToys = getFeaturedToys();
        request.setAttribute("featuredItem", featuredToys);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
        dispatcher.forward(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="DB Methods">
        private ArrayList<Bean.Toy> getFeaturedToys() throws ClassNotFoundException, SQLException{
        ArrayList<Bean.Toy> result = new ArrayList<Bean.Toy>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Toys]");
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        //4 random numbers        
        ArrayList<Integer> numbers = new ArrayList<Integer>();   
        Random rand = new Random();
        while (numbers.size() < 4) {
            int random = rand.nextInt(numRow);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        
        int i = 0;
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                if(numbers.contains(i)) {
                    Bean.Toy featured = new Bean.Toy();
                    featured.setName(rs.getString("Name"));
                    featured.setDes(rs.getString("Description"));
                    featured.setPrice(Float.parseFloat(rs.getString("Price")));
                    featured.setAge(Integer.parseInt(rs.getString("Age")));
                    featured.setSex(Integer.parseInt(rs.getString("Sex")));
                    featured.setPicUrl(rs.getString("PicUrl"));
                    featured.setCategoryId(Integer.parseInt(rs.getString("CategoryId")));
                    result.add(featured);
                }
                i++;
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return result;
    }
    
    // </editor-fold>
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
        Globals.beanLog.info((Supplier<String>) e);
        } catch (SQLException e) {
            Globals.beanLog.info((Supplier<String>) e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
        Globals.beanLog.info((Supplier<String>) e);
        } catch (SQLException e) {
            Globals.beanLog.info((Supplier<String>) e);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
