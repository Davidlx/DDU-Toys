<%-- 
    Document   : sale
    Created on : 2016-4-9, 22:16:40
    Author     : David Liu
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>--%>//Contains whether the user logged in, user name, how many items in the cart
<jsp:useBean id="listSales"  class="java.util.ArrayList"  scope="request"/>
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
                <h1 class="homePageTitle">Hi <c:out value="${customer.username}"/></h1>
                <div class="row userSection">
                    <div class="col-md-2">
                       <%@ include file="userNav.jsp" %>
                    </div>
                    <div class="col-md-10">
                        <div class="userMainContent">
                            <h1>Sales</h1>
                            
                                <c:forEach items="${listSales}" var="item">
                                    
                                    <div class="row  borderBottom marginTop">
                                        <div class="col-md-2">
                                            <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                        </div>
                                         <div class="col-md-6">
                                            <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">{item.toyInfo.name}</h1>
                                            <p class="basicInformation">${item.toyInfo.categoryName} & <c:if test="${(item.toyInfo.sex ==0)}">
                                                        Female
                                                    </c:if>
                                                    <c:if test="${(item.toyInfo.sex ==1)}">
                                                        Male
                                                    </c:if>
                                                    <c:if test="${(item.toyInfo.sex ==2)}">
                                                        Unisex
                                                    </c:if>
                                                    & ${item.toyInfo.age} Years Old & Sold by ${item.customerInfo.username}</p>
                                            <p>${item.usedItem.conDes}</p>
                                            <a href="deleteSales?sid=${item.usedItem.id}"><p>Delete this Item</p></a>
                                        </div>
                                         <div class="col-md-2">${item.usedItem.amount}</div>
                                         <div class="col-md-2 itemPrice">${item.usedItem.price}</div>
                                    </div>
                                </c:forEach>
                            
                            
                            
                            
                        </div>
                    </div>
                </div>
                    
            </div>
                <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
