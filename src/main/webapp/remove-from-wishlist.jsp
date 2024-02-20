<%@page import="java.util.List"%>
<%@page import="com.dao.WishlistDao"%>
<%@page import="com.bean.Wishlist"%>
<%
	int pid=Integer.parseInt(request.getParameter("pid"));
    int uid=Integer.parseInt(request.getParameter("uid"));
     Wishlist w=new Wishlist();
     w.setPid(pid);
     w.setUid(uid);
     WishlistDao.removeFromWishlist(w);
     List<Wishlist> list=WishlistDao.getWishlistByUser(uid);
     session.setAttribute("wishlist_count",list.size());
     response.sendRedirect("wishlist.jsp");
  %>