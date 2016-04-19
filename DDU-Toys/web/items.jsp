<%-- 
    Document   : Items
    Created on : 2016-4-9, 22:13:55
    Author     : David Liu
    Funtion    : List the items in Category/or other search rules
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>--%>
<jsp:useBean id="items"  class="java.util.ArrayList"  scope="request"/>
<jsp:useBean id="scope"  class="java.lang.String"  scope="request"/>
<jsp:useBean id="firstHand"  type="java.lang.Boolean"  scope="request"/>
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
                    <h1 class="homePageTitle">${scope}</h1>
                    <c:set var="count" value="0" scope="page" />
                    <c:forEach items="${items}" var="item">
                        
                        
                        
                         <c:if test="${(count % 4 ==0)}">
                            <div class="row">
                        </c:if>
                                
                                <div class="col-sm-6 col-md-3">
                                  <div class="thumbnail">
                                     <c:if test="${firstHand}">
                                     <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${item.toyInfo.picUrl}"/> 
                                     <div class="caption">
                                        <h3>${item.toyInfo.name}</h3>
                                        <p>${item.toyInfo.des}</p>
                                        <p class="basicInformation">
                                            <c:if test="${(item.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if>
                                            & ${item.toyInfo.age} Years Old</p>
                                    </div>
                                    </c:if>
                                      <c:if test="${!firstHand}">
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
                                            </c:if> & ${item.toyInfo.age} Years Old & Sold by ${item.customerInfo.username}</p>
                                    </div>
                                    </c:if>
                                  </div>
                                </div>
                             
                        <c:if test="${(count % 4 ==3)}">
                            </div>
                        </c:if>
                                
                        <c:set var="count" value="${count + 1}" scope="page"/>
                    </c:forEach>
                     <c:if test="${(count % 4 !=0)}">
                            </div>
                        </c:if>
                   
                </div>
            </div>
                <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>
