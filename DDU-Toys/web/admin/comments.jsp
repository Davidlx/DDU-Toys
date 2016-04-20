<%-- 
    Document   : userIndex
    Created on : 2016-4-9, 22:15:27
    Author     : David Liu

Fetch an array list of the comments attached to the stock, information needed spercified in the table, and rememeber to have an stock bean contains the toy information

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
                            <h1>Comments for "Toy Name"</h1>
                            <div class="row borderBottom" style="margin-top: 10px;">
                                <div class="col-md-2">
                                    <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;padding-bottom: 10px;width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDU4YzAgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NThjMCI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                                </div>
                                 <div class="col-md-6">
                                    <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">Snacks</h1>
                                    <p class="basicInformation">Category · Sex · Age · Brand New / Second Hand</p>
                                    <p>description</p>
                                </div>
                                 <div class="col-md-2">1</div>
                                 <div class="col-md-2 itemPrice">Price</div>
                            </div>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <td>ID</td>
                                        <td>Comment</td>
                                        <td>Commentor</td>
                                        <td>Time</td>
                                        <td>Reply Message</td>
                                        <td>Reply Time</td>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                </thead>
                                <tr>
                                    <td>ID</td>
                                        <td>Comment</td>
                                        <td>Commentor</td>
                                        <td>Time</td>
                                        <td>Reply Message</td>
                                        <td>Reply Time</td>
                                        <td><a  data-toggle="modal" data-target="#replyid"><p>Reply Comments</p></a></td>
                                </tr>
                                
                            </table>
                            <div class="modal fade" id="replyid" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                    <div class="modal-dialog" role="document">
                                      <div class="modal-content">
                                        <div class="modal-header">
                                          <h4 class="modal-title" id="myModalLabel">Reply Message</h4>
                                        </div>
                                        <form class="form-horizontal">
                                        <div class="modal-body">
                                            <p>Original Messages</p>
                                            
                                                <div class="form-group">
                                                  <div class="col-sm-12">
                                                    <textarea class="form-control" rows="2"></textarea>
                                                  </div>
                                                </div>
                                            
                                        </div>
                                        <div class="modal-footer">
                                          <button type="submit" class="btn btn-primary">Submit</button>
                                        </div>
                                        </form>
                                      </div>
                                    </div>
                                  </div>
                        </div>
                    </div>
                </div>
                
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>