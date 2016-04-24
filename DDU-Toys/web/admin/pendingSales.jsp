<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
    Author     : David Liu


An Arraylist of all the pending sales object containing all the information in the table (please refer to it)

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
                <h1 class="homePageTitle">Hi <c:out value="${customer.username}"/></h1>
                <div class="row userSection">
                    <div class="col-md-2">
                       <%@ include file="adminNav.jsp" %>
                    </div>
                    <div class="col-md-10">
                        <div class="userMainContent">
                            <h1>Pending New Sales</h1>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Price</td>
                                        <td>Description</td>
                                        <td>Condition Description</td>
                                        <td>Original Price</td>
                                        <td>Amount</td>
                                        <td>Name</td>
                                        <td>Age</td>
                                        <td>Sex</td>
                                        <td>Seller</td>
                                        <td>Category</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${pendingSalesList}" var="item">
                                        <tr>
                                            <td>${item.id}</td>
                                            <td>${item.price}</td>
                                            <td>${item.des}</td>
                                            <td>${item.conDes}</td>
                                            <td>${item.orgPrice}</td>
                                            <td>${item.amount}</td>
                                            <td>${item.name}</td>
                                            <td>${item.age}</td>
                                            <td>${item.sex}</td>
                                            <td>${item.cid}</td>
                                            <td>${item.categoryName}</td>
                                            <td><a href="pendingSales?action=grant&tempToyId=${item.id}"><p>Grant Sale</p></a></td>
                                            <td><a href="pendingSales?action=delete&tempToyId=${item.id}"><p>Delete Sale</p></a></td>
                                        </tr>
                                    </c:forEach>
                                
                                <tbody>
                            </table>
                        </div>
                    </div>
                </div>
                
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>