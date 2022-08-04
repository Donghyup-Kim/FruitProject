<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<%@ page import="kopo.poly.util.CmmUtil" %>
<%@ page import="kopo.poly.dto.FileInfoDTO" %>
<%@ page import="kopo.poly.dto.ProductDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>


<%

    ProductDTO nDTO = (ProductDTO) request.getAttribute("nDTO");
    FileInfoDTO fDTO = (FileInfoDTO) request.getAttribute("fDTO");

    String user_id = CmmUtil.nvl((String) session.getAttribute("SS_USER_ID"));
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Product Detail</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="/images/icons/favicon.png"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/iconic/css/material-design-iconic-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/fonts/linearicons-v1.0.0/icon-font.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/animsition/css/animsition.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/daterangepicker/daterangepicker.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/slick/slick.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/MagnificPopup/magnific-popup.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/vendor/perfect-scrollbar/perfect-scrollbar.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="/css/util.css">
    <link rel="stylesheet" type="text/css" href="/css/main.css">
    <!--===============================================================================================-->
    <script src="/js/jquery-3.6.0.min.js"></script>


</head>
<body class="animsition">


<!-- Header -->
<!-- Header desktop -->
<div class="container-menu-desktop">
    <!-- Topbar -->
    <div class="top-bar">
        <div class="content-topbar flex-sb-m h-full container">
            <div class="left-top-bar">

            </div>

            <div class="right-top-bar flex-w h-full">
                <a href="/loginPage" class="flex-c-m trans-04 p-lr-25">
                    LoginPage
                </a>
                <a href="/Sellpage" class="flex-c-m trans-04 p-lr-25">
                    Sellpage
                </a>

                <a href="/userpage" class="flex-c-m trans-04 p-lr-25">
                    Userpage
                </a>

                <a href="/userlogout" class="flex-c-m trans-04 p-lr-25">
                    LogOut
                </a>

                <a href="/itemprice" class="flex-c-m trans-04 p-lr-25">
                    평균가격 조회
                </a>
            </div>
        </div>
    </div>

    <div class="wrap-menu-desktop" style="top: 40px; margin-bottom: 50px;">
        <nav class="limiter-menu-desktop container">

            <!-- Logo desktop -->
            <span class="ltext-101 cl2 respon2" style="color: cornflowerblue; font-weight:bold">
									Dong Store
								</span>

            <!-- Menu desktop -->
            <div class="menu-desktop">
                <ul class="main-menu">
                    <li class="active-menu">
                        <a href="/index">Home</a>
                        <ul class="sub-menu">
                            <li><a href="index.html">Homepage 1</a></li>
                            <li><a href="home-02.html">Homepage 2</a></li>
                            <li><a href="home-03.html">Homepage 3</a></li>
                        </ul>
                    </li>

                    <li>
                        <a href="/index">Shop</a>
                    </li>
                    <li>
                        <a href="/board/boardlist">Board</a>
                    </li>

                </ul>
            </div>

            <!-- Icon header -->

            <div><%=session.getAttribute("SS_USER_ID")%></div>



                <!-- Icon header -->
                <div class="wrap-icon-header flex-w flex-r-m">
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 js-show-modal-search">
                        <i class="zmdi zmdi-search"></i>
                    </div>
                    <div class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti js-show-cart" data-notify="2">
                        <i class="zmdi zmdi-shopping-cart"></i>
                    </div>

                    <a href="#" class="icon-header-item cl2 hov-cl1 trans-04 p-l-22 p-r-11 icon-header-noti" data-notify="0">
                        <i class="zmdi zmdi-favorite-outline"></i>
                    </a>


                </div>


        </nav>
    </div>
</div>

<!-- Header Mobile -->
<div class="wrap-header-mobile">
    <!-- Logo moblie -->
    <div class="logo-mobile">
        Dong store
    </div>


</div>

            </nav>
        </div>
    </div>



    <!-- Button show menu -->

</header>


