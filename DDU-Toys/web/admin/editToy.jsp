<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
    Author     : David Liu

A bean containing the original informaiton of the stock
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
                            
                            <h1>Edit Toys</h1>
                            <div class="newToy">
                                <form method="POST" action="${pageContext.request.contextPath}/admin/editToy?tid=${toy.id}">
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Item Name</label>
                                      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Item Name" name="itemName" value="${toy.name}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Description</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Description" name="desc" value="${toy.des}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Original Price</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Original Price" name="originalPrice" value="${toy.price}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Target Age (Inclusive)</label>
                                      <input type="number" class="form-control" id="exampleInputPassword1" placeholder=">Target Age (Inclusive)" name="age" value="${toy.age}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Picture Url</label>
                                      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Picture Url" name="picUrl" value="${toy.picUrl}">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Sex</label>
                                      <select class="form-control" value="${toy.sex}" name="sex">
                                        <option value = "0">Female</option>
                                        <option value = "1">Male</option>
                                        <option value = "2">Unisex</option>
                                      </select>
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Category</label>
                                      <select class="form-control" value="${toy.categoryId}" name="category">
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