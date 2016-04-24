<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
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
                       <%@ include file="adminNav.jsp" %>
                    </div>
                    <div class="col-md-10">
                        <div class="userMainContent">
                            <h1>Toys</h1>
                            
                            <c:forEach items="${toys}" var="toy">
                                <div class="ToyItem">
                                    <div class="row borderBottom" style="margin-top:10px;">
                                        <div class="col-md-2">
                                            <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;padding-bottom: 10px;width: 100%; height:230px; display: block;" src="${toy.picUrl}"/>
                                        </div>
                                         <div class="col-md-6">
                                            <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">${toy.name}</h1>
                                            <p class="basicInformation">${toy.categoryName} · <c:if test="${(toy.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(toy.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(toy.sex ==2)}">
                                                Unisex
                                            </c:if> · ${toy.age} Years Old</p>
                                            <p>${toy.des}</p>
                                        </div>
                                         <div class="col-md-2">Original Price: ${toy.price}</div>
                                         <div class="col-md-2 itemPrice" style="font-size: 15px">
                                             <a href="toyStock?tid=${toy.id}"><p>View Stocks</p></a>
                                             <a href="editToy?tid=${toy.id}"><p>Edit this Toy</p></a>
                                             <a href="deleteToy?tid=${toy.id}"><p>Delete this Toy</p></a>
                                         </div>
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