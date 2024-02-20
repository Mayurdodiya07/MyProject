<%@page import="com.dao.ProductDao"%>
<%@page import="com.bean.Product"%>
<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%
	int pid=Integer.parseInt(request.getParameter("pid"));
    int uid=Integer.parseInt(request.getParameter("uid"));
    Product p=ProductDao.getProduct(pid);
     cart c=new cart();
     c.setPid(pid);
     c.setUid(uid);
     c.setProduct_qty(1);
     c.setProduct_price(p.getProduct_price());
     c.setTotal_price(p.getProduct_price());
     CartDao.addToCart(c);
     List<cart> list=CartDao.getcartByUser(uid);
     session.setAttribute("cart_count",list.size());
     response.sendRedirect("Cart.jsp");
  %>