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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ugo
 */
public class cartServlet extends basicServlet {

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
        request=super.retrieveBasicAttributes(request);
        
        
        
        Boolean isCartEmpty = true;
        int sid = Integer.parseInt(request.getParameter("sid"));
        
//        int sid = 1;
//        int recycle = 0;

        if(sid != 0) {
            isCartEmpty = false;
            request.setAttribute("isCartEmpty",isCartEmpty);
            HttpSession session = request.getSession();
            int recycle = Integer.parseInt(request.getParameter("recycle"));
            try {
                if(recycle == 0) {
                    
                    // Get the session FirstHandList and check if null
                    ArrayList<Bean.SpecificBean.FirstHandCartItem> firstHandList = (ArrayList<Bean.SpecificBean.FirstHandCartItem>) session.getAttribute("firstHandCartList");
                    if(firstHandList == null) {
                        throw new NullPointerException();
                    }
                    
                    // Check if item already existst
                    Boolean exists = false;
                    int matchIndex = -1;
                    for(int i = 0; i < firstHandList.size(); i++){
                        if(sid == firstHandList.get(i).getFirstHandItem().getId()){
                            exists = true;
                            matchIndex = i;
                        }
                    }
                    
                    //If exist increase amount - if not: add to list
                    if(exists) {
                        
                        //Check if there enought amount
                        if(firstHandList.get(matchIndex).getItemAmount() < firstHandList.get(matchIndex).getFirstHandItem().getAmount()){
                            int amount = firstHandList.get(matchIndex).getItemAmount();
                            amount++;
                            firstHandList.get(matchIndex).setItemAmount(amount);
                        } else {
                            PrintWriter out = response.getWriter();
                            out.println("<script type=\"text/javascript\">alert(\"Reached stock limit\")</script>");
                            out.println("<script type=\"text/javascript\">window.location=\"cart?sid=0\";</script>");
                            out.close();
                        }
                    } 
                    else {
                       firstHandList = updateFirthHandCartList(firstHandList,sid); 
                    }
                    
                    //Set attribute
                    session.setAttribute("firstHandCartList", firstHandList);
                    
                } else {
                    // Get the session FirstHandList and check if null
                    ArrayList<Bean.SpecificBean.SecondHandCartItem> secondHandList = (ArrayList<Bean.SpecificBean.SecondHandCartItem>) session.getAttribute("secondHandCartList");
                    if(secondHandList == null) {
                        throw new NullPointerException();
                    }
                    
                    // Check if item already existst
                    Boolean exists = false;
                    int matchIndex = -1;
                    for(int i = 0; i < secondHandList.size(); i++){
                        if(sid == secondHandList.get(i).getUsedItem().getId()){
                            exists = true;
                            matchIndex = i;
                        }
                    }
                    
                    //If exist increase amount - if not: add to list
                    if(exists) {                        
                        //Check if there enought amount
                        if(secondHandList.get(matchIndex).getItemAmount() < secondHandList.get(matchIndex).getUsedItem().getAmount()){
                            int amount = secondHandList.get(matchIndex).getItemAmount();
                            amount++;
                            secondHandList.get(matchIndex).setItemAmount(amount);
                        } else {
                            PrintWriter out = response.getWriter();
                            out.println("<script type=\"text/javascript\">alert(\"Reached stock limit\")</script>");
                            out.println("<script type=\"text/javascript\">window.location=\"cart?sid=0\";</script>");
                            out.close();
                        }
                    } 
                    else {
                        secondHandList = updateSecondHandCartList(secondHandList,sid);
                    }
                    
                    
                    //Set attribute
                    session.setAttribute("secondHandCartList", secondHandList);
                }
            } catch(NullPointerException e) {
                if(recycle == 0) {
                    ArrayList<Bean.SpecificBean.FirstHandCartItem> firstHandList = new ArrayList<Bean.SpecificBean.FirstHandCartItem>();
                    firstHandList = updateFirthHandCartList(firstHandList,sid);
                    session.setAttribute("firstHandCartList", firstHandList);
                } else {
                    ArrayList<Bean.SpecificBean.SecondHandCartItem> secondHandList = new ArrayList<Bean.SpecificBean.SecondHandCartItem>();
                    secondHandList = updateSecondHandCartList(secondHandList,sid);
                    session.setAttribute("secondHandCartList", secondHandList);
                }
            } 
            
        } else {
            request.setAttribute("isCartEmpty",isCartEmpty);
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp"); 
        dispatcher.forward(request, response);
    }
    
    private ArrayList<Bean.SpecificBean.FirstHandCartItem> updateFirthHandCartList(ArrayList<Bean.SpecificBean.FirstHandCartItem> cartList, int sid) throws ClassNotFoundException, SQLException {
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Stock] WHERE Sid = " + sid);
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
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
                    Bean.SpecificBean.FirstHandCartItem featuredUsedItem = new Bean.SpecificBean.FirstHandCartItem();
                    featuredUsedItem.setItem(item);
                    featuredUsedItem.setItemAmount(1);
                    cartList.add(featuredUsedItem);
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return cartList;
    }
    
    private ArrayList<Bean.SpecificBean.SecondHandCartItem> updateSecondHandCartList(ArrayList<Bean.SpecificBean.SecondHandCartItem> cartList, int sid) throws ClassNotFoundException, SQLException {
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [Stock] WHERE Sid = " + sid);
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        
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
                    Bean.SpecificBean.SecondHandCartItem featuredUsedItem = new Bean.SpecificBean.SecondHandCartItem();
                    featuredUsedItem.setUsedItem(item);
                    featuredUsedItem.setItemAmount(1);
                    cartList.add(featuredUsedItem);
            }
        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return cartList;
    }

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
            Logger.getLogger(cartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(cartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(cartServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(cartServlet.class.getName()).log(Level.SEVERE, null, ex);
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
