<%-- 
    Document   : index
    Created on : 6 avr. 2016, 10:20:47
    Author     : Ugo

    For every page, I will spericify the data I need in the negining of the jsp file. You can commont out the one that I can use o indicate that you have completed your part
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>Contains whether the user logged in, user name, how many items in the cart--%>
<jsp:useBean id="featuredItem"  class="java.util.ArrayList"  scope="request"/><%--Contains an array list of the item object which contains the name, img link, description, price for the item--%>
<jsp:useBean id="featuredUsedItem"  class="java.util.ArrayList"  scope="request"/>



<%--<jsp:useBean id="featuredUsedItem"  class="java.util.Arraylist"  scope="request/application"/>Contains an array list of the second handed item object which contains the name, img link, description, price for the item--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <%@ include file="header.jsp" %>
    </head>
    <body>
        <div class="contents">
            <%@ include file="nav.jsp" %>
            <div class="mainContent">
                <%@ include file="shopTitle.jsp" %>
                <div class="sections">
                    <h1 class="homePageTitle">Featured Products</h1>
                    <div class="row">
                        <c:forEach items="${featuredItem}" var="item">
                            <div class="col-sm-6 col-md-3">
                                <div class="thumbnail" onclick="location.href='${pageContext.request.contextPath}/itemDetail?stockId=${item.firstHandItem.id}'"  style="cursor: pointer">
                                  <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                    <div class="caption">
                                    <h3>${item.toyInfo.name}</h3>
                                    <p>${item.toyInfo.des}</p>
                                    <p class="basicInformation"><c:if test="${(item.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if> & ${item.toyInfo.age} Years Old Above</p>
                                  </div>
                                </div>
                              </div>
                        </c:forEach>
                    </div>
                    <h1 class="homePageTitle">Second Hand Products</h1>
                        <div class="row">
                            
                            <c:forEach items="${featuredUsedItem}" var="item">
                                <div class="col-sm-6 col-md-3">
                                    <div class="thumbnail" onclick="location.href='${pageContext.request.contextPath}/itemDetail?stockId=${item.usedItem.id}'"  style="cursor: pointer">
                                      <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                        <div class="caption">
                                        <h3>${item.toyInfo.name}</h3>
                                        <p>${item.toyInfo.des}</p>
                                        <p class="basicInformation"><c:if test="${(item.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if> & ${item.toyInfo.age} Years Old Above & Sold by ${item.customerInfo.username}</p>
                                        <p class="basicInformation">Conditional Description: ${item.usedItem.conDes}</p>
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
