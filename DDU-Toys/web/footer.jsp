            <div class="footer">
                <table id="footerTable">
                    <tbody valign="top">
                        <tr>
                            <td>
                                <img width="150px" src="http://cdn6.bigcommerce.com/s-p3ht1/images/stencil/original/capacity_bright_logo_1453415380__93451.png" />
                            </td>
                            <td>
                                <ul class="footerItems">
                                    <li class="ulTitle">CATEGORY</li>
                                    <c:forEach items="${cate}" var="category">
                                        <li><a href="item?category=1">${category.name}</a></li>
                                    </c:forEach>
                                </ul>
                            </td>

                            <td>
                                <ul class="footerItems">
                                    <li class="ulTitle">PAGES</li>
                                    <li>Shop</li>
                                    <li>About Us</li>
                                    <li>Contact Us</li>
                                </ul>
                            </td>

                            <td>
                                <ul class="footerItems">
                                    <li class="ulTitle">PRODUCTS</li>
                                    <li>Brand New</li>
                                    <li>Second Hand</li>
                                </ul>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4">
                                <p id="copyRight">Copyright �2016 DDU-Toys � All Rights Reserved</p>
                                <p style="text-align: center">This web site exists to fulfill the coursework requirement of CS4280. Do not use your real personal data as input.</p>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>