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
                            <h1>Pending New Sales</h1>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Price</td>
                                        <td>Description</td>
                                        <td>Condition Description</td>
                                        <td>Original Price</td>
                                        <td>Amount</td>
                                        <td>Name</td>
                                        <td>Age</td>
                                        <td>Sex</td>
                                        <td>Seller</td>
                                        <td>Category</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tr>
                                    <td>ID</td>
                                    <td>Price</td>
                                    <td>Description</td>
                                    <td>Condition Description</td>
                                    <td>Original Price</td>
                                    <td>Amount</td>
                                    <td>Name</td>
                                    <td>Age</td>
                                    <td>Sex</td>
                                    <td>Seller</td>
                                    <td>Category</td>
                                    <td><a href="comments?stockId="><p>Grant Sale</p></a></td>
                                    <td><a href="editStock?stockId="><p>Delete Sale</p></a></td>
                                </tr>
                            </table>
                            
                             <h1>New Stock</h1>
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