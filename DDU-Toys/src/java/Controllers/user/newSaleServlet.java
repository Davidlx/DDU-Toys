/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.user;

import Bean.Customer;
import Bean.Globals;
import Bean.SpecificBean.SecondHandItem;
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
public class newSaleServlet extends basicServlet {

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
        addSale(request, response);
    }

    private void addSale(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        int existingToyId = -1;
        if (request.getParameter("existingToy") != null) {
            existingToyId = Integer.parseInt(request.getParameter("existingToy"));
        }
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String conDescription = request.getParameter("conDescription");
        String originalPrice = request.getParameter("originalPrice");
        String currentPrice = request.getParameter("currentPrice");
        String amountToys = request.getParameter("amountToys");
        String targetAge = request.getParameter("targetAge");
        String pictureUrl = request.getParameter("pictureUrl");
        String sex = request.getParameter("sex");
        String categoryId = request.getParameter("categoryId");
        
        HttpSession session = request.getSession();
        Customer c=(Customer)session.getAttribute("customer");
        int cid = c.getId();
        //if the user specifies a new toy
        if (existingToyId == 0) {
            if (name != null && !name.equalsIgnoreCase("")
                    && description != null && !description.equalsIgnoreCase("")
                    && conDescription != null && !conDescription.equalsIgnoreCase("")
                    && originalPrice != null && !originalPrice.equalsIgnoreCase("")
                    && currentPrice != null && !currentPrice.equalsIgnoreCase("")
                    && amountToys != null && !amountToys.equalsIgnoreCase("")
                    && targetAge != null && !targetAge.equalsIgnoreCase("")
                    && pictureUrl != null && !pictureUrl.equalsIgnoreCase("")
                    && sex != null && !sex.equalsIgnoreCase("")
                    && categoryId != null && !categoryId.equalsIgnoreCase("")) {
                TempToy temp = new TempToy();
                temp.setName(name);
                temp.setDes(description);
                temp.setConDes(conDescription);
                temp.setOrgPrice(Integer.parseInt(originalPrice));
                temp.setPrice(Integer.parseInt(currentPrice));
                temp.setAmount(Integer.parseInt(amountToys));
                temp.setAge(Integer.parseInt(targetAge));
                temp.setPicUrl(pictureUrl);
                temp.setSex(Integer.parseInt(sex));
                temp.setCategoryId(Integer.parseInt(categoryId));
                temp.setCid(cid);
                temp.insert();
                String uri = request.getParameter("from");
                //removes the .jsp
                if (uri.toLowerCase().contains(".jsp")) {
                    uri = uri.substring(0, uri.length() - 4);
                }
                response.sendRedirect(uri);
            }
        }
        //the user wants to sell a toy that already exists
        else if (existingToyId > 0) {
            if (conDescription != null && !conDescription.equalsIgnoreCase("")
                    && currentPrice != null && !currentPrice.equalsIgnoreCase("")
                    && amountToys != null && !amountToys.equalsIgnoreCase("")) {
                TempToy temp = new TempToy();
                temp.setTid(existingToyId);
                temp.setConDes(conDescription);
                temp.setPrice(Integer.parseInt(currentPrice));
                temp.setAmount(Integer.parseInt(amountToys));
                temp.setCid(cid);
                temp.insert();
                
                String uri = request.getParameter("from");
                //removes the .jsp
                if (uri.toLowerCase().contains(".jsp")) {
                    uri = uri.substring(0, uri.length() - 4);
                }
                response.sendRedirect(uri);
            }
        } 
        //redirect
        else {
            //send a list of toys
            ArrayList<Toy> toys = new ArrayList<Toy>();
            try {
                Globals.openConn();
                Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                //retreive all orders of the user
                ResultSet rs = stmt.executeQuery("SELECT * FROM [Toys]");
                int numRow = 0;
                if (rs != null && rs.last() != false) {
                    numRow = rs.getRow();
                    rs.beforeFirst();
                }
                if (numRow > 0) {
                    while (rs != null && rs.next() != false) {
                        Toy toy = new Toy();
                        toy.setId(rs.getInt(1));
                        toy.getOnId();
                        toys.add(toy);
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

            request.setAttribute("toys", toys);
            RequestDispatcher dispatcher = request.getRequestDispatcher("newSale.jsp");
            dispatcher.forward(request, response);
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
            Logger.getLogger(newSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(newSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(newSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(newSaleServlet.class.getName()).log(Level.SEVERE, null, ex);
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