<!-- Menu Mobile -->
<div class="menu-mobile">
    <ul class="topbar-mobile">
        <li>
            <div class="left-top-bar">

            </div>
        </li>

        <li>
            <div class="right-top-bar flex-w h-full">
                <a href="#" class="flex-c-m p-lr-10 trans-04">
                    Help & FAQs
                </a>

                <a href="#" class="flex-c-m p-lr-10 trans-04">
                    My Account
                </a>

                <a href="#" class="flex-c-m p-lr-10 trans-04">
                    EN
                </a>

                <a href="#" class="flex-c-m p-lr-10 trans-04">
                    USD
                </a>
            </div>
        </li>
    </ul>

    <ul class="main-menu-m">
        <li>
            <a href="/index">Home</a>
            <ul class="sub-menu-m">
                <li><a href="/index">Homepage 1</a></li>
                <li><a href="/home-02.html">Homepage 2</a></li>
                <li><a href="/home-03.html">Homepage 3</a></li>
            </ul>
            <span class="arrow-main-menu-m">
						<i class="fa fa-angle-right" aria-hidden="true"></i>
					</span>
        </li>

        <li>
            <a href="/product.html">Shop</a>
        </li>

        <li>
            <a href="/shoping-cart.html" class="label1 rs1" data-label1="hot">Features</a>
        </li>

    </ul>
</div>


<!-- Cart -->
<div class="wrap-header-cart js-panel-cart">
    <div class="s-full js-hide-cart"></div>

    <div class="header-cart flex-col-l p-l-65 p-r-25">
        <div class="header-cart-title flex-w flex-sb-m p-b-8">
				<span class="mtext-103 cl2">
					Your Cart
				</span>

            <div class="fs-35 lh-10 cl2 p-lr-5 pointer hov-cl1 trans-04 js-hide-cart">
                <i class="zmdi zmdi-close"></i>
            </div>
        </div>

        <div class="header-cart-content flex-w js-pscroll">
            <ul class="header-cart-wrapitem w-full" id="cart_append">
                <li class="header-cart-item flex-w flex-t m-b-12">
                    <div class="header-cart-item-img">
                    </div>


                </li>

                <li class="header-cart-item flex-w flex-t m-b-12">
                    <div class="header-cart-item-img">

                    </div>

                    <div class="header-cart-item-txt p-t-8">
                        <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">

                        </a>

                        <span class="header-cart-item-info">

							</span>
                    </div>
                </li>

                <li class="header-cart-item flex-w flex-t m-b-12">
                    <div class="header-cart-item-img">
                    </div>

                    <div class="header-cart-item-txt p-t-8">
                        <a href="#" class="header-cart-item-name m-b-18 hov-cl1 trans-04">

                        </a>

                        <span class="header-cart-item-info">

							</span>
                    </div>
                </li>
            </ul>

            <div class="w-full">
                <div class="header-cart-total w-full p-tb-40">

                </div>

                <div class="header-cart-buttons flex-w w-full">

                    <a href="/shoping-cart.html" class="flex-c-m stext-101 cl0 size-107 bg3 bor2 hov-btn3 p-lr-15 trans-04 m-b-10">
                        Check Out
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- breadcrumb -->



