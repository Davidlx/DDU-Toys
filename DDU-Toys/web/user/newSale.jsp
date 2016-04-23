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
