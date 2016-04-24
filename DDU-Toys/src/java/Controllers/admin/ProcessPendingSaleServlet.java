/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.admin;

import Bean.Customer;
import Bean.Globals;
import Bean.Stock;
import Bean.TempToy;
import Bean.Toy;
import Controllers.basicServlet;
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
public class ProcessPendingSaleServlet extends basicServlet {

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
        if(customer == null || !customer.getIsAdmin()) {
            response.sendRedirect("../adminLogin?from=/admin/");
            return;
        }

        int isAccepted = Integer.parseInt(request.getParameter("isAccepted"));
        int ttid = Integer.parseInt(request.getParameter("ttid"));
        TempToy temp = new TempToy();
        temp.setId(ttid);
        temp.getOnId();
        //if accepted, copy it 
        if (isAccepted == 1) {
            Stock stock = new Stock();
            stock.setTid(temp.getTid());
            //if it is a new toy
            if (temp.getTid() == 0) {
                //first add a toy
                Toy toy = new Toy();
                toy.setName(temp.getName());
                toy.setDes(temp.getDes());
                toy.setSex(temp.getSex());
                toy.setAge(temp.getAge());
                toy.setPrice(temp.getOrgPrice());
                toy.setPicUrl(temp.getPicUrl());
                toy.setCategoryId(temp.getCategoryId());
                toy.insert();
                //then set the stock
                stock.setTid(toy.getId());
            }
            stock.setRecycled(1);
            stock.setConDes(temp.getConDes());
            stock.setCid(temp.getCid());
            stock.setAmount(temp.getAmount());
            stock.setPrice(temp.getPrice());
            stock.insert();

        }
        //then delete it from temp toys
        temp.delete();

        
        PrintWriter out = response.getWriter();
        if (isAccepted == 1)
            out.println("<script type=\"text/javascript\">alert(\"The pending sales has been granted\")</script>");
        else
            out.println("<script type=\"text/javascript\">alert(\"The pending sales has been deleted\")</script>");
        out.println("<script type=\"text/javascript\">window.location=\"pendingSales\";</script>");
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
            processRequest(request, response);
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
