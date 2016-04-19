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
import javax.servlet.http.HttpSession;

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
        request = super.retrieveBasicAttributes(request);
        
        // Add featured toys to request
        ArrayList<Bean.SpecificBean.FirstHandItem> featuredToys = getFeaturedToys(4);
        request.setAttribute("featuredItem", featuredToys);
        
        // Add used featured items
        ArrayList<Bean.SpecificBean.SecondHandItem> featuredUsedItems = getFeaturedRecycledItems(4);
        request.setAttribute("featuredUsedItem", featuredUsedItems);
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
        dispatcher.forward(request, response);
    }
    
    // <editor-fold defaultstate="collapsed" desc="DB Methods">
    private ArrayList<Bean.SpecificBean.FirstHandItem> getFeaturedToys(int amount) throws ClassNotFoundException, SQLException{
        ArrayList<Bean.SpecificBean.FirstHandItem> result = new ArrayList<Bean.SpecificBean.FirstHandItem>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Stock] WHERE Recycle = 0");
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        //4 random numbers        
        ArrayList<Integer> numbers = new ArrayList<Integer>();   
        Random rand = new Random();
        while (numbers.size() < amount) {
            int random = rand.nextInt(numRow);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        
        int i = 0;
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                if(numbers.contains(i)) {
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
                i++;
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return result;
    }
        
    private ArrayList<Bean.SpecificBean.SecondHandItem> getFeaturedRecycledItems(int amount) throws ClassNotFoundException, SQLException{
        ArrayList<Bean.SpecificBean.SecondHandItem> result = new ArrayList<Bean.SpecificBean.SecondHandItem>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Stock] WHERE Recycle = 1");
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
        //4 random numbers        
        ArrayList<Integer> numbers = new ArrayList<Integer>();   
        Random rand = new Random();
        while (numbers.size() < amount) {
            int random = rand.nextInt(numRow);
            if (!numbers.contains(random)) {
                numbers.add(random);
            }
        }
        
        int i = 0;
        if(numRow > 0) {
            while(rs != null && rs.next() != false) {
                if(numbers.contains(i)) {
                    Bean.Stock item = new Bean.Stock();
                    item.setId(Integer.parseInt(rs.getString("Sid")));
                    item.setRecycled(Integer.parseInt(rs.getString("Recycle")));
                    item.setConDes(rs.getString("ConditionDescription"));
                    item.setAmount(Integer.parseInt(rs.getString("Amount")));
                    item.setPrice(Float.parseFloat(rs.getString("Price")));
                    item.setCid(Integer.parseInt(rs.getString("Cid")));
                    item.setTid(Integer.parseInt(rs.getString("Tid")));
                    
                    Bean.SpecificBean.SecondHandItem featuredUsedItem = new Bean.SpecificBean.SecondHandItem();
                    featuredUsedItem.setUsedItem(item);
                    result.add(featuredUsedItem);
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
        Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
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
