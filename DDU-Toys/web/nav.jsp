             <jsp:useBean id="cate"  class="java.util.ArrayList"  scope="application"/>   
             <jsp:useBean id="isLoggedIn"  type="java.lang.Boolean"  scope="session"/>
             <jsp:useBean id="customer"  class="Bean.Customer"  scope="session"/>
            <div class="nav">
                <div class="centering">
                    <ul id="navItems">
                        <li>Home Page</li>
                        <li>
                            <div class="btn-group">
                                <button type="button" class="btn navBtn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  Categories <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
                                    <c:forEach items="${cate}" var="category">
                                        <li><a href="#">${category.name}</a></li>
                                    </c:forEach>
<!--                                  <li><a href="#"><%--<jsp:getProperty name="cate" property="name" />--%></a></li>-->
                                </ul>
                              </div>
                        </li>
                        <li>Brand New</li>
                        <li>Second Hand Product</li>
                        <li>
                            <div class="btn-group">
                                <button type="button" class="btn navBtn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  Ages <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
<!--                                  <li><a href="#"><%--<jsp:getProperty name="cate" property="name" />--%></a></li>-->

                                  <li><a href="#">0 ~ 3</a></li>
                                  <li><a href="#">4 ~ 7</a></li>
                                  <li><a href="#">7 ~ 9</a></li>
                                  <li><a href="#">10 ~ 12</a></li>
                                </ul>
                              </div>
                        </li>
                    </ul>

                </div>
                <div class="rightNav">
                    <p>
                        <c:if test="${isLoggedIn}">
                            <a class="btn navBtn" href="${pageContext.request.contextPath}/user/" role="button"><c:out value="${customer.username}"/></a>
                        </c:if>
                        <c:if test="${!isLoggedIn}">
                            <a class="btn navBtn" href="${pageContext.request.contextPath}/login" role="button">Login</a>
                        </c:if>
                        
                        <a class="btn navBtn" href="#" role="button">Cart</a>
                    </p>
                </div>
            </div>