/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Bean.Customer;
import Bean.CustomerOrder;
import Bean.Globals;
import Bean.OrderItem;
import Bean.SpecificBean.FirstHandCartItem;
import Bean.SpecificBean.SecondHandCartItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Dennis T
 */
public class checkOutServlet extends basicServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        request=super.retrieveBasicAttributes(request);
        
        HttpSession session = request.getSession();
        Customer customer = new Customer();
        
        try {
            
            Boolean isFHCEmpty = (Boolean) session.getAttribute("isFirstHandCartEmpty");
            Boolean isSHCEmpty = (Boolean) session.getAttribute("isSecondHandCartEmpty");
            
            if(isFHCEmpty == true && isSHCEmpty == true) {
                    PrintWriter out = response.getWriter();
                    out.println("<script type=\"text/javascript\">alert(\"Nothing in the cart\")</script>");
                    out.println("<script type=\"text/javascript\">window.location=\"cart?action=0\";</script>");
                    out.close(); 
            } else {
            
            customer = (Customer) session.getAttribute("customer");
            if(customer.getId() == 0) {
                response.sendRedirect("/DDU-Toys/login?from=/DDU-Toys/cart");
            } else {
                
                int cid = customer.getId();
                createOrderInDB(session, cid, isFHCEmpty, isSHCEmpty);
             
                PrintWriter out = response.getWriter();
                out.println("<script type=\"text/javascript\">alert(\"We have received your order, thanks!\")</script>");
                out.println("<script type=\"text/javascript\">window.location=\"user/index\";</script>");
                out.close();
            }
            }
        } catch (NullPointerException e) {
            Globals.beanLog.info(e.toString());
        }
        
    }

    private void createOrderInDB(HttpSession session, int cid, Boolean isFHCEmpty, Boolean isSHCEmpty) throws SQLException, ClassNotFoundException{
        
        // Create CostumerOrder and get Id 
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setCid(cid);
        Date date = new Date();
        customerOrder.setOrderTime(new Timestamp(date.getTime()));
        customerOrder.insert();
        int oid = customerOrder.getId();
        
        //First Hand:
        if(!isFHCEmpty) {
            ArrayList<FirstHandCartItem> items = (ArrayList<FirstHandCartItem>) session.getAttribute("firstHandCartList");
            
            for(int i = 0; i < items.size(); i++) {
                // Insert stock in order in db
                OrderItem item = new OrderItem();
                item.setAmount(items.get(i).getItemAmount());
                item.setRecycled(items.get(i).getFirstHandItem().getRecycled());
                item.setCid(0); //Because it's a first hand
                item.setDescription(items.get(i).getToyInfo().getDes());
                item.setOid(oid);
                item.setPrice(items.get(i).getFirstHandItem().getPrice());
                item.setTid(items.get(i).getFirstHandItem().getTid());
                item.insert();
                
                // Delete or decrease amount of stock
                int dbAmount = items.get(i).getFirstHandItem().getAmount();
                int buyAmount = items.get(i).getItemAmount();
                
                if(dbAmount <= buyAmount){
                    items.get(i).getFirstHandItem().delete();
                } else {
                    items.get(i).getFirstHandItem().setAmount(dbAmount - buyAmount);
                    items.get(i).getFirstHandItem().update();
                }
            }
            //Clear session
            ArrayList<FirstHandCartItem> emptyItems = new ArrayList<FirstHandCartItem>();
            session.removeAttribute("firstHandCartList");
            session.setAttribute("isFirstHandCartEmpty", true);
        }
        
        if(!isSHCEmpty) {
            ArrayList<SecondHandCartItem> items = (ArrayList<SecondHandCartItem>) session.getAttribute("secondHandCartList");
            
            for(int i = 0; i < items.size(); i++) {
                // Insert stock in order in db
                OrderItem item = new OrderItem();
                item.setAmount(items.get(i).getItemAmount());
                item.setRecycled(items.get(i).getUsedItem().getRecycled());
                item.setCid(items.get(i).getUsedItem().getCid());
                item.setDescription("General Information:\n" + items.get(i).getToyInfo().getDes() + "\nCondition:\n" + items.get(i).getUsedItem().getConDes());
                item.setOid(oid);
                item.setPrice(items.get(i).getUsedItem().getPrice());
                item.setTid(items.get(i).getUsedItem().getTid());
                item.insert();
                
                // Delete or decrease amount of stock
                int dbAmount = items.get(i).getUsedItem().getAmount();
                int buyAmount = items.get(i).getItemAmount();
                
                if(dbAmount <= buyAmount){
                    items.get(i).getUsedItem().delete();
                } else {
                    items.get(i).getUsedItem().setAmount(dbAmount - buyAmount);
                    items.get(i).getUsedItem().update();
                }
            }
            //Clear session
            ArrayList<SecondHandCartItem> emptyItems = new ArrayList<SecondHandCartItem>();
            session.removeAttribute("secondHandCartList");
            session.setAttribute("isSecondHandCartEmpty", true);
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
            Logger.getLogger(checkOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(checkOutServlet.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(checkOutServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(checkOutServlet.class.getName()).log(Level.SEVERE, null, ex);
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
