package com.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.bean.User;
import com.bean.Wishlist;
import com.bean.cart;
import com.dao.CartDao;
import com.dao.UserDao;
import com.dao.WishlistDao;
import com.service.Services;


public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          String action=request.getParameter("action");
          
          if(action.equalsIgnoreCase("signup"))
          {
        	  boolean flag=UserDao.checkEmail(request.getParameter("email"));
        	  if(flag==false)
        	  {
        		  if(request.getParameter("password").equals(request.getParameter("cpassword")))
        		  {
        			User u=new User();
        			u.setUsertype(request.getParameter("usertype"));
        			u.setFname(request.getParameter("fname"));
        			u.setLname(request.getParameter("fname"));
        			u.setEmail(request.getParameter("email"));
        			u.setMobile(Long.parseLong(request.getParameter("mobile")));
        			u.setAddress(request.getParameter("address"));
        			u.setPassword(request.getParameter("password"));
        			UserDao.signupuser(u);
        			request.setAttribute("msg","User Sign Up successfully");
        			request.getRequestDispatcher("login.jsp").forward(request, response);
        	  }
        		  else
        		  {
        			  request.setAttribute("msg","Password & Confirm Password Does Not Matched");
          			 request.getRequestDispatcher("signup.jsp").forward(request, response);
        		  }
          }
        	  else  
        	  {
        		  request.setAttribute("msg","Email Already Registered");
      			request.getRequestDispatcher("login.jsp").forward(request, response);
        	  }
   
	}
          else if(action.equalsIgnoreCase("login"))
          {
        	  User u=UserDao.loginUser(request.getParameter("email"));
        	  if(u!=null)
        	  {
        		  if(u.getPassword().equals(request .getParameter("password")))
        		  {
        			HttpSession session=request.getSession();
        			 session.setAttribute("u",u);
        			 if(u.getUsertype().equals("buyer"))
        			 {
        				 List<Wishlist> list=WishlistDao.getWishlistByUser(u.getId());
        				 session.setAttribute("wishlist_count",list.size());
        				 List<cart> list1=CartDao.getcartByUser(u.getId());
        				 session.setAttribute("cart_count",list1.size());
        			 request.getRequestDispatcher("index.jsp").forward(request, response);
        			 }
        			 else
        			 {
        				 request.getRequestDispatcher("seller-index.jsp").forward(request, response);
        			 }
        		  }
        		  else
        		  {
        			  request.setAttribute("msg","Incorrect Password");
        			  request.getRequestDispatcher("login.jsp").forward(request, response);
        		  }
        	  }
        	  else 
        	  {

    			  request.setAttribute("msg","Email Not Registered");
    			  request.getRequestDispatcher("login.jsp").forward(request, response);
        	  }
          }
          else if(action.equalsIgnoreCase("change password"))
          {
        	  HttpSession session=request.getSession();
        	  User u=(User)session.getAttribute("u");
        	  if(u.getPassword().equals(request.getParameter("old_password")))
        	  {
        		  if(request.getParameter("new_password").equals(request.getParameter("cnew_password")))
        		  {
        			  UserDao.changePassword(u.getEmail(),request.getParameter("new_password"));
        			  response.sendRedirect("logout.jsp");
        		  }
        		  else
        		  {
        			  request.setAttribute("msg","New password & confirm new password Does Not Matched");
        			 if(u.getUsertype().equals("buyer"))
        			  {
        			  request.getRequestDispatcher("change-password.jsp").forward(request, response);
        			  }
        			 else 
        			  {
        			  request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
        			  }
        		  }
        			  
        	  }
        	  else
        	  {
        		  request.setAttribute("msg","Old password Does Not Matched");
        		  if(u.getUsertype().equals("buyer"))
    			  {
    			  request.getRequestDispatcher("change-password.jsp").forward(request, response);
    			  }
    			 else 
    			  {
    			  request.getRequestDispatcher("seller-change-password.jsp").forward(request, response);
    			  }
        	  }
          }
          else if(action.equalsIgnoreCase("send otp"))
  		{
  			
  			String email= request.getParameter("email");
  			boolean flag=UserDao.checkEmail(email);
  			if(flag==true)
  			{
  				Random t= new Random();
  		    	int minRange = 1000, maxRange= 9999;
  	        	int otp = t.nextInt(maxRange - minRange) + minRange;
  				Services.sendMail(email, otp);
  				request.setAttribute("email", email);
  				request.setAttribute("otp", otp);
  				request.getRequestDispatcher("otp.jsp").forward(request, response);
  			}
  			else
  			{
  				request.setAttribute("msg","Email Not Registered");
  				request.getRequestDispatcher("forgot-password.jsp").forward(request, response);
  			}
  		}
          else if(action.equalsIgnoreCase("Verify OTP"))
          {
        	  int otp=Integer.parseInt(request.getParameter("otp"));
        	  int uotp=Integer.parseInt(request.getParameter("uotp"));
        	  String email=request.getParameter("email");
        	  
        	  if(otp==uotp)
        	  {
        		request.setAttribute("email", email);
        		request.getRequestDispatcher("new-password.jsp").forward(request, response);
        	  }
        	  else
        	  {
        		request.setAttribute("msg","Invalid otp");
    		    request.setAttribute("email", email);
				request.setAttribute("otp", otp);
				request.getRequestDispatcher("otp.jsp").forward(request, response);
				  
        	  }
        	  
          }
          else if(action.equalsIgnoreCase("Update password"))
          {
        	  String email=request.getParameter("email");
        	  String np=request.getParameter("new_password");
        	  String cnp=request.getParameter("cnew_password");
        	  
        	  if(np.equals(cnp))
        	  {
        		 UserDao.changePassword(email,np);
        		  response.sendRedirect("login.jsp");
        	  }
        	  else
        	  {
    		  request.setAttribute("msg","New Password & Confirm New password does Not Matched ");
    		  request.setAttribute("email", email);
      		  request.getRequestDispatcher("new-password.jsp").forward(request,response);
    	  }
          }
	}
	
	}
