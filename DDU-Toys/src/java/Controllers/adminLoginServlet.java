/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.Admin;
import Bean.Customer;
import Bean.Globals;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
public class adminLoginServlet extends basicServlet {

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
        checkAdmin(request,response);
    }
    
    private void checkAdmin(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email != null && !email.equalsIgnoreCase("") &&
                password != null && !password.equalsIgnoreCase("")) {
                // Setup connection to db
                Globals.openConn();
                Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM [Manager] WHERE [Email] = '"+email+"' AND [Password] = '"+password+"'");
                
                int numRow = 0;
                if(rs != null && rs.last() != false) {
                    numRow = rs.getRow();
                    rs.beforeFirst();
                }
                
                if(numRow == 1) {
                    //bean creation
                    Customer admin = new Customer();
                    while(rs != null && rs.next() != false) {
                        admin.setId(rs.getInt(1));
                        admin.setIsAdmin(true);
                        admin.setUsername(rs.getString(2));
                        admin.setPassword(password);
                        admin.setEmail(email);
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("customer", admin);
                    session.setAttribute("isLoggedIn",true);
                    String uri = request.getParameter("from");
                    //removes the .jsp
                    if (uri==null) {
                        response.sendRedirect("DDU-Toys/admin");
                        return;
                    }
                    
                    if(uri.toLowerCase().contains(".jsp"))
                        uri=uri.substring(0, uri.length()-4);
                    response.sendRedirect("DDU-Toys/"+uri);
                }
                //user doesn't exist
                else{
                    RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp"); 
                    dispatcher.forward(request, response);
                }
                                  
                // close connections and statements
                if(rs != null) {
                    rs.close();
                }
                Globals.closeConn();
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp"); 
                dispatcher.forward(request, response);
            }
           
        } catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
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
            Logger.getLogger(adminLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(adminLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminLoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
