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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ugo
 */
public class itemsServlet extends basicServlet {

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
        request = super.retrieveBasicAttributes(request);
        
       
        int choice = Integer.parseInt(request.getParameter("type"));
        
        switch(choice) {
            case 0:
                // Define Scope
                request.setAttribute("scope",request.getParameter("categoryname"));
                // Set Items
                ArrayList<Bean.SpecificBean.FirstHandItem> categoryItems = getCategoryFirstHandItems(Integer.parseInt(request.getParameter("subtype")));
                request.setAttribute("items", categoryItems);
                break;
            case 1:
                // Define Scope
                request.setAttribute("scope","Second Hand Products");
                // Set Items
                ArrayList<Bean.SpecificBean.SecondHandItem> secondHandItems = getSecondHandItems();
                request.setAttribute("items", secondHandItems);
                break;
            case 2:
                // Define Scope
                int lower = Integer.parseInt(request.getParameter("subtype1"));
                int upper = Integer.parseInt(request.getParameter("subtype2"));
                if(upper != 100) {
                    request.setAttribute("scope","Toys for age " + lower + " to " + upper);
                } else {
                    request.setAttribute("scope","Toys for age 12+");
                }
                
                // Set items         
                ArrayList<Bean.SpecificBean.FirstHandItem> ageItems = getAgeFirstHandItems(lower,upper);
                request.setAttribute("items", ageItems);
                break;               
            default:
                break;
        }        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("items.jsp"); 
        dispatcher.forward(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Get Items Methods">
    
    private ArrayList<Bean.SpecificBean.FirstHandItem> getCategoryFirstHandItems(int categoryId) throws ClassNotFoundException, SQLException{
        ArrayList<Bean.SpecificBean.FirstHandItem> result = new ArrayList<Bean.SpecificBean.FirstHandItem>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT stock.* \n" +
                                            "FROM [Stock] as stock\n" +
                                            "INNER JOIN [Toys] as toys\n" +
                                            "ON stock.Tid = toys.Tid\n" +
                                            "WHERE stock.Recycle = 0 AND toys.CategoryId = " + categoryId);
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        int i = 0;
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                    Bean.Stock item = new Bean.Stock();
                    item.setId(Integer.parseInt(rs.getString("Sid")));
                    item.setRecycled(Integer.parseInt(rs.getString("Recycle")));
                    item.setConDes(rs.getString("ConditionDescription"));
                    item.setAmount(Integer.parseInt(rs.getString("Amount")));
                    item.setPrice(Float.parseFloat(rs.getString("Price")));
                    item.setCid(Globals.tryParse(rs.getString("Cid")));
                    item.setTid(Integer.parseInt(rs.getString("Tid")));
                    Bean.SpecificBean.FirstHandItem featuredUsedItem = new Bean.SpecificBean.FirstHandItem();
                    featuredUsedItem.setItem(item);
                    result.add(featuredUsedItem);
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return result;
    }
   
    private ArrayList<Bean.SpecificBean.SecondHandItem> getSecondHandItems() throws ClassNotFoundException, SQLException{
        ArrayList<Bean.SpecificBean.SecondHandItem> result = new ArrayList<Bean.SpecificBean.SecondHandItem>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT stock.* \n" +
                                            "FROM [Stock] as stock\n" +
                                            "INNER JOIN [Toys] as toys\n" +
                                            "ON stock.Tid = toys.Tid\n" +
                                            "WHERE stock.Recycle = 1");
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        int i = 0;
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                    Bean.Stock item = new Bean.Stock();
                    item.setId(Integer.parseInt(rs.getString("Sid")));
                    item.setRecycled(Integer.parseInt(rs.getString("Recycle")));
                    item.setConDes(rs.getString("ConditionDescription"));
                    item.setAmount(Integer.parseInt(rs.getString("Amount")));
                    item.setPrice(Float.parseFloat(rs.getString("Price")));
                    item.setCid(Globals.tryParse(rs.getString("Cid")));
                    item.setTid(Integer.parseInt(rs.getString("Tid")));
                    Bean.SpecificBean.SecondHandItem featuredUsedItem = new Bean.SpecificBean.SecondHandItem();
                    featuredUsedItem.setUsedItem(item);
                    result.add(featuredUsedItem);
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return result;
    }
    
    private ArrayList<Bean.SpecificBean.FirstHandItem> getAgeFirstHandItems(int lower, int upper) throws ClassNotFoundException, SQLException{
        ArrayList<Bean.SpecificBean.FirstHandItem> result = new ArrayList<Bean.SpecificBean.FirstHandItem>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT stock.* \n" +
                                            "FROM [Stock] as stock\n" +
                                            "INNER JOIN [Toys] as toys\n" +
                                            "ON stock.Tid = toys.Tid\n" +
                                            "WHERE stock.Recycle = 0 AND (toys.Age BETWEEN "+lower+" AND "+upper+")");
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        int i = 0;
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                    Bean.Stock item = new Bean.Stock();
                    item.setId(Integer.parseInt(rs.getString("Sid")));
                    item.setRecycled(Integer.parseInt(rs.getString("Recycle")));
                    item.setConDes(rs.getString("ConditionDescription"));
                    item.setAmount(Integer.parseInt(rs.getString("Amount")));
                    item.setPrice(Float.parseFloat(rs.getString("Price")));
                    item.setCid(Globals.tryParse(rs.getString("Cid")));
                    item.setTid(Integer.parseInt(rs.getString("Tid")));
                    Bean.SpecificBean.FirstHandItem featuredUsedItem = new Bean.SpecificBean.FirstHandItem();
                    featuredUsedItem.setItem(item);
                    result.add(featuredUsedItem);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(itemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(itemsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(itemsServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(itemsServlet.class.getName()).log(Level.SEVERE, null, ex);
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
