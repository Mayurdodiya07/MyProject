<%@page import="com.dao.CartDao"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

   <%@ include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <html lang="en">
 
 <head>

  </head>

  <body>
    <!--================Home Banner Area =================-->
    <section class="banner_area">
      <div class="banner_inner d-flex align-items-center">
        <div class="container">
          <div
            class="banner_content d-md-flex justify-content-between align-items-center"
          >
            <div class="mb-3 mb-md-0">
              <h2>Product Details</h2>
              
            </div>
            <div class="page_link">
              <a href="index.jsp">Home</a>
              <a href="seller-product-details.jsp">Product Details</a>
            </div>
          </div>
        </div>
      </div>
    </section>
    <!--================End Home Banner Area =================-->
		<%
		int pid=Integer.parseInt(request.getParameter("pid"));
		Product p=ProductDao.getProduct(pid);
		
		%>
 <!--================Single Product Area =================-->
    <div class="product_image_area">
      <div class="container">
        <div class="row s_product_inner">
          <div class="col-lg-5">
            <div class="s_product_img">
              <div
                id="carouselExampleIndicators"
                class="carousel slide"
                data-ride="carousel" >
                
                <div class="carousel-inner">
                  <div class="carousel-item active">
                    <img
                      class="d-block w-10"
                      src="product_image/<%=p.getProduct_image()%>"
                      alt="First slide"/>
                  </div>
                 
                </div>
              </div>
            </div>
          </div>
          <div class="col-lg-5 offset-lg-1">
            <div class="s_product_text">
              <h3><%=p.getProduct_name() %></h3>
              <h2><%=p.getProduct_price() %></h2>
              
              <p>
                <%=p.getProduct_desc() %>
              </p>
              <p>
              <%
              if(u!=null)
              { 
              %>
               <%
                boolean flag1=CartDao.checkCart(p.getPid(),u.getId());
                if(flag1==false)
               {
              %>
              <a href="add-to-cart.jsp?pid=<%=p.getPid()%>&uid=<%=u.getId()%>"><input type="button" value="Add To cart" class="btn btn-success"></a>
              <%
               }
                else
                {
              %>
              <a href="remove-from-cart.jsp?pid=<%=p.getPid()%>&uid=<%=u.getId()%>"><input type="button" value="Remove from cart" class="btn btn-danger"></a>
              <%
                }
               %>
              
            <%--  <a href="seller-product-edit.jsp?pid=<%=p.getPid()%>"><input type="button" value="Add To Cart" class="btn btn-success"></a> --%>
             
             <%
                boolean flag=WishlistDao.checkWishlist(p.getPid(),u.getId());
                if(flag==false)
               {
              %>
              
              
              <a href="add-to-wishlist.jsp?pid=<%=p.getPid()%>&uid=<%=u.getId()%>"><input type="button" value="Add To Wishlist" class="btn btn-success"></a>
              <%
               }
                else
                {
              %>
              <a href="remove-from-wishlist.jsp?pid=<%=p.getPid()%>&uid=<%=u.getId()%>"><input type="button" value="Remove from Wishlist" class="btn btn-danger"></a>
              <%
                }
               %>
              
              <%
              }
              else 
              {
              %>
              <a href="login.jsp"><input type="button" value="login" class="btn btn-success"></a>
               <%
              }
               %>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!--================End Single Product Area =================-->

    <!--================ start footer Area  =================-->
    <footer class="footer-area section_gap">
      <div class="container">
        <div class="row">
          <div class="col-lg-2 col-md-6 single-footer-widget">
            <h4>Top Products</h4>
            <ul>
              <li><a href="#">Managed Website</a></li>
              <li><a href="#">Manage Reputation</a></li>
              <li><a href="#">Power Tools</a></li>
              <li><a href="#">Marketing Service</a></li>
            </ul>
          </div>
          <div class="col-lg-2 col-md-6 single-footer-widget">
            <h4>Quick Links</h4>
            <ul>
              <li><a href="#">Jobs</a></li>
              <li><a href="#">Brand Assets</a></li>
              <li><a href="#">Investor Relations</a></li>
              <li><a href="#">Terms of Service</a></li>
            </ul>
          </div>
          <div class="col-lg-2 col-md-6 single-footer-widget">
            <h4>Features</h4>
            <ul>
              <li><a href="#">Jobs</a></li>
              <li><a href="#">Brand Assets</a></li>
              <li><a href="#">Investor Relations</a></li>
              <li><a href="#">Terms of Service</a></li>
            </ul>
          </div>
          <div class="col-lg-2 col-md-6 single-footer-widget">
            <h4>Resources</h4>
            <ul>
              <li><a href="#">Guides</a></li>
              <li><a href="#">Research</a></li>
              <li><a href="#">Experts</a></li>
              <li><a href="#">Agencies</a></li>
            </ul>
          </div>
          <div class="col-lg-4 col-md-6 single-footer-widget">
            <h4>Newsletter</h4>
            <p>You can trust us. we only send promo offers,</p>
            <div class="form-wrap" id="mc_embed_signup">
              <form target="_blank" action="https://spondonit.us12.list-manage.com/subscribe/post?u=1462626880ade1ac87bd9c93a&amp;id=92a4423d01"
                method="get" class="form-inline">
                <input class="form-control" name="EMAIL" placeholder="Your Email Address" onfocus="this.placeholder = ''"
                  onblur="this.placeholder = 'Your Email Address '" required="" type="email">
                <button class="click-btn btn btn-default">Subscribe</button>
                <div style="position: absolute; left: -5000px;">
                  <input name="b_36c4fd991d266f23781ded980_aefe40901a" tabindex="-1" value="" type="text">
                </div>
  
                <div class="info"></div>
              </form>
            </div>
          </div>
        </div>
        <div class="footer-bottom row align-items-center">
          <p class="footer-text m-0 col-lg-8 col-md-12"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
          <div class="col-lg-4 col-md-12 footer-social">
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-dribbble"></i></a>
            <a href="#"><i class="fa fa-behance"></i></a>
          </div>
        </div>
      </div>
    </footer>
    <!--================ End footer Area  =================-->

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/popper.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/stellar.js"></script>
    <script src="vendors/lightbox/simpleLightbox.min.js"></script>
    <script src="vendors/nice-select/js/jquery.nice-select.min.js"></script>
    <script src="vendors/isotope/imagesloaded.pkgd.min.js"></script>
    <script src="vendors/isotope/isotope-min.js"></script>
    <script src="vendors/owl-carousel/owl.carousel.min.js"></script>
    <script src="js/jquery.ajaxchimp.min.js"></script>
    <script src="js/mail-script.js"></script>
    <script src="vendors/jquery-ui/jquery-ui.js"></script>
    <script src="vendors/counter-up/jquery.waypoints.min.js"></script>
    <script src="vendors/counter-up/jquery.counterup.js"></script>
    <script src="js/theme.js"></script>
  </body>
</html>
