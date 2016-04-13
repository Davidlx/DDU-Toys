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
                                        <li><a href="${pageContext.request.contextPath}/items?type=0&subtype=${category.id}">${category.name}</a></li>
                                    </c:forEach>
<!--                                  <li><a href="#"><%--<jsp:getProperty name="cate" property="name" />--%></a></li>-->
                                </ul>
                              </div>
                        </li>
                        <li><a href="${pageContext.request.contextPath}/items?type=1"  style="color:white; text-decoration: none">Second Hand Product</a></li>
                        <li>
                            <div class="btn-group">
                                <button type="button" class="btn navBtn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  Ages <span class="caret"></span>
                                </button>
                                <ul class="dropdown-menu">
<!--                                  <li><a href="#"><%--<jsp:getProperty name="cate" property="name" />--%></a></li>-->

                                  <li><a href="${pageContext.request.contextPath}/items?type=2&subtype1=0&&subtype2=3">0 ~ 3</a></li>
                                  <li><a href="${pageContext.request.contextPath}/items?type=2&subtype1=4&&subtype2=6">4 ~ 6</a></li>
                                  <li><a href="${pageContext.request.contextPath}/items?type=2&subtype1=7&&subtype2=9">7 ~ 9</a></li>
                                  <li><a href="${pageContext.request.contextPath}/items?type=2&subtype1=10&&subtype2=12">10 ~ 12</a></li>
                                  <li><a href="${pageContext.request.contextPath}/items?type=2&subtype1=12&&subtype2=100">12+</a></li>
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
                            <a class="btn navBtn" href="${pageContext.request.contextPath}/login?from=${pageContext.request.requestURI}" role="button">Login</a>
                        </c:if>
                        
                        <a class="btn navBtn" href="#" role="button">Cart</a>
                    </p>
                </div>
            </div>
