<%-- 
    Document   : Cart
    Created on : 2016-4-9, 22:13:55
    Author     : David Liu
    Funtion    : List the items in the user's cart
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>Contains whether the user logged in, user name, how many items in the cart--%>
<%--<jsp:useBean id="items"  class="java.util.Arraylist"  scope="request"/>Contains an array list of the item object which contains the name, img link, description, price for the item, remember to have a int in her eto spercify the number of items--%>
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
                <div class="sections">
                    <h1 class="homePageTitle">Shopping Cart</h1>
                    <div class="cartItem">
                        <div class="row">
                            <div class="col-md-2">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 70%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDU4YzAgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NThjMCI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                            </div>
                            <div class="col-md-6">
                                <h1 class="itemName">Snacks</h1>
                                <p class="basicInformation">Category · Sex · Age</p>
                                <a href=""><p>Delete this item</p></a>
                            </div>
                            <div class="col-md-2 itemPrice"> Price</div>
                            <div class="col-md-2">1</div>
                        </div>
                    </div>
                    <div class="cartSummary">
                        <h1>Subtotal (n items): Price</h1>
                        <button type="button" class="btn btn-success">Proceed to Checkout</button>
                    </div>
                </div>
            </div>
                <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
