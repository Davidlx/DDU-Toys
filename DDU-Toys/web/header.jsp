<%-- 
    Document   : header
    Created on : 2016-4-6, 4:54:20
    Author     : David Liu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <jsp:useBean id="allCategories" type="java.util.ArrayList" scope="session" />
    <body>
        <h1>Hello World!</h1>
         <% for(int i = 0; i < allCategories.size(); i+=1) { %> 
                <a href="${allCategories.get(i).getURL()}"><p>${allCategories.get(i).getCategoryName()}</p></a>
        <% } %>
    </body>
</html>
