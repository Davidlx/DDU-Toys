/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.admin;

import Bean.Toy;
import Controllers.basicServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author David Liu
 */
public class toyEditServlet extends basicServlet {

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
        
        int tid = Integer.parseInt(request.getParameter("tid"));
        
        Toy tempToy = new Toy();
        tempToy.setId(tid);
        tempToy.getOnId();
        
        request.setAttribute("toy", tempToy);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("editToy.jsp"); 
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
        
        String itemName = request.getParameter("itemName");
        String desc =  request.getParameter("desc");
        String originalPrice = request.getParameter("originalPrice");
        String age = request.getParameter("age");
        String picUrl = request.getParameter("picUrl");
        String sex = request.getParameter("sex");
        String category = request.getParameter("category");
        
        String tid=request.getParameter("tid");
        
        
        Toy tempToy = new Toy();
        tempToy.setId(Integer.parseInt(tid));
        tempToy.getOnId();
        
        tempToy.setAge(Integer.parseInt(age));
        tempToy.setCategoryId(Integer.parseInt(category));
        tempToy.setDes(desc);
        tempToy.setName(itemName);
        tempToy.setPicUrl(picUrl);
        tempToy.setPrice(Float.parseFloat(originalPrice));
        tempToy.setSex(Integer.parseInt(sex));
        
        tempToy.update();
        
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">alert(\"Toy Information has been updated successfully\")</script>");
        out.println("<script type=\"text/javascript\">window.location=\"toyStock?tid="+tempToy.getId()+"\";</script>");
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
            Logger.getLogger(toyEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(toyEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(toyEditServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(toyEditServlet.class.getName()).log(Level.SEVERE, null, ex);
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
