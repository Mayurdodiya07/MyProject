package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.bean.Product;
import com.util.UserUtil;

public class ProductDao {

	public static void addProduct(Product p)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="insert into product(uid,product_name,product_price,product_desc,product_image)values(?,?,?,?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,p.getUid());
            pst.setString(2, p.getProduct_name());
            pst.setInt(3,p.getProduct_price());
            pst.setString(4, p.getProduct_desc());
            pst.setString(5, p.getProduct_image());
            pst.executeUpdate();
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	public static void updateProduct(Product p)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="update product set product_name=?,product_price=?,product_desc=? where pid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(4,p.getPid());
            pst.setString(1, p.getProduct_name());
            pst.setInt(2,p.getProduct_price());
            pst.setString(3, p.getProduct_desc());
           
            pst.executeUpdate();
            
			
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	public static void deleteProduct(int pid)
	{
		try {
			Connection conn=UserUtil.createConnection();
			String sql="delete from product where pid=?";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,pid);  
            pst.executeUpdate();
           	
		} catch (Exception e) {
			e.printStackTrace();
		}  
	}
	public static List<Product>getProductBySelller(int uid)
	{
		List<Product>list=new  ArrayList<Product>();
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select* from product where uid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, uid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
			Product p=new Product();
			p.setPid(rs.getInt("pid"));
			p.setUid(rs.getInt("uid"));
			p.setProduct_price(rs.getInt("product_price"));
			p.setProduct_name(rs.getString("product_name"));
			p.setProduct_desc(rs.getString("product_desc"));
	        p.setProduct_image(rs.getString("product_image"));
	        list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public static List<Product> getAllProduct()
	{
		List<Product>list=new  ArrayList<Product>();
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select*from product";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
			Product p=new Product();
			p.setPid(rs.getInt("pid"));
			p.setUid(rs.getInt("uid"));
			p.setProduct_price(rs.getInt("product_price"));
			p.setProduct_name(rs.getString("product_name"));
			p.setProduct_desc(rs.getString("product_desc"));
	        p.setProduct_image(rs.getString("product_image"));
	        list.add(p);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public static  Product getProduct(int pid)
	{
      Product p=null;
		
		
		try {
			Connection conn=UserUtil.createConnection();
			String sql="select* from product where pid=?";
			PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pid);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
			p=new Product();
			p.setPid(rs.getInt("pid"));
			p.setUid(rs.getInt("uid"));
			p.setProduct_price(rs.getInt("product_price"));
			p.setProduct_name(rs.getString("product_name"));
			p.setProduct_desc(rs.getString("product_desc"));
	        p.setProduct_image(rs.getString("product_image"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return p;
		
	}
}
