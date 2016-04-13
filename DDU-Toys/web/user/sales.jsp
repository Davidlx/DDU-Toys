<%-- 
    Document   : sale
    Created on : 2016-4-9, 22:16:40
    Author     : David Liu
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>--%>//Contains whether the user logged in, user name, how many items in the cart
<%--<jsp:useBean id="items"  class="java.util.Arraylist"  scope="request"/>--%>//Contains an array list of the item object which contains the name, img link, description, price for the item
<%--<jsp:useBean id="Scope"  class="String"  scope="session"/>--%> // Spercify which category/kind of item the user wants to see
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DDU-Toys</title>
        <%@ include file="header.jsp" %>
    </head>
    <body>
        <div class="contents">
            <%@ include file="nav.jsp" %>
            <div class="mainContent">
                <%@ include file="shopTitle.jsp" %>
                <h1 class="homePageTitle">Hi UserName</h1>
                <div class="row userSection">
                    <div class="col-md-2">
                       <%@ include file="userNav.jsp" %>
                    </div>
                    <div class="col-md-10">
                        <div class="userMainContent">
                            <h1>Sales</h1>
                        </div>
                    </div>
                </div>
                    
            </div>
                <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
