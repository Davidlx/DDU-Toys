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
                            <div class="ToyItem">
                                <div class="row borderBottom" style="margin-top:10px;">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;padding-bottom: 10px;width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDU4YzAgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NThjMCI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                                    </div>
                                     <div class="col-md-6">
                                        <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">Item Name</h1>
                                        <p class="basicInformation">Category · Sex · Age</p>
                                        <p>description</p>
                                    </div>
                                     <div class="col-md-2">price</div>
                                     <div class="col-md-2 itemPrice" style="font-size: 15px">
                                         <a href="toyStock?tid="><p>View Stocks</p></a>
                                         <a href="editToy?tid="><p>Edit this Toy</p></a>
                                         <a href="deleteToy?tid="><p>Delete this Toy</p></a>
                                     </div>
                                </div>
                            </div>
                            
                            <h1>New Toys</h1>
                            <div class="newToy">
                                <form>
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Item Name</label>
                                      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Item Name">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Description</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Description">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Original Price</label>
                                      <input type="text" class="form-control" id="exampleInputPassword1" placeholder="Original Price">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Target Age (Inclusive)</label>
                                      <input type="number" class="form-control" id="exampleInputPassword1" placeholder=">Target Age (Inclusive)">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputEmail1">Picture Url</label>
                                      <input type="text" class="form-control" id="exampleInputEmail1" placeholder="Picture Url">
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Sex</label>
                                      <select class="form-control">
                                        <option value = "0">Female</option>
                                        <option value = "1">Male</option>
                                        <option value = "2">Unisex</option>
                                      </select>
                                    </div>
                                    
                                    <div class="form-group">
                                      <label for="exampleInputPassword1">Category</label>
                                      <select class="form-control">
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