<%-- 
    Document   : Cart
    Created on : 2016-4-9, 22:13:55
    Author     : David Liu
    Funtion    : List the items in the user's cart
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>Contains whether the user logged in, user name, how many items in the cart--%>
<%--<jsp:useBean id="items"  class="java.util.Arraylist"  scope="request"/>Contains an array list of the item object which contains the name, img link, description, price for the item, remember to have a int in her eto spercify the number of items--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="isFirstHandCartEmpty"  type="java.lang.Boolean"  scope="request"/>
<jsp:useBean id="isSecondHandCartEmpty"  type="java.lang.Boolean"  scope="request"/>
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
                    
                    <c:if test="${isFirstHandCartEmpty&&isSecondHandCartEmpty}">
                        <p>
                            The cart is empty!
                        </p>
                    </c:if>
                        
                    <c:if test="${!isFirstHandCartEmpty}">
                        <jsp:useBean id="firstHandCartList"  type="java.util.ArrayList"  scope="request"/>
                        <c:forEach items="${firstHandCartList}" var="item">
                            <div class="cartItem">
                                <div class="row">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    </div>
                                    <div class="col-md-6">
                                        <h1 class="itemName">{item.toyInfo.name}</h1>
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
                                        <a href="cart?sid=${item.firstHandItem.id}&action=2&recycle=${item.firstHandItem.recycled}"><p>Delete this item</p></a>
                                    </div>
                                    <div class="col-md-2 itemPrice"> Price</div>
                                    <div class="col-md-2">
                                        <p>Current Quantity: 1</p>
                                        <a href="cart?sid=${item.firstHandItem.id}&action=3&recycle=${item.firstHandItem.recycled}"><p>Current Quantity: 1</p></a>
                                        <a href="cart?sid=${item.firstHandItem.id}&action=4&recycle=${item.firstHandItem.recycled}"><p>Current Quantity: 1</p></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                        
                     <c:if test="${!isSecondHandCartEmpty}">
                        <jsp:useBean id="isSecondHandCartList"  type="java.util.ArrayList"  scope="request"/>
                        <c:forEach items="${isSecondHandCartList}" var="item">
                            <div class="cartItem">
                                <div class="row">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    </div>
                                    <div class="col-md-6">
                                        <h1 class="itemName">{item.toyInfo.name}</h1>
                                        <p class="basicInformation">${item.toyInfo.categoryName} & <c:if test="${(item.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if>
                                            & ${item.toyInfo.age} Years Old & Sold by ${item.customerInfo.username}</p>
                                        <a href="cart?sid=${item.usedItem.id}&action=2&recycle=${item.usedItem.recycled}"><p>Delete this item</p></a>
                                    </div>
                                    <div class="col-md-2 itemPrice"> Price</div>
                                    <div class="col-md-2">
                                        <p>Current Quantity: 1</p>
                                        <a href="cart?sid=${item.usedItem.id}&action=3&recycle=${item.usedItem.recycled}"><p>Current Quantity: 1</p></a>
                                        <a href="cart?sid=${item.usedItem.id}&action=4&recycle=${item.usedItem.recycled}"><p>Current Quantity: 1</p></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    
                    
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
