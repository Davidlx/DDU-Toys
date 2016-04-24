<%-- 
    Document   : Cart
    Created on : 2016-4-9, 22:13:55
    Author     : David Liu
    Funtion    : Presnet the information of a spercific Item
--%>
<%--<jsp:useBean id="userInfo"  class="Bean.***"  scope="session"/>Contains whether the user logged in, user name, how many items in the cart--%>
<jsp:useBean id="isRecycled"  type="java.lang.Boolean"  scope="request"/>
<jsp:useBean id="listComments"  class="java.util.ArrayList"  scope="request"/>
<%--<jsp:useBean id="items"  class="java.util.ArrayList"  scope="request"/> List of similar items--%>
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
                    
                    <c:if test="${isRecycled}">
                        <jsp:useBean id="itemSecond" class="Bean.SpecificBean.SecondHandItem"  scope="request"/>
                        <h1 class="homePageTitle">${itemSecond.toyInfo.name}</h1>
                    
                        <div class="row">
                            <div class="col-md-3">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${itemSecond.toyInfo.picUrl}"/>
                            </div>
                            <div class="col-md-9">
                                <p class="itemName">${itemSecond.toyInfo.name}</p>
                                <p class="basicInformation">${itemSecond.toyInfo.categoryName} · <c:if test="${(itemSecond.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(itemSecond.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(itemSecond.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if> ·For ${itemSecond.toyInfo.age} years above · Sold by ${itemSecond.customerInfo.username}</p>
                                <p class="description"> ${itemSecond.toyInfo.des}</p>
                                <p> ${itemSecond.usedItem.amount} in Stock  · <del>Original Price:$${itemSecond.toyInfo.price}</del></p>
                                <p>Current Price: $${itemSecond.usedItem.price}</p>
                                <a href="cart?sid=${itemSecond.usedItem.id}&recycle=1&action=1"><button type="button" class="btn btn-primary btn-lg">Add to Cart</button></a>
                            </div>
                         </div>
                    </c:if>

                    <c:if test="${!isRecycled}">
                        <jsp:useBean id="itemFirst" class="Bean.SpecificBean.FirstHandItem"  scope="request"/>
                       <h1 class="homePageTitle">${itemFirst.toyInfo.name}</h1>
                    
                        <div class="row">
                            <div class="col-md-3">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; height:400px; display: block;" src="${itemFirst.toyInfo.picUrl}"/>
                            </div>
                            <div class="col-md-9">
                                <p class="itemName">${itemFirst.toyInfo.name}</p>
                                <p class="basicInformation">${itemFirst.toyInfo.categoryName} · <c:if test="${(itemFirst.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(itemFirst.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(itemFirst.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if> · For ${itemFirst.toyInfo.age} years above</p>
                                <p class="description"> ${itemFirst.toyInfo.des}</p>
                                <p> ${itemFirst.firstHandItem.amount} in Stock  · <del>Original Price:$${itemFirst.toyInfo.price}</del></p>
                                <p>Current Price: $${itemFirst.firstHandItem.price}</p>
                                <a href="cart?sid=${itemFirst.firstHandItem.id}&recycle=0&action=1"><button type="button" class="btn btn-primary btn-lg">Add to Cart</button></a>
                            </div>
                         </div>
                    </c:if>
                       
                    <div class="comments">
                        
                        <c:if test="${isLoggedIn}">
                            <p class="CommentsTitle" style="border-top:1px solid rgb(213,213,213)">New Comment</p>
                            <form class="form-horizontal"  method="POST" action="${pageContext.request.contextPath}/newComment">
                                <input type="hidden" name="sid" value="<c:if test="${!isRecycled}">${itemFirst.firstHandItem.id}</c:if><c:if test="${isRecycled}">${itemSecond.usedItem.id}</c:if>">
                                <div class="form-group">
                                  <div class="col-sm-12">
                                    <textarea class="form-control" rows="2" name = "text" ></textarea>
                                  </div>
                                </div>
                                <div class="form-group">
                                  <div class="col-sm-12">
                                    <button type="submit" class="btn btn-default">Submit Comment</button>
                                  </div>
                                </div>
                              </form>
                        </c:if>
                        
                        <p class="CommentsTitle">Comments for <c:if test="${!isRecycled}">${itemFirst.toyInfo.name}</c:if><c:if test="${isRecycled}">${itemSecond.toyInfo.name}</c:if></p>
                         <c:forEach items="${listComments}" var="com">
                            
                             <div class="comment">
                                <p class="commentContent">${com.comment}</p>
                                <p class="CommentorInfo">${com.username} · ${com.postTime}</p>
                                <c:if test="${(com.reply!=null)}">
                                    <div class="replyComment">
                                        <p class="commentContent">${com.reply.comment}</p>
                                        <p class="CommentorInfo">${com.reply.username} · ${com.reply.postTime}</p>
                                    </div>
                                </c:if>
                            </div>
                             
                        </c:forEach>
                        
                    </div>
                    
                    <h1 class="homePageTitle" style="padding-top:50px">Similar Items</h1>
                    <div class="row">
                            <div class="col-sm-6 col-md-3">
                              <div class="thumbnail">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDU4YzAgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NThjMCI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                                <div class="caption">
                                  <h3>Thumbnail label</h3>
                                  <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                              <div class="thumbnail">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDYzOWMgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NjM5YyI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                                <div class="caption">
                                  <h3>Thumbnail label</h3>
                                  <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                              <div class="thumbnail">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDU4OTcgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NTg5NyI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                                <div class="caption">
                                  <h3>Thumbnail label</h3>
                                  <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                                </div>
                              </div>
                            </div>
                            <div class="col-sm-6 col-md-3">
                              <div class="thumbnail">
                                <img data-src="holder.js/100%x200" alt="100%x200" style="width: 100%; display: block;" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iMTkyIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDE5MiAyMDAiIHByZXNlcnZlQXNwZWN0UmF0aW89Im5vbmUiPjwhLS0KU291cmNlIFVSTDogaG9sZGVyLmpzLzEwMCV4MjAwCkNyZWF0ZWQgd2l0aCBIb2xkZXIuanMgMi42LjAuCkxlYXJuIG1vcmUgYXQgaHR0cDovL2hvbGRlcmpzLmNvbQooYykgMjAxMi0yMDE1IEl2YW4gTWFsb3BpbnNreSAtIGh0dHA6Ly9pbXNreS5jbwotLT48ZGVmcz48c3R5bGUgdHlwZT0idGV4dC9jc3MiPjwhW0NEQVRBWyNob2xkZXJfMTUzZmEzNDU4OTcgdGV4dCB7IGZpbGw6I0FBQUFBQTtmb250LXdlaWdodDpib2xkO2ZvbnQtZmFtaWx5OkFyaWFsLCBIZWx2ZXRpY2EsIE9wZW4gU2Fucywgc2Fucy1zZXJpZiwgbW9ub3NwYWNlO2ZvbnQtc2l6ZToxMHB0IH0gXV0+PC9zdHlsZT48L2RlZnM+PGcgaWQ9ImhvbGRlcl8xNTNmYTM0NTg5NyI+PHJlY3Qgd2lkdGg9IjE5MiIgaGVpZ2h0PSIyMDAiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSI3MC4wNTQ2ODc1IiB5PSIxMDQuNSI+MTkyeDIwMDwvdGV4dD48L2c+PC9nPjwvc3ZnPg==" data-holder-rendered="true">
                                <div class="caption">
                                  <h3>Thumbnail label</h3>
                                  <p>Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
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
