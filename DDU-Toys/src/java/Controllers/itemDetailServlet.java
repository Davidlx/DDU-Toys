/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.Comment;
import Bean.Customer;
import Bean.Globals;
import Bean.SpecificBean.FirstHandItem;
import Bean.SpecificBean.SecondHandItem;
import Bean.Stock;
import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ugo
 */
public class itemDetailServlet extends basicServlet {

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
        int stockId = Integer.parseInt(request.getParameter("stockId"));
        Stock stock = new Stock();
        stock.setId(stockId);
        stock.getOnId();
        Boolean isRecycled;
        //if recycled, create a second hand bean
        if(stock.getRecycled()==1){
            SecondHandItem item = new SecondHandItem();
            item.setUsedItem(stock);
            isRecycled=true;
            request.setAttribute("item", item);
        }
        //if not then create a first hand bean
        else{
            FirstHandItem item = new FirstHandItem();
            item.setItem(stock);
            isRecycled=false;
            request.setAttribute("item", item);
        }
        request.setAttribute("isRecycled", isRecycled);
        
        //comments part
        ArrayList<Comment> list=new ArrayList<Comment>();
        try{
            Globals.openConn();
            Statement stmt = Globals.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //retreive all comments which are not replies
            ResultSet rs = stmt.executeQuery("SELECT * FROM [Comment] WHERE [Sid] = "+stockId+" AND [ReplyId] = 0");
            int numRow = 0;
            if(rs != null && rs.last() != false) {
                numRow = rs.getRow();
                rs.beforeFirst();
            }
            if(numRow !=0) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                Connection con2 = DriverManager.getConnection("jdbc:sqlserver://w2ksa.cs.cityu.edu.hk:1433;databaseName=aiad072_db", "aiad072", "aiad072");

                while(rs != null && rs.next() != false) {
                    //create the comment bean
                    Comment comment = new Comment();
                    comment.setId(rs.getInt(1));
                    comment.getOnId();
                    //check if this comment has a reply
                    Statement stmt2 = con2.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs2 = stmt2.executeQuery("SELECT * FROM [Comment] WHERE [ReplyId] = '"+rs.getInt(1)+"'");
                    int numRow2 = 0;
                    if(rs2 != null && rs2.last() != false) {
                        numRow2 = rs2.getRow();
                        rs2.beforeFirst();
                    }
                    //if it has a reply we set it in the bean
                    if(numRow2 ==1) {
                        while(rs2 != null && rs2.next() != false) {
                            comment.setReplyComment(rs2.getInt(1));
                        }
                    }
                    list.add(comment);
                    if(rs2 != null) {
                        rs2.close();
                    }
                }
                con2.close();
            }
            if(rs != null) {
                rs.close();
            }
            Globals.closeConn();
        }
        catch (ClassNotFoundException e) {
            Globals.beanLog.info(e.toString());
        } catch (SQLException e) {
            Globals.beanLog.info(e.toString());
        }
        
        request.setAttribute("listComments", list);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("itemDetail.jsp"); 
        dispatcher.forward(request, response);
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
            Logger.getLogger(itemDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(itemDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(itemDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(itemDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
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
