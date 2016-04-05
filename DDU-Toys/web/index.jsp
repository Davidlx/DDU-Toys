<%-- 
    Document   : index
    Created on : 6 avr. 2016, 10:20:47
    Author     : Ugo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DDU-Toys</title>
    </head>
    <jsp:useBean id="cate"  class="Bean.Category"  scope="request"/>
    <body>
        <h1>Home Page</h1>
        <p><jsp:getProperty name="cate" property="id" /></p>
        <p><jsp:getProperty name="cate" property="name" /></p>
    </body>
</html>
