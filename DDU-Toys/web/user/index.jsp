<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
    Author     : David Liu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<jsp:useBean id="listOrders"  class="java.util.ArrayList"  scope="request"/>
<!--This is to have an array list of order object, which contains the basic order information(date time, proce, order number, status, list of items)--> 

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
                            <h1>Orders</h1>
                            <c:forEach items="${listOrders}" var="order">
                                
                                <div class="orderItem">
                                <div class="orderBasicInfo">
                                    <p>Order Time: ${order.orderInfo.orderTime} & Order Number: ${order.orderInfo.id}</p>
                                    
                                </div>
                                    <c:forEach items="${order.items}" var="item">
                                        <div class="row borderBottom" style="padding-top:10px;">
                                            <div class="col-md-2">
                                                <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;width: 100%; height:200px;display: block;" src="${item.toyInfo.picUrl}">
                                            </div>
                                             <div class="col-md-6">
                                                <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">${item.toyInfo.name}</h1>
                                                <p class="basicInformation">${item.toyInfo.categoryName} ·  <c:if test="${(item.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if> ·  ${item.toyInfo.age} years above · <c:if test="${item.itemInfo.recycled==0}">Brand New</c:if><c:if test="${item.itemInfo.recycled!=0}">Second Hand · Sold by ${item.sellerInfo.username}</c:if></p>
                                                <p>${item.itemInfo.description}</p>
                                            </div>
                                             <div class="col-md-2">${item.itemInfo.amount}</div>
                                             <div class="col-md-2 itemPrice">$${item.itemInfo.price}</div>
                                        </div>
                                    </c:forEach>
                                
                                <div class="orderSummary">
                                    <h1>Subtotal (${order.numberOfItems} items): ${order.totalPrice}</h1>
                                </div>
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