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
                            
                            <h1>New Toys</h1>
                            <div class="newToy">
                                <form method="POST" action="${pageContext.request.contextPath}/admin/newToy">
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Item Name</label>
                                      <input type="text" class="form-control" id="exampleInputEmail1" name="itemName" placeholder="Item Name">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Description</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" name="desc" placeholder="Description">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Original Price</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" name="originalPrice" placeholder="Original Price">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Target Age (Inclusive)</label>
                                      <input type="number" class="form-control" id="exampleInputPassword1" name="age" placeholder=">Target Age (Inclusive)">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Picture Url</label>
                                      <input type="text" class="form-control" id="exampleInputEmail1" name="picUrl" placeholder="Picture Url">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Sex</label>
                                      <select class="form-control" name="sex">
                                        <option value = "0">Female</option>
                                        <option value = "1">Male</option>
                                        <option value = "2">Unisex</option>
                                      </select>
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Category</label>
                                      <select class="form-control" name="category">
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