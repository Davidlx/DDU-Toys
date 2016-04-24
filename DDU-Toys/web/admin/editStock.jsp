<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
    Author     : David Liu

A bean containing the original informaiton of the stock, and a bean contains the toy information

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
                            <h1>Edit stocks for ${toy.name}</h1>
                            <div class="ToyItem">
                                <div class="row borderBottom" style="margin-top:10px;">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;padding-bottom: 10px;width: 100%; display: block;" src=" ${toy.picUrl}"/></div>
                                     <div class="col-md-6">
                                        <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;"> ${toy.name}</h1>
                                        <p class="basicInformation">${toy.categoryName} ·  <c:if test="${(toy.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(toy.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(toy.sex ==2)}">
                                                Unisex
                                            </c:if> · ${toy.age}</p>
                                        <p>${toy.des}</p>
                                    </div>
                                     <div class="col-md-4">Original Price: ${toy.price}</div>
                                </div>
                            </div>
                            
                             <h1>Edit Stock</h1>
                            <div class="newToy">
                                 <form method="POST" action="${pageContext.request.contextPath}/admin/editStock?sid=${stock.id}">
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Condition Description</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Condition Description" name="conDes" value="${stock.conDes}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Current Price</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Current Price" name="price" value="${stock.price}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Amount of Toys</label>
                                      <input type="number" class="form-control" id="exampleInputPassword1" placeholder="Amont of Toys" name="amount" value="${stock.amount}">
                                    </div>
                                    
                                    <button type="submit" class="btn btn-default">Submit</button>
                                  </form>
                            </div>
                            
                        </div>
                    </div>
                </div>
                
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>