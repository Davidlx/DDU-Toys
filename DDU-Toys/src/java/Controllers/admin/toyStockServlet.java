/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.admin;

import Bean.Globals;
import Bean.Stock;
import Bean.Toy;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controllers.basicServlet;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David Liu
 */
public class toyStockServlet extends basicServlet {

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

        HttpSession session = request.getSession();
        Bean.Customer customer = (Bean.Customer) session.getAttribute("customer");
        if (customer == null || !customer.getIsAdmin()) {
            response.sendRedirect("../adminLogin?from=/DDU-Toys/admin/");
            return;
        }

        ArrayList<Stock> stocks = new ArrayList<Stock>();
        int tid = Integer.parseInt(request.getParameter("tid"));
        Toy currentToy = new Toy();
        currentToy.setId(tid);
        currentToy.getOnId();

        request.setAttribute("currentToy", currentToy);
        
        boolean displayNewStock=true;

        try {
            Globals.openConn();
            Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //retreive all orders of the user
            ResultSet rs = stmt.executeQuery("SELECT * FROM [Stock] WHERE [Tid] = " + tid);
            int numRow = 0;
            if (rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }
            if (numRow > 0) {
                while (rs != null && rs.next() != false) {
                    Stock tempStock = new Stock();
                    tempStock.setId(rs.getInt(1));
                    tempStock.getOnId();
                    stocks.add(tempStock);
                    if(tempStock.getRecycled()==0)
                        displayNewStock=false;
                }
            }
            if (rs != null) {
                rs.close();
            }
            Globals.closeConn();
        } catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        }

        request.setAttribute("stocks", stocks);
        
        request.setAttribute("displayNewStock", displayNewStock);

        RequestDispatcher dispatcher = request.getRequestDispatcher("toyStock.jsp");
        dispatcher.forward(request, response);
    }

    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request = super.retrieveBasicAttributes(request);

        HttpSession session = request.getSession();
        Bean.Customer customer = (Bean.Customer) session.getAttribute("customer");
        if (customer == null || !customer.getIsAdmin()) {
            response.sendRedirect("../adminLogin?from=/DDU-Toys/admin/");
            return;
        }

        String amount = request.getParameter("amount");
        String newPrice = request.getParameter("price");
        String tid = request.getParameter("tid");

        if (amount != null && !"".equals(amount)) {
            Toy toy = new Toy();
            toy.setId(Integer.parseInt(tid));
            toy.getOnId();

            Stock stock = new Stock();

            stock.setAmount(Integer.parseInt(amount));
            stock.setRecycled(0);
            stock.setCid(0);
            stock.setTid(Integer.parseInt(tid));
            //if a price has been written, we set it and update the toy
            if (newPrice != null && !"".equals(newPrice)) {
                stock.setPrice(Float.parseFloat(newPrice));
                toy.setPrice(Float.parseFloat(newPrice));
                toy.update();
            } //if not we use the old price
            else {
                stock.setPrice(toy.getPrice());
            }

            stock.insert();

            response.sendRedirect("toyStock?tid=" + tid);
        } else {
            response.sendRedirect("toyStock?tid=" + tid);
        }
        
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
            Logger.getLogger(pendingSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pendingSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            processPost(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(pendingSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(pendingSalesServlet.class.getName()).log(Level.SEVERE, null, ex);
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
