<!-- Header -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<header class="header">
    <!-- Topbar -->
    <div class="topbar">
        <div class="container">
            <div class="row">
                <div class="col-lg-7 col-12">
                    <!-- Top Contact -->
                    <div class="top-contact">
                        <div class="single-contact"><i class="fa fa-phone"></i>Phone: 0961 535 596</div>
                        <div class="single-contact"><i class="fa fa-envelope-open"></i>Email: duyshop@gmail.com</div>
                        <div class="single-contact"><i class="fa fa-clock-o"></i>Opening: 08AM-09PM</div>
                    </div>
                    <!-- End Top Contact -->
                </div>
                <div class="col-lg-5 col-12">
                    <div class="topbar-right">
                        <!-- Social Icons -->

                        <ul class="social-icons">
                            <c:if test="${param.img.length()>0}">
                                <li><img src="<c:url value="/image/${param.img}"/>" width="50px"></li>
                            </c:if>
                            <li><label style="color: white" class="mx-2">${param.name}</label></li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                            <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                            <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
                        </ul>
                        <div class="button">
                            <c:if test="${param.name.length()>0}">
                                <a href="<c:url value="/signin"/>" class="bizwheel-btn">Đăng xuất</a>
                            </c:if>
                            <c:if test="${param.name.length()<1}">
                                <a href="<c:url value="/signin"/>" class="bizwheel-btn">Đăng nhập</a>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ End Topbar -->
    <!-- Middle Header -->
    <div class="middle-header">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="middle-inner">
                        <div class="row">
                            <div class="col-lg-2 col-md-3 col-12">
                                <!-- Logo -->
                                <div class="logo">
                                    <!-- Image Logo -->
                                    <div class="img-logo">
                                        <a href="<c:url value="/signin"/>">
                                            <img src="<c:url value="/Img/logo.png"/>" alt="#">
                                        </a>
                                    </div>
                                </div>
                                <div class="mobile-nav"></div>
                            </div>
                            <div class="col-lg-10 col-md-9 col-12">
                                <div class="menu-area">
                                    <!-- Main Menu -->
                                    <nav class="navbar navbar-expand-lg">
                                        <div class="navbar-collapse">
                                            <div class="nav-inner">
                                                <div class="menu-home-menu-container">
                                                    <!-- Naviagiton -->
                                                    <ul id="nav" class="nav main-menu menu navbar-nav">
                                                        <li><a href="<c:url value="/index"/>">Trang chủ</a></li>
                                                        <li><a href="<c:url value="/service"/>">Dịch vụ</a></li>
                                                        <li><a href="#">Diễn đàn</a></li>
                                                        <li class="icon-active"><a href="#">Tài khoản của bạn</a>
                                                            <ul class="sub-menu">
                                                                <li><a href="<c:url value="/myaccount"/>">Thông tin</a>
                                                                </li>
                                                                <li><a href="<c:url value="/mycart"/>">Giỏ hàng</a></li>
                                                                <li><a href="<c:url value="/myproduct"/>">Sản phẩm</a>
                                                                </li>
                                                                <li><a href="<c:url value="/history"/>">Đơn hàng</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                        <li class="icon-active"><a href="#">Ngôn ngữ</a>
                                                            <ul class="sub-menu">
                                                                <li>
                                                                    <a href="#"
                                                                       onclick="doGTranslate('vi|vi');return false;"
                                                                       title="Tiếng Việt" class="gflag nturl"
                                                                    ><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Flag_of_North_Vietnam_%281955%E2%80%931976%29.svg/230px-Flag_of_North_Vietnam_%281955%E2%80%931976%29.svg.png"
                                                                          style="width: 25px"
                                                                          alt="English"/> Tiếng Việt</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#"
                                                                       onclick="doGTranslate('vi|zh-CN');return false;"
                                                                       title="Tiếng Trung" class="gflag nturl"
                                                                    ><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Flag_of_the_People%27s_Republic_of_China.svg/225px-Flag_of_the_People%27s_Republic_of_China.svg.png"
                                                                          style="width: 25px"
                                                                          alt="English"/> Tiếng Trung</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#"
                                                                       onclick="doGTranslate('vi|en');return false;"
                                                                       title="Tiếng Anh" class="gflag nturl"
                                                                    ><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/a/ae/Flag_of_the_United_Kingdom.svg/300px-Flag_of_the_United_Kingdom.svg.png"
                                                                          style="width: 25px"
                                                                          alt="English"/> Tiếng Anh</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#"
                                                                       onclick="doGTranslate('vi|ko');return false;"
                                                                       title="Tiếng Hàn" class="gflag nturl"
                                                                    ><img src="https://globalmap.edu.vn/wp-content/uploads/2020/10/flag_of_the_provisional_government_of_the_republic_of_korea-svg.png"
                                                                          style="width: 25px"
                                                                          alt="English"/> Tiếng Hàn</a>
                                                                </li>
                                                                <li>
                                                                    <a href="#"
                                                                       onclick="doGTranslate('vi|ja');return false;"
                                                                       title="Tiếng Nhật" class="gflag nturl"
                                                                    ><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Flag_of_Japan.svg/225px-Flag_of_Japan.svg.png"
                                                                          style="width: 25px"
                                                                          alt="English"/> Tiếng Nhật</a>
                                                                </li>
                                                            </ul>
                                                        </li>
                                                    </ul>
                                                    <!--/ End Naviagiton -->
                                                </div>
                                            </div>
                                        </div>
                                    </nav>
                                    <!--/ End Main Menu -->
                                    <!-- Right Bar -->
                                    <div class="right-bar">
                                        <!-- Search Bar -->
                                        <ul class="right-nav">
                                            <li class="top-search"><a href="#0"><i class="fa fa-search"></i></a></li>
                                            <li class="bar"><a class="fa fa-bars"></a></li>
                                        </ul>
                                        <!--/ End Search Bar -->
                                        <!-- Search Form -->
                                        <div class="search-top">
                                            <form action="#" class="search-form" method="get">
                                                <input type="text" name="s" value="" placeholder="Search here"/>
                                                <button type="submit" id="searchsubmit"><i class="fa fa-search"></i>
                                                </button>
                                            </form>
                                        </div>
                                        <!--/ End Search Form -->
                                    </div>
                                    <!--/ End Right Bar -->
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--/ End Middle Header -->
    <!-- Sidebar Popup -->
    <div class="sidebar-popup">
        <div class="cross">
            <a class="btn"><i class="fa fa-close"></i></a>
        </div>
        <div class="single-content">
            <h4>About Bizwheel</h4>
            <p>The main component of a healthy environment for self esteem is that it needs be nurturing. It should
                provide unconditional warmth.</p>
            <!-- Social Icons -->
            <ul class="social">
                <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
                <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
            </ul>
        </div>
        <div class="single-content">
            <h4>Important Links</h4>
            <ul class="links">
                <li><a href="#">About Us</a></li>
                <li><a href="#">Our Services</a></li>
                <li><a href="#">Portfolio</a></li>
                <li><a href="#">Pricing Plan</a></li>
                <li><a href="#">Blog & News</a></li>
                <li><a href="#">Contact us</a></li>
            </ul>
        </div>
    </div>
    <!--/ Sidebar Popup -->
</header>
<!--/ End Header -->

<!-- Hero Slider -->
<!--/ End Hero Slider -->