<!-- Product Detail -->
<section class="sec-product-detail bg0 p-t-65 p-b-60">
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-lg-7 p-b-30">
                <div class="p-l-25 p-r-30 p-lr-0-lg">

                    <div class="wrap-slick3-dots"></div>
                    <div class="wrap-slick3-arrows flex-sb-m flex-w"></div>

                    <div class="slick3 gallery-lb">
                        <div class="item-slick3" data-thumb="images/product-detail-01.jpg">
                            <div class="wrap-pic-w pos-relative">
                                <img src="<%=CmmUtil.nvl(fDTO.getFile_detail_path())%>" alt="IMG-PRODUCT">

                                <a class="flex-c-m size-108 how-pos1 bor0 fs-16 cl10 bg0 hov-btn3 trans-04" href="<%=CmmUtil.nvl(fDTO.getFile_detail_path())%>">
                                    <i class="fa fa-expand"></i>
                                </a>
                            </div>
                        </div>
                    </div>

                </div>
            </div>


            <div class="col-md-6 col-lg-5 p-b-30">

                <div class="p-r-50 p-t-5 p-lr-0-lg">

                    <h4 class="mtext-105 cl2 js-name-detail p-b-14">
                        상품명 : <%=CmmUtil.nvl(nDTO.getPro_title())%>
                    </h4>

                    <span class="mtext-106 cl2">
							가격 : <%=CmmUtil.nvl(nDTO.getPro_price())%>
						</span>

                    <p class="stext-102 cl3 p-t-23" style="padding-bottom: 50px">
                        설명 : <%=CmmUtil.nvl(nDTO.getPro_content())%>
                    </p>
                    <div class="flex-w flex-r-m p-b-10">
                        <div class="size-204 flex-w flex-m respon6-next">

                            <input type="hidden" id="add_cart" name="add_cart" value="">
                            <button id="cart_add" class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail" type="button" onclick="product_add();">
                                Add to cart
                            </button>
                            <form action="/orderlogic">
                                <input type="hidden" id="product_seq" name="product_seq" value="<%=(nDTO.getPro_seq())%>">
                                <button class="flex-c-m stext-101 cl0 size-101 bg1 bor1 hov-btn1 p-lr-15 trans-04 js-addcart-detail" type="submit">
                                    Buy
                                </button>
                            </form>
                        </div>
                    </div>
                </div>



                <!--  -->
                <div class="flex-w flex-m p-l-100 p-t-40 respon7">
                    <div class="flex-m bor9 p-r-10 m-r-11">
                        <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 js-addwish-detail tooltip100" data-tooltip="Add to Wishlist">
                            <i class="zmdi zmdi-favorite"></i>
                        </a>
                    </div>

                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Facebook">
                        <i class="fa fa-facebook"></i>
                    </a>

                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Twitter">
                        <i class="fa fa-twitter"></i>
                    </a>

                    <a href="#" class="fs-14 cl3 hov-cl1 trans-04 lh-10 p-lr-5 p-tb-2 m-r-8 tooltip100" data-tooltip="Google Plus">
                        <i class="fa fa-google-plus"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <div class="bor10 m-t-50 p-t-43 p-b-40">
        <!-- Tab01 -->
        <div class="tab01">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li class="nav-item p-b-10">
                    <a class="nav-link active" data-toggle="tab" href="#description" role="tab">Description</a>
                </li>


                <li class="nav-item p-b-10">
                    <a class="nav-link" data-toggle="tab" href="#reviews" role="tab">Reviews (1)</a>
                </li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content p-t-43">
                <!-- - -->
                <div class="tab-pane fade show active" id="description" role="tabpanel">
                    <div class="how-pos2 p-lr-15-md" >
                        <p class="stext-102 cl6" style="margin: auto; font-size : 20px" >
                            <%=CmmUtil.nvl(nDTO.getPro_content())%>
                        </p>
                    </div>
                </div>

                <!-- - -->
                <div class="tab-pane fade" id="information" role="tabpanel">
                    <div class="row">
                        <div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
                            <ul class="p-lr-28 p-lr-15-sm">
                                <li class="flex-w flex-t p-b-7">
											<span class="stext-102 cl3 size-205">
												Weight
											</span>

                                    <span class="stext-102 cl6 size-206">
												0.79 kg
											</span>
                                </li>

                                <li class="flex-w flex-t p-b-7">
											<span class="stext-102 cl3 size-205">
												Dimensions
											</span>

                                    <span class="stext-102 cl6 size-206">
												110 x 33 x 100 cm
											</span>
                                </li>

                                <li class="flex-w flex-t p-b-7">
											<span class="stext-102 cl3 size-205">
												Materials
											</span>

                                    <span class="stext-102 cl6 size-206">
												60% cotton
											</span>
                                </li>

                                <li class="flex-w flex-t p-b-7">
											<span class="stext-102 cl3 size-205">
												Color
											</span>

                                    <span class="stext-102 cl6 size-206">
												Black, Blue, Grey, Green, Red, White
											</span>
                                </li>

                                <li class="flex-w flex-t p-b-7">
											<span class="stext-102 cl3 size-205">
												Size
											</span>

                                    <span class="stext-102 cl6 size-206">
												XL, L, M, S
											</span>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <!-- - -->
                <div class="tab-pane fade" id="reviews" role="tabpanel">
                    <div class="row">
                        <div class="col-sm-10 col-md-8 col-lg-6 m-lr-auto">
                            <div class="p-b-30 m-lr-15-sm">
                                <!-- Review -->
                                <div class="flex-w flex-t p-b-68">
                                    <div class="wrap-pic-s size-109 bor0 of-hidden m-r-18 m-t-6">
                                        <img src="/images/avatar-01.jpg" alt="AVATAR">
                                    </div>

                                    <div class="size-207">
                                        <div class="flex-w flex-sb-m p-b-17">
													<span class="mtext-107 cl2 p-r-20">
														Ariana Grande
													</span>

                                            <span class="fs-18 cl11">
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star"></i>
														<i class="zmdi zmdi-star-half"></i>
													</span>
                                        </div>

                                        <p class="stext-102 cl6">
                                            Quod autem in homine praestantissimum atque optimum est, id deseruit. Apud ceteros autem philosophos
                                        </p>
                                    </div>
                                </div>

                                <!-- Add review -->
                                <form class="w-full">
                                    <h5 class="mtext-108 cl2 p-b-7">
                                        Add a review
                                    </h5>

                                    <p class="stext-102 cl6">
                                        Your email address will not be published. Required fields are marked *
                                    </p>

                                    <div class="flex-w flex-m p-t-50 p-b-23">
												<span class="stext-102 cl3 m-r-16">
													Your Rating
												</span>

                                        <span class="wrap-rating fs-18 cl11 pointer">
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<i class="item-rating pointer zmdi zmdi-star-outline"></i>
													<input class="dis-none" type="number" name="rating">
												</span>
                                    </div>

                                    <div class="row p-b-25">
                                        <div class="col-12 p-b-5">
                                            <label class="stext-102 cl3" for="review">Your review</label>
                                            <textarea class="size-110 bor8 stext-102 cl2 p-lr-20 p-tb-10" id="review" name="review"></textarea>
                                        </div>

                                        <div class="col-sm-6 p-b-5">
                                            <label class="stext-102 cl3" for="name">Name</label>
                                            <input class="size-111 bor8 stext-102 cl2 p-lr-20" id="name" type="text" name="name">
                                        </div>

                                        <div class="col-sm-6 p-b-5">
                                            <label class="stext-102 cl3" for="email">Email</label>
                                            <input class="size-111 bor8 stext-102 cl2 p-lr-20" id="email" type="text" name="email">
                                        </div>
                                    </div>

                                    <button class="flex-c-m stext-101 cl0 size-112 bg7 bor11 hov-btn3 p-lr-15 trans-04 m-b-10">
                                        Submit
                                    </button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

    <div class="bg6 flex-c-m flex-w size-302 m-t-73 p-tb-15">
			<span class="stext-107 cl6 p-lr-25">

			</span>

        <span class="stext-107 cl6 p-lr-25">

			</span>
    </div>
