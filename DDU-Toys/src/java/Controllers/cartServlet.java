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
        
        HttpSession session = request.getSession();
        Boolean isFirstHandCartEmpty = isFirstHandCartEmpty(session);
        Boolean isSecondHandCartEmpty = isSecondHandCartEmpty(session);
        
        int action;
        
        try {
            action = Integer.parseInt(request.getParameter("action"));
        } catch (Exception e) {
            action = 0;
        }
        // action = 0: Just pressed the navigation cart button
        if(action != 0) {    

            int sid = Integer.parseInt(request.getParameter("sid"));
            int recycle = Integer.parseInt(request.getParameter("recycle"));
            
            // Add to cart
            if(action == 1) {
                if(recycle == 0) {
                    isFirstHandCartEmpty = false;
                    AddToFirstHandItemCart(session, sid, response);
                } else {
                    isSecondHandCartEmpty = false;
                    AddToSecondHandItemCart(session, sid, response);
                }
            }
            
            // Delete
            if(action == 2){
                if(recycle == 0) {
                    if(DeleteFirstHandItem(session, sid)){
                        isFirstHandCartEmpty = true;
                    }
                } else {
                    if(DeleteSecondHandItem(session, sid)){
                        isSecondHandCartEmpty = true;
                    }
                }
            }
            
            // Increment
            if(action == 3){
                if(recycle == 0){
                    increaseFirstHandAmount(session, sid, response);
                } else {
                    increaseSecondHandAmount(session, sid, response);
                }
            }
            
            // Decrement
            if(action == 4) {
                if(recycle == 0){
                    if(decreaseFirstHandAmount(session, sid)){
                        isFirstHandCartEmpty = true;
                    }
                } else {
                    if(decreaseSecondHandAmount(session, sid)){
                        isSecondHandCartEmpty = true;
                    }
                }
            }
            
        }        
        // Set attributes
        session.setAttribute("isFirstHandCartEmpty", isFirstHandCartEmpty);
        session.setAttribute("isSecondHandCartEmpty", isSecondHandCartEmpty);
        
  
        calcFirstHandPriceAndAmount(session, isFirstHandCartEmpty);
        calcSecondHandPriceAndAmount(session, isSecondHandCartEmpty);      

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp"); 
        dispatcher.forward(request, response);
    }
    
    private Boolean isFirstHandCartEmpty(HttpSession session) {
       try {
            Boolean isEmpty = (Boolean) session.getAttribute("isFirstHandCartEmpty");
            if(isEmpty == null){
                throw new NullPointerException();
            }
            return isEmpty;
           
       } catch(NullPointerException e) {
           return true;
       }
    }
    
    private Boolean isSecondHandCartEmpty(HttpSession session) {
       try {
            Boolean isEmpty = (Boolean) session.getAttribute("isSecondHandCartEmpty");
            if(isEmpty == null){
                throw new NullPointerException();
            }
            return isEmpty;
           
       } catch(NullPointerException e) {
           return true;
       }
    }
    
    // Returns true if the list now is empty
    private Boolean decreaseFirstHandAmount(HttpSession session, int sid) {
        ArrayList<Bean.SpecificBean.FirstHandCartItem> firstHandList = (ArrayList<Bean.SpecificBean.FirstHandCartItem>) session.getAttribute("firstHandCartList");
        for(int i = 0; i < firstHandList.size(); i++){
            if(sid == firstHandList.get(i).getFirstHandItem().getId()) {
                if(1 < firstHandList.get(i).getItemAmount()){
                    int amount = firstHandList.get(i).getItemAmount();
                    amount--;
                    firstHandList.get(i).setItemAmount(amount);
                 //   calcFirstHandPriceAndAmount(session, firstHandList);
                    return false;
                } else {
                    firstHandList.remove(i);
                    if(firstHandList.isEmpty()){
                        session.removeAttribute("firstHandCartList");
                        return true;
                    }
                    return false;
                }
            }
        }
       // calcFirstHandPriceAndAmount(session, firstHandList);
        return false;
    }
    
    // Returns true if the list now is empty
    private Boolean decreaseSecondHandAmount(HttpSession session, int sid) {
        ArrayList<Bean.SpecificBean.SecondHandCartItem> secondHandList = (ArrayList<Bean.SpecificBean.SecondHandCartItem>) session.getAttribute("secondHandCartList");
        for(int i = 0; i < secondHandList.size(); i++){
            if(sid == secondHandList.get(i).getUsedItem().getId()) {
                if(1 < secondHandList.get(i).getItemAmount()){
                    int amount = secondHandList.get(i).getItemAmount();
                    amount--;
                    secondHandList.get(i).setItemAmount(amount);
                //    calcSecondHandPriceAndAmount(session, secondHandList);
                    return false;
                } else {
                    secondHandList.remove(i);
                    if(secondHandList.isEmpty()){
                        session.removeAttribute("secondHandCartList");
                        return true;
                    }
                    return false;
                }
            }
        }
   //     calcSecondHandPriceAndAmount(session, secondHandList);
        return false;
    }
    
    private void increaseFirstHandAmount(HttpSession session, int sid, HttpServletResponse response) throws IOException {
        ArrayList<Bean.SpecificBean.FirstHandCartItem> firstHandList = (ArrayList<Bean.SpecificBean.FirstHandCartItem>) session.getAttribute("firstHandCartList");
        for(int i = 0; i < firstHandList.size(); i++){
            if(sid == firstHandList.get(i).getFirstHandItem().getId()) {
                if(firstHandList.get(i).getItemAmount() < firstHandList.get(i).getFirstHandItem().getAmount()){
                    int amount = firstHandList.get(i).getItemAmount();
                    amount++;
                    firstHandList.get(i).setItemAmount(amount);
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">alert(\"Reached stock limit\")</script>");
                    out.println("<script type=\"text/javascript\">window.location=\"cart?action=0\";</script>");
                    out.close();
                }
            }
        }
 //       calcFirstHandPriceAndAmount(session, firstHandList);
    }
    
    private void increaseSecondHandAmount(HttpSession session, int sid, HttpServletResponse response) throws IOException {
        ArrayList<Bean.SpecificBean.SecondHandCartItem> secondHandList = (ArrayList<Bean.SpecificBean.SecondHandCartItem>) session.getAttribute("secondHandCartList");
        for(int i = 0; i < secondHandList.size(); i++){
            if(sid == secondHandList.get(i).getUsedItem().getId()) {
                if(secondHandList.get(i).getItemAmount() < secondHandList.get(i).getUsedItem().getAmount()){
                    int amount = secondHandList.get(i).getItemAmount();
                    amount++;
                    secondHandList.get(i).setItemAmount(amount);
                } else {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">alert(\"Reached stock limit\")</script>");
                    out.println("<script type=\"text/javascript\">window.location=\"cart?action=0\";</script>");
                    out.close();
                }
            }
        }
    //    calcSecondHandPriceAndAmount(session, secondHandList);
    }
    
    // Returns true if the list now is empty
    private Boolean DeleteFirstHandItem(HttpSession session, int sid) {
        ArrayList<Bean.SpecificBean.FirstHandCartItem> firstHandList = (ArrayList<Bean.SpecificBean.FirstHandCartItem>) session.getAttribute("firstHandCartList");
        for(int i = 0; i < firstHandList.size(); i++){
            if(sid == firstHandList.get(i).getFirstHandItem().getId()) {
                firstHandList.remove(i);
                if(firstHandList.isEmpty()){
                    session.removeAttribute("firstHandCartList");
                    return true;
                }
   //             calcFirstHandPriceAndAmount(session, firstHandList);
                return false;
            }
        }
   //     calcFirstHandPriceAndAmount(session, firstHandList);
        return false;
    }
    
    // Returns true if the list now is empty
    private Boolean DeleteSecondHandItem(HttpSession session, int sid) {
        ArrayList<Bean.SpecificBean.SecondHandCartItem> secondHandList = (ArrayList<Bean.SpecificBean.SecondHandCartItem>) session.getAttribute("secondHandCartList");
        for(int i = 0; i < secondHandList.size(); i++){
            if(sid == secondHandList.get(i).getUsedItem().getId()) {
                secondHandList.remove(i);
                if(secondHandList.isEmpty()){
                    session.removeAttribute("secondHandCartList");
                    return true;
                } 
   //             calcSecondHandPriceAndAmount(session, secondHandList);
                return false;
            }
        }
   //     calcSecondHandPriceAndAmount(session, secondHandList);
        return false;
    }
    
    private void AddToFirstHandItemCart(HttpSession session, int sid, HttpServletResponse response) throws ClassNotFoundException, SQLException, IOException{
            try {
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
                            out.println("<script type=\"text/javascript\">window.location=\"cart?action=0\";</script>");
                            out.close();
                        }
                    } 
                    else {
                       firstHandList = updateFirthHandCartList(firstHandList,sid); 
                    }
                    
                    //Set attribute
  //                  calcFirstHandPriceAndAmount(session, firstHandList);
                    session.setAttribute("firstHandCartList", firstHandList);
            } catch (NullPointerException e) {
                    ArrayList<Bean.SpecificBean.FirstHandCartItem> firstHandList = new ArrayList<Bean.SpecificBean.FirstHandCartItem>();
                    firstHandList = updateFirthHandCartList(firstHandList,sid);
     //               calcFirstHandPriceAndAmount(session, firstHandList);
                    session.setAttribute("firstHandCartList", firstHandList);
            }
        
    }
    
    private void AddToSecondHandItemCart(HttpSession session, int sid, HttpServletResponse response) throws IOException, ClassNotFoundException, SQLException{
            try {                // Get the session FirstHandList and check if null
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
                            out.println("<script type=\"text/javascript\">window.location=\"cart?action=0\";</script>");
                            out.close();
                        }
                    } 
                    else {
                        secondHandList = updateSecondHandCartList(secondHandList,sid);
                    }
                    
                    
                    //Set attribute
      //              calcSecondHandPriceAndAmount(session, secondHandList);
                    session.setAttribute("secondHandCartList", secondHandList);
            } catch (NullPointerException e) {
                    ArrayList<Bean.SpecificBean.SecondHandCartItem> secondHandList = new ArrayList<Bean.SpecificBean.SecondHandCartItem>();
                    secondHandList = updateSecondHandCartList(secondHandList,sid);
    //                calcSecondHandPriceAndAmount(session, secondHandList);
                    session.setAttribute("secondHandCartList", secondHandList);
            }
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
    
    private void calcFirstHandPriceAndAmount(HttpSession session, Boolean isEmpty) {
        float price = 0;
        int amount = 0;
        if(!isEmpty) {
            ArrayList<Bean.SpecificBean.FirstHandCartItem> list = (ArrayList<Bean.SpecificBean.FirstHandCartItem>) session.getAttribute("firstHandCartList");
            for(int i = 0; i < list.size(); i++){
                price += list.get(i).getItemAmount() * list.get(i).getFirstHandItem().getPrice();
                amount += list.get(i).getItemAmount();
            }
        }
        
        session.setAttribute("firstHandCartAmount", amount);
        session.setAttribute("firstHandCartPrice", String.format("%.2f", price));
    }
    
    private void calcSecondHandPriceAndAmount(HttpSession session, Boolean isEmpty) {
        int amount = 0;
        float price = 0;
        
        if(!isEmpty) {
            ArrayList<Bean.SpecificBean.SecondHandCartItem> list = (ArrayList<Bean.SpecificBean.SecondHandCartItem>) session.getAttribute("secondHandCartList");
            for(int i = 0; i < list.size(); i++){
                price += list.get(i).getItemAmount() * list.get(i).getUsedItem().getPrice();
                amount += list.get(i).getItemAmount();
            }
        }
        session.setAttribute("secondHandCartAmount", amount);
        session.setAttribute("secondHandCartPrice", String.format("%.2f", price));
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
