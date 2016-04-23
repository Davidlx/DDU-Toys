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
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:200px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    </div>
                                     <div class="col-md-6">
                                        <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">${item.toyInfo.name}</h1>
                                        <p class="basicInformation">${item.toyInfo.categoryName} & <c:if test="${(item.toyInfo.sex ==0)}">
                                                    Female
                                                </c:if>
                                                <c:if test="${(item.toyInfo.sex ==1)}">
                                                    Male
                                                </c:if>
                                                <c:if test="${(item.toyInfo.sex ==2)}">
                                                    Unisex
                                                </c:if>
                                                & ${item.toyInfo.age} Years Old</p>
                                        <p>${item.usedItem.conDes}</p>
                                        <a href="deleteSales?sid=${item.usedItem.id}"><p>Delete this Item</p></a>
                                    </div>
                                     <div class="col-md-2">${item.usedItem.amount} in stock</div>
                                     <div class="col-md-2 itemPrice">$${item.usedItem.price}</div>
                                </div>
                            </c:forEach>
                            
                            
                            
                            <h1>New Sale Item</h1>
                            <div class="row marginTop marginLeft">
                                <form method="POST" action="${pageContext.request.contextPath}/newSale">
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Choose Existing Toys</label>
                                      <select class="form-control" name="existingToy" id="toySelection">
                                          <option value = "0">I would like to specify a new toy</option>
                                        <c:forEach items="${toys}" var="toy">
                                            <option value = "${toy.id}">${toy.name} & ${toy.categoryName}</option>
                                        </c:forEach>
                                        
                                      </select>
                                    </div>
                                    
                                    <div class="form-group" >
                                      <label for="exampleInputEmail1">Item Name</label>
                                      <input type="text" class="form-control" id="ToyInput" name = "name" placeholder="Item Name">
                                    </div>
                                    
                                    <div class="form-group" >
                                      <label for="exampleInputPassword1">Description</label>
                                      <input type="text" class="form-control"  id="descInput" name = "description" placeholder="Description">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Condition Description</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1"  name = "conDescription" placeholder="Condition Description">
                                    </div>
                                    
                                    <div class="form-group" >
                                      <label for="exampleInputPassword1">Original Price</label>
                                      <input type="text" class="form-control" id="priceInput" name = "originalPrice" placeholder="Original Price">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Current Price</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" name = "currentPrice" placeholder="Current Price">
                                    </div>
                                    
                                    <div class="form-group" >
                                      <label for="exampleInputPassword1">Amont of Toys</label>
                                      <input type="number" class="form-control" id="amontInput" name = "amountToys" placeholder="Amont of Toys">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Target Age (Inclusive)</label>
                                      <input type="number" class="form-control" id="ageInput" name = "targetAge" placeholder=">Target Age (Inclusive)">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Picture Url</label>
                                      <input type="text" class="form-control" id="picInput" name = "pictureUrl" placeholder="Picture Url">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Sex</label>
                                      <select class="form-control" id="sexSelection" name = "sex">
                                        <option value = "0">Female</option>
                                        <option value = "1">Male</option>
                                        <option value = "2">Unisex</option>
                                      </select>
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Category</label>
                                      <select class="form-control" id="cateSelection" name = "categoryId">
                                        <c:forEach items="${cate}" var="category">
                                            <option value = "${category.id}">${category.name}</option>
                                        </c:forEach>
                                        
                                      </select>
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
