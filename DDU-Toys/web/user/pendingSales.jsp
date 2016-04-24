<%-- 
    Document   : newSales
    Created on : 2016-4-9, 22:16:48
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
                <h1 class="homePageTitle">Hi <c:out value="${customer.username}"/></h1>
                <div class="row userSection">
                    <div class="col-md-2">
                       <%@ include file="userNav.jsp" %>
                    </div>
                    <div class="col-md-10">
                        <div class="userMainContent">
                            
                            <h1>Pending Sales</h1>
                            <c:forEach items="${listPendingSales}" var="item">

                                <div class="row  borderBottom marginTop">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:200px; display: block;" src="${item.picUrl}"/> 
                                    </div>
                                     <div class="col-md-6">
                                        <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">${item.name}</h1>
                                        <p class="basicInformation">${item.categoryName} & <c:if test="${(item.sex ==0)}">
                                                    Female
                                                </c:if>
                                                <c:if test="${(item.sex ==1)}">
                                                    Male
                                                </c:if>
                                                <c:if test="${(item.sex ==2)}">
                                                    Unisex
                                                </c:if>
                                                & ${item.age} Years Old</p>
                                        <p>${item.conDes}</p>
                                        <a href="deleteSales?type=pending&sid=${item.id}"><p>Delete this Item</p></a>
                                    </div>
                                     <div class="col-md-2">${item.amount} in stock</div>
                                     <div class="col-md-2 itemPrice">Current Price:$${item.price}</div>
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
