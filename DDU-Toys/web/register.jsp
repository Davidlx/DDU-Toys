<%-- 
    Document   : register
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
                <div class="register">
                    <h1>New Customer</h1>
                    <form method="POST" action="${pageContext.request.contextPath}/register">
                        <div class="form-group">
                          <label for="exampleInputEmail1">User Name</label>
                          <input type="text" class="form-control" id="username" name="username" placeholder="User Name">
                        </div>
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
                <a href="login" style="text-align: center; color: gray"><p>Already have an account? Login here.</p></a>
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>