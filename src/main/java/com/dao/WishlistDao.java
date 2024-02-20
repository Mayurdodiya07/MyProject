package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import com.bean.Wishlist;

import com.util.UserUtil;

public class WishlistDao {
     
	
	 public static void addToWishlist(Wishlist w)
	 {
		try {
			Connection conn=UserUtil.createConnection();
			String sql="insert into wishlist(pid,uid)values(?,?)";
		    PreparedStatement pst=conn.prepareStatement(sql);
		    pst.setInt(1,w.getPid());
		    pst.setInt(2,w.getUid());
		    pst.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 }
	 public static void removeFromWishlist(Wishlist w)
	 {
		try {
			Connection conn=UserUtil.createConnection();
			String sql="delete from wishlist where pid=? and uid=?";
		    PreparedStatement pst=conn.prepareStatement(sql);
		    pst.setInt(1,w.getPid());
		    pst.setInt(2,w.getUid());
		    pst.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} 
	 }
	 
	 public static boolean checkWishlist(int pid,int uid)
	 {
		 boolean flag=false;
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select * from wishlist where pid=? and uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			pst.setInt(2, uid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				flag=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return flag;
	 }
	 public static List<Wishlist> getWishlistByUser(int uid) 
	 {
		 List<Wishlist> list=new ArrayList<Wishlist>();
		 try {
			 Connection conn=UserUtil.createConnection();
			 String sql="select* from wishlist where uid=?";
			 PreparedStatement pst=conn.prepareStatement(sql);
			 pst.setInt(1, uid);
	          ResultSet  rs=pst.executeQuery();
	          while(rs.next())
	          {
	        	 Wishlist w=new Wishlist();
	        	 w.setWid(rs.getInt("wid"));
	        	 w.setPid(rs.getInt("pid"));
	        	 w.setUid(rs.getInt("uid"));
	        	 list.add(w);
	          }
		} catch (Exception e) {
		}
		 
		 return list;
	 }
	
	 
}
