/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.admin;

import Bean.Stock;
import Bean.Toy;
import Controllers.basicServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
 * @author David Liu
 */
public class editStockServlet extends basicServlet {

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
        Bean.Customer customer = (Bean.Customer) session.getAttribute("customer");
        if(customer == null || !customer.getIsAdmin()) {
            response.sendRedirect("../adminLogin?from=/admin/");
            return;
        }
        
        int sid = Integer.parseInt(request.getParameter("stockId"));
        Stock tempStock = new Stock();
        tempStock.setId(sid);
        tempStock.getOnId();
        
        request.setAttribute("stock", tempStock);
        
        Toy tempToy = new Toy();
        tempToy.setId(tempStock.getTid());
        tempToy.getOnId();
        
        request.setAttribute("toy", tempToy);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("editStock.jsp"); 
        dispatcher.forward(request, response);
    }
    
     protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException{
        response.setContentType("text/html;charset=UTF-8");
        request=super.retrieveBasicAttributes(request);
        
        HttpSession session = request.getSession();
        Bean.Customer customer = (Bean.Customer) session.getAttribute("customer");
        if(customer == null || !customer.getIsAdmin()) {
            response.sendRedirect("../adminLogin?from=/admin/");
            return;
        }
        
        String price =  request.getParameter("price");
        String amount = request.getParameter("amount");
        String sid = request.getParameter("sid");
        
        Stock stock = new Stock();
        stock.setId(Integer.parseInt(sid));
        stock.getOnId();
        
        stock.setAmount(Integer.parseInt(amount));
        stock.setPrice(Float.parseFloat(price));
        
        stock.update();
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">alert(\"The stock has been updated successfully\")</script>");
        out.println("<script type=\"text/javascript\">window.location=\"toyStock?tid="+stock.getTid()+"\";</script>");
        out.close();
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
            Logger.getLogger(editStockServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(editStockServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(editStockServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(editStockServlet.class.getName()).log(Level.SEVERE, null, ex);
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
