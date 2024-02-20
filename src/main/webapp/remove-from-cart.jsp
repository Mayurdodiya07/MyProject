<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.cart"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%
	int pid=Integer.parseInt(request.getParameter("pid"));
    int uid=Integer.parseInt(request.getParameter("uid"));
     cart c=new cart();
     c.setPid(pid);
     c.setUid(uid);
     CartDao.removeFromCart(c);
     List<cart> list=CartDao.getcartByUser(uid);
     session.setAttribute("cart_count",list.size());
     response.sendRedirect("Cart.jsp");
  %>