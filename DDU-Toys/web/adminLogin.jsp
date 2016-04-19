<%-- 
    Document   : login
    Created on : 2016-4-9, 22:13:24
    Author     : David Liu
--%>
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
                <div class="row">
                        <div class="col-md-6 loginSection">
                            <h1>Admin Log in</h1>
                            <form method="POST" action="${pageContext.request.contextPath}/adminLogin">
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Email address</label>
                                  <input type="email" class="form-control" id="exampleInputEmail1" name="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputPassword1">Password</label>
                                  <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
                                </div>
                                <input type="hidden" name="from" value="${param.from}">
                                <button type="submit" class="btn btn-default">Submit</button>
                              </form>
                        </div>
                        <div class="col-md-6">
                            <h1 style="font-size: 30px; padding-bottom:20px;margin-top: 80px;">Notice</h2>
                            <P>This page is for admin user only, please go back to the user login page</P>
                            <a href="login"<button class="btn btn-default" style="margin-top:130px">User Login</button></a>
                        </div>
                     </div>
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>