</section>



<!-- Footer -->
<footer class="bg3 p-t-75 p-b-32">
    <div class="container">
        <div class="row">
            <div class="col-sm-6 col-lg-3 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    Categories
                </h4>

                <ul>
                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Women
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Men
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Shoes
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Watches
                        </a>
                    </li>
                </ul>
            </div>

            <div class="col-sm-6 col-lg-3 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    Help
                </h4>

                <ul>
                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Track Order
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Returns
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            Shipping
                        </a>
                    </li>

                    <li class="p-b-10">
                        <a href="#" class="stext-107 cl7 hov-cl1 trans-04">
                            FAQs
                        </a>
                    </li>
                </ul>
            </div>

            <div class="col-sm-6 col-lg-3 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    GET IN TOUCH
                </h4>

                <p class="stext-107 cl7 size-201">
                    Any questions? Let us know in store at 8th floor, 379 Hudson St, New York, NY 10018 or call us on (+1) 96 716 6879
                </p>

                <div class="p-t-27">
                    <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                        <i class="fa fa-facebook"></i>
                    </a>

                    <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                        <i class="fa fa-instagram"></i>
                    </a>

                    <a href="#" class="fs-18 cl7 hov-cl1 trans-04 m-r-16">
                        <i class="fa fa-pinterest-p"></i>
                    </a>
                </div>
            </div>

            <div class="col-sm-6 col-lg-3 p-b-50">
                <h4 class="stext-301 cl0 p-b-30">
                    Newsletter
                </h4>

                <form>
                    <div class="wrap-input1 w-full p-b-4">
                        <input class="input1 bg-none plh1 stext-107 cl7" type="text" name="email" placeholder="email@example.com">
                        <div class="focus-input1 trans-04"></div>
                    </div>

                    <div class="p-t-18">
                        <button class="flex-c-m stext-101 cl0 size-103 bg1 bor1 hov-btn2 p-lr-15 trans-04">
                            Subscribe
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <div class="p-t-40">
            <div class="flex-c-m flex-w p-b-18">
                <a href="#" class="m-all-1">
                    <img src="/images/icons/icon-pay-01.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="/images/icons/icon-pay-02.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="/images/icons/icon-pay-03.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="/images/icons/icon-pay-04.png" alt="ICON-PAY">
                </a>

                <a href="#" class="m-all-1">
                    <img src="/images/icons/icon-pay-05.png" alt="ICON-PAY">
                </a>
            </div>

            <p class="stext-107 cl6 txt-center">
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | Made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a> &amp; distributed by <a href="https://themewagon.com" target="_blank">ThemeWagon</a>
                <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->

            </p>
        </div>
    </div>
