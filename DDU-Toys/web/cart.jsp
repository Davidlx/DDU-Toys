<%-- 
    Document   : Cart
    Created on : 2016-4-9, 22:13:55
    Author     : David Liu
    Funtion    : List the items in the user's cart
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>Contains whether the user logged in, user name, how many items in the cart--%>
<%--<jsp:useBean id="items"  class="java.util.Arraylist"  scope="request"/>Contains an array list of the item object which contains the name, img link, description, price for the item, remember to have a int in her eto spercify the number of items--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="isFirstHandCartEmpty"  type="java.lang.Boolean"  scope="session"/>
<jsp:useBean id="isSecondHandCartEmpty"  type="java.lang.Boolean"  scope="session"/>
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
                        <p style="text-align: center">
                            The cart is empty!
                        </p>
                    </c:if>
                        
                    <c:if test="${!isFirstHandCartEmpty}">
                        <jsp:useBean id="firstHandCartList"  type="java.util.ArrayList"  scope="session"/>
                        <c:forEach items="${firstHandCartList}" var="item">
                            <div class="cartItem">
                                <div class="row">
                                    <div class="col-md-1">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:100px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    </div>
                                    <div class="col-md-7">
                                        <h1 class="itemName" onclick="location.href='${pageContext.request.contextPath}/itemDetail?stockId=${item.firstHandItem.id}'"  style="cursor: pointer">${item.toyInfo.name}</h1>
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
                                    <div class="col-md-2 itemPrice"> $${item.firstHandItem.price}</div>
                                    <div class="col-md-2">
                                        <p>Current Quantity: ${item.itemAmount}</p>
                                        <a href="cart?sid=${item.firstHandItem.id}&action=3&recycle=${item.firstHandItem.recycled}"><p>Increase Quantity</p></a>
                                        <a href="cart?sid=${item.firstHandItem.id}&action=4&recycle=${item.firstHandItem.recycled}"><p>Decrease Quantity</p></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                        
                     <c:if test="${!isSecondHandCartEmpty}">
                        <jsp:useBean id="secondHandCartList"  type="java.util.ArrayList"  scope="session"/>
                        <c:forEach items="${secondHandCartList}" var="item">
                            <div class="cartItem">
                                <div class="row">
                                    <div class="col-md-2">
                                        <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:100px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    </div>
                                    <div class="col-md-6">
                                        <h1 class="itemName" onclick="location.href='${pageContext.request.contextPath}/itemDetail?stockId=${item.usedItem.id}'"  style="cursor: pointer">${item.toyInfo.name}</h1>
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
                                    <div class="col-md-2 itemPrice"> $${item.usedItem.price}</div>
                                    <div class="col-md-2">
                                        <p>Current Quantity: ${item.itemAmount}</p>
                                        <a href="cart?sid=${item.usedItem.id}&action=3&recycle=${item.usedItem.recycled}"><p>Increase Quantity</p></a>
                                        <a href="cart?sid=${item.usedItem.id}&action=4&recycle=${item.usedItem.recycled}"><p>Decrease Quantity</p></a>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    
                    
                    <c:if test="${!isFirstHandCartEmpty||!isSecondHandCartEmpty}">
                        <div class="cartSummary">
                            <h1>Subtotal (${firstHandCartAmount+secondHandCartAmount} items): $${ firstHandCartPrice+secondHandCartPrice}</h1>
                            <a href="checkOut"><button type="button" class="btn btn-success">Proceed to Checkout</button></a>
                        </div>
                    </c:if>
                        
                    <h1 class="homePageTitle">Featured Products</h1>
                    <div class="row">
                        <c:forEach items="${featuredItem}" var="item">
                            <div class="col-sm-6 col-md-3">
                                <div class="thumbnail" onclick="location.href='${pageContext.request.contextPath}/itemDetail?stockId=${item.firstHandItem.id}'"  style="cursor: pointer">
                                  <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    <div class="caption">
                                    <h3>${item.toyInfo.name}</h3>
                                    <p>${item.toyInfo.des}</p>
                                    <p class="basicInformation">${item.toyInfo.sex} & ${item.toyInfo.age} Years Old</p>
                                  </div>
                                </div>
                              </div>
                        </c:forEach>
                    </div>
                        
                </div>
            </div>
                <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
