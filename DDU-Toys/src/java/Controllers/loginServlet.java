/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.Customer;
import Bean.Globals;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
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
public class loginServlet extends basicServlet{

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
        checkUser(request,response);
    }
    
    private void checkUser(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (email != null && !email.equalsIgnoreCase("") &&
                password != null && !password.equalsIgnoreCase("")) {
                // Setup connection to db
                Globals.openConn();
                Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery("SELECT * FROM [Customer] WHERE [Email] = '"+email+"' AND [Password] = '"+password+"'");
                
                int numRow = 0;
                if(rs != null && rs.last() != false) {
                    numRow = rs.getRow();
                    rs.beforeFirst();
                }
                
                if(numRow == 1) {
                    //bean creation
                    Customer cust = new Customer();
                    while(rs != null && rs.next() != false) {
                        cust.setId(rs.getInt(1));
                        cust.getOnId();
                    }
                    HttpSession session = request.getSession();
                    session.setAttribute("customer", cust);
                    session.setAttribute("isLoggedIn",true);
                    String uri = request.getParameter("from");
                    //removes the .jsp
                    if(uri.toLowerCase().contains(".jsp"))
                        uri=uri.substring(0, uri.length()-4);
                    response.sendRedirect(uri);
                }
                //user doesn't exist
                else{
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">alert(\"Your email and password is incorrect, please check again\")</script>");
                    out.println("<script type=\"text/javascript\">window.location=\"login\";</script>");
                    out.close();
                }
                                  
                // close connections and statements
                if(rs != null) {
                    rs.close();
                }
                Globals.closeConn();
            }
            else{
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp"); 
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
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(loginServlet.class.getName()).log(Level.SEVERE, null, ex);
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