</footer>


<!-- Back to top -->
<div class="btn-back-to-top" id="myBtn">
		<span class="symbol-btn-back-to-top">
			<i class="zmdi zmdi-chevron-up"></i>
		</span>
</div>



<!--===============================================================================================-->
<script src="/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/bootstrap/js/popper.js"></script>
<script src="/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/select2/select2.min.js"></script>
<script>
    $(".js-select2").each(function(){
        $(this).select2({
            minimumResultsForSearch: 20,
            dropdownParent: $(this).next('.dropDownSelect2')
        });
    })
</script>
<!--===============================================================================================-->
<script src="/vendor/daterangepicker/moment.min.js"></script>
<script src="/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
<script src="/vendor/slick/slick.min.js"></script>
<script src="/js/slick-custom.js"></script>
<!--===============================================================================================-->
<script src="/vendor/parallax100/parallax100.js"></script>
<script>
    $('.parallax100').parallax100();
</script>
<!--===============================================================================================-->
<script src="/vendor/MagnificPopup/jquery.magnific-popup.min.js"></script>
<script>
    $('.gallery-lb').each(function() { // the containers for all your galleries
        $(this).magnificPopup({
            delegate: 'a', // the selector for gallery item
            type: 'image',
            gallery: {
                enabled:true
            },
            mainClass: 'mfp-fade'
        });
    });
</script>
<!--===============================================================================================-->
<script src="/vendor/isotope/isotope.pkgd.min.js"></script>
<!--===============================================================================================-->
<script src="/vendor/sweetalert/sweetalert.min.js"></script>
<script>
    $('.js-addwish-b2, .js-addwish-detail').on('click', function(e){
        e.preventDefault();
    });



</script>
<!--===============================================================================================-->
<script src="/vendor/perfect-scrollbar/perfect-scrollbar.min.js"></script>
<script>
    $('.js-pscroll').each(function(){
        $(this).css('position','relative');
        $(this).css('overflow','hidden');
        var ps = new PerfectScrollbar(this, {
            wheelSpeed: 1,
            scrollingThreshold: 1000,
            wheelPropagation: false,
        });

        $(window).on('resize', function(){
            ps.update();
        })
    });
</script>
<!--===============================================================================================-->
<script src="/js/main1.js"></script>
<script>
    function product_add(){
        let prod_seq = $("#product_seq").val();

        let data_seq = {"prod_seq" : prod_seq}
        console.log(prod_seq);
        $.ajax({
            data : data_seq,
            url : "/test",
            method :"post",
            success : function (data){
                console.log("성공데이터", data)
                if(data == prod_seq){
                    let cart_append = document.getElementById("cart_append");

                    let new_li = document.createElement("li");
                    new_li.setAttribute("class","header-cart-item flex-w flex-t m-b-12");

                    let new_div = document.createElement("div");
                    new_div.setAttribute("class", "header-cart-item-img");

                    let new_img = document.createElement("img");
                    let test = "<%=fDTO.getFile_detail_path()%>"
                    new_img.src = test;

                    let new_div2 = document.createElement("div");
                    new_div2.setAttribute("class", "header-cart-item-txt p-t-8");

                    let new_a = document.createElement("a");
                    new_a.setAttribute("class","header-cart-item-name m-b-18 hov-cl1 trans-04");
                    let title = "<%=nDTO.getPro_title()%>"
                    new_a.innerText = title;

                    let new_span = document.createElement("span");
                    new_span.setAttribute("class","header-cart-item-info")
                    new_span.innerText=<%=nDTO.getPro_price()%>;


                    cart_append.appendChild(new_li);
                    new_li.appendChild(new_div);
                    new_li.append(new_div2);
                    new_div.append(new_img);
                    new_div2.appendChild(new_a);
                    new_div2.appendChild(new_span);

                }

            }

        })

    }

</script>


</body>
</html>