/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ugo
 */
public class navServlet extends HttpServlet {

    
    protected HttpServletRequest retrieveCate(HttpServletRequest request) throws ServletException, IOException {
        Bean.Category cate = new Bean.Category();
        request.setAttribute("cate", cate);
        return request;
    }

}
