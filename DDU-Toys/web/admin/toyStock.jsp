<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
    Author     : David Liu

A bean contains the information for Toy and an arrayList of stocks

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
                            <h1>Stocks for ${currentToy.name}</h1>
                            <div class="ToyItem">
                                <div class="row borderBottom" style="margin-top:10px;">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;padding-bottom: 10px;width: 100%; display: block;" src="${currentToy.picUrl}"/>
                                    </div>
                                     <div class="col-md-6">
                                        <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">${currentToy.name}</h1>
                                        <p class="basicInformation">${currentToy.categoryName} · <c:if test="${(currentToy.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(currentToy.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(currentToy.sex ==2)}">
                                                Unisex
                                            </c:if> ·  For ${currentToy.age} years above</p>
                                        <p>${currentToy.des}</p>
                                    </div>
                                     <div class="col-md-4">Original Price:$${currentToy.price}</div>
                                </div>
                            </div>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Type</td>
                                        <td>Condition Description</td>
                                        <td>Amount</td>
                                        <td>Price</td>
                                        <td>Seller</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </thead>
                                <c:forEach items="${stocks}" var="item">
                                    <tr>
                                        <td>${item.id}</td>
                                        <td><c:if test="${item.recycled==0}">Brand New</c:if><c:if test="${item.recycled!=0}">Second Hand</c:if></td>
                                        <td>${item.conDes}</td>
                                        <td>${item.amount}</td>
                                        <td>$${item.price}</td>
                                        <td>${item.cid}</td>
                                        <td><a href="comments?stockId=${item.id}"><p>View Comments</p></a></td>
                                        <td><a href="editStock?stockId=${item.id}"><p>Edit Stock</p></a></td>
                                        <td><a href="deleteStock?stockId=${item.id}"><p>Delete Stock</p></a></td>
                                    </tr>
                                </c:forEach>
                                
                            </table>
                            
                             <h1 style="margin-bottom: 10px">New Stock</h1>
                            <div class="newToy">
                                 <form>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Condition Description</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Condition Description">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Current Price</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Current Price">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Amont of Toys</label>
                                      <input type="number" class="form-control" id="exampleInputPassword1" placeholder="Amont of Toys">
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