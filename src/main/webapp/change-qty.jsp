<%@page import="com.dao.CartDao"%>
<%@page import="com.bean.cart"%>
 <%
  int product_qty=Integer.parseInt(request.getParameter("product_qty"));
  int cid=Integer.parseInt(request.getParameter("cid"));
  cart c=CartDao.getcart(cid);
  int total_price=c.getTotal_price(); 
  int product_price=c.getProduct_price();
  total_price=product_price*product_qty;
  c.setTotal_price(total_price);
  c.setProduct_qty(product_qty);
  CartDao.changeQuantity(c);
  response.sendRedirect("Cart.jsp"); 
  %>
 
 
 
 
 
 
 