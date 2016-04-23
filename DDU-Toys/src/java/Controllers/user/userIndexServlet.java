/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.user;

import Bean.Customer;
import Bean.Globals;
import Bean.OrderItem;
import Bean.SpecificBean.Order;
import Bean.SpecificBean.OItem;
import Controllers.basicServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ugo
 */
public class userIndexServlet extends basicServlet {

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
        Customer c=(Customer)session.getAttribute("customer");
        int cid = c.getId();
        ArrayList<Order> orders = new ArrayList<Order>();
        
        try{
            orders = getOrdersForCostumer(cid);
        }
        catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        }
        
        request.setAttribute("listOrders", orders);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp"); 
        dispatcher.forward(request, response);
    }

    private ArrayList<Order> getOrdersForCostumer(int cid) throws ClassNotFoundException, SQLException {
        ArrayList<Order> result = new ArrayList<Order>();
        
        // Setup connection to db
        Globals.openConn();
        Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * FROM [CustomerOrder] WHERE Cid = " + cid);
        
        int numRow = 0;
        if(rs != null && rs.last() != false) {
            numRow = rs.getRow();
            rs.beforeFirst();
        }
        if(numRow > 0) {
 
           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           // Connection con2 = DriverManager.getConnection("jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad072_db", "aiad072", "aiad072");
            
            while(rs != null && rs.next() != false) {
                    // Create Order
                    Bean.CustomerOrder Order = new Bean.CustomerOrder();
                    Order.setId(Integer.parseInt(rs.getString("Oid")));
                    Order.setOrderTime(rs.getTimestamp("OrderTime"));
                    Order.setCid(Integer.parseInt(rs.getString("Cid")));
                    
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    Connection con2 = DriverManager.getConnection("jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad072_db", "aiad072", "aiad072");
                    
                    // Insert OrderItems
                    Statement stmt2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs2 = stmt2.executeQuery("SELECT * FROM [OrderItem] WHERE [Oid] = " + Order.getId());
                    int numRow2 = 0;
                    if(rs2 != null && rs2.last() != false) {
                        numRow2 = rs2.getRow();
                        rs2.beforeFirst();
                    }
                    
                    ArrayList<Bean.SpecificBean.OItem> Oitems = new ArrayList<Bean.SpecificBean.OItem>();
                    
                    if(numRow2 !=0) {
                        //for each item, we put it in the items list
                        while(rs2 != null && rs2.next() != false) {
                            //Set OrderItem
                            OrderItem item = new OrderItem();
                            item.setId(rs2.getInt("OItemId"));
                            item.setRecycled(Integer.parseInt(rs2.getString("Recycled")));
                            item.setAmount(Integer.parseInt(rs2.getString("Amount")));
                            item.setPrice(Float.parseFloat(rs2.getString("Price")));
                            item.setDescription(rs2.getString("Description"));
                            item.setOid(Integer.parseInt(rs2.getString("Oid")));
                            item.setTid(Integer.parseInt(rs2.getString("Tid")));
                            item.setCid(Integer.parseInt(rs2.getString("Cid")));
                            
                            //Set Oitem
                            Bean.SpecificBean.OItem oitem = new Bean.SpecificBean.OItem();
                            oitem.setItemInfo(item);
                            
                            //Add Oitem to OItems
                            Oitems.add(oitem);
                        }
                    }
                    
                    Order finalOrder = new Order();
                    finalOrder.setOrderInfo(Order);
                    finalOrder.setItems(Oitems);
                    result.add(finalOrder);
                    
                    if(rs2 != null) {
                        rs2.close();
                    }
                con2.close();
                stmt2.close();
            }

        }
        
        if(rs != null){
            rs.close();
        }
            
        Globals.closeConn();
        
        return result;
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
            Logger.getLogger(userIndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(userIndexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(userIndexServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(userIndexServlet.class.getName()).log(Level.SEVERE, null, ex);
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
