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
                            <h1>Log in</h1>
                            <form>
                                <div class="form-group">
                                  <label for="exampleInputEmail1">Email address</label>
                                  <input type="email" class="form-control" id="exampleInputEmail1" name="email" placeholder="Email">
                                </div>
                                <div class="form-group">
                                  <label for="exampleInputPassword1">Password</label>
                                  <input type="password" class="form-control" id="exampleInputPassword1" name="password" placeholder="Password">
                                </div>
                                <button type="submit" class="btn btn-default">Submit</button>
                              </form>
                        </div>
                        <div class="col-md-6">
                            <h1 style="font-size: 30px; padding-bottom:20px;margin-top: 100px;">New Customer</h2>
                            <P>Create an account with us and you'll be able to:</P>
                            <ul style="margin-bottom: 20px;">
                                <li>Check out faster</li>
                                <li>Save multiple shipping addresses</li>
                                <li>Access your order history</li>
                                <li>Track new orders</li>
                                <li>Save items to your wish list</li>
                            </ul>
                            <a href="register"><button class="btn btn-default">Create Account</button></a>
                        </div>
                     </div>
                <a href="adminLogin" style="text-align: center; color: gray"><p>Admin User</p></a>
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>