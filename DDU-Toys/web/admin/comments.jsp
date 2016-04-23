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
                                    <img data-src="holder.js/100%x200" alt="100%x200" style="padding-left: 10px;padding-bottom: 10px;width: 100%; display: block;" src="${item.toyInfo.picUrl}"/>
                                </div>
                                 <div class="col-md-6">
                                    <h1 class="itemName" style="border:none;padding-top: 0; margin-top: 0px;">${item.toyInfo.name}</h1>
                                    <p class="basicInformation">${item.toyInfo.categoryName} · <c:if test="${(item.toyInfo.sex ==0)}">
                                                Female
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==1)}">
                                                Male
                                            </c:if>
                                            <c:if test="${(item.toyInfo.sex ==2)}">
                                                Unisex
                                            </c:if> · ${item.toyInfo.age} · <c:if test="${type==0}">Brand New</c:if> <c:if test="${type==1}">Second Hand · Sold by ${item.customerInfo.username}</c:if></p>
                                    <p>${item.toyInfo.des}</p>
                                </div>
                                    <div class="col-md-2"><c:if test="${type==0}">${item.firstHandItem.amount}</c:if> <c:if test="${type==1}">${item.usedItem.amount}</c:if> in stock</div>
                                    <div class="col-md-2 itemPrice">
                                         <p><del>Original Price: ${item.toyInfo.price}</del></p>
                                        <p>Current Price: <c:if test="${type==0}">${item.firstHandItem.price}</c:if> <c:if test="${type==1}">${item.usedItem.price}</c:if></p>
                                    </div>
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
                                <tbody>
                                    <c:forEach items="${comments}" var="comment">
                                    <tr>
                                        <td>${comment.id}</td>
                                        <td>${comment.comment}</td>
                                        <td>${comment.username}</td>
                                        <td>${comment.postTime}</td>
                                        <td><c:if test="${comment.reply==null}">Not replied yet</c:if><c:if test="${comment.reply!=null}">${comment.reply.comment}</c:if></td>
                                        <td><c:if test="${comment.reply==null}"></c:if><c:if test="${comment.reply!=null}">${comment.reply.postTime}</c:if></td>
                                        <td><c:if test="${comment.reply==null}"><a  data-toggle="modal" data-target="#reply${comment.id}"><p>Reply Comments</p></a></c:if></td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <c:forEach items="${comments}" var="comment">
                            <div class="modal fade" id="reply${comment.id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                <div class="modal-dialog" role="document">
                                  <div class="modal-content">
                                    <div class="modal-header">
                                      <h4 class="modal-title" id="myModalLabel">Reply Message</h4>
                                    </div>
                                    <form class="form-horizontal">
                                    <div class="modal-body">
                                        <p>Original Messages</p>
                                        <p>${comment.comment}</p>
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
                            </c:forEach>
                        </div>
                    </div>
                </div>
                
            </div>
            <%@ include file="footer.jsp" %>
        </div>
    </body>
</html>