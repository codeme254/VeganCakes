package com.vegancakes.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import com.vegancakes.*;

public class ProductDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;
	public ProductDao(Connection con) {
		super();
		this.con = con;
	}
	
	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();
		
		try {
			query = "select * from Products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setCake_image(rs.getString("cake_image"));
				row.setCake_name(rs.getString("cake_name"));
				row.setCake_description(rs.getString("cake_description"));
				row.setCake_category(rs.getString("cake_category"));
				row.setCake_quantity(rs.getInt("cake_quantity"));
				row.setCake_price(rs.getInt("cake_price"));
				products.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return products;
	}
	
	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> products = new ArrayList<Cart>();
		try {
			if (cartList.size() > 0) {
				for (Cart item: cartList) {
					query = "select * from Products where id = ?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setCake_image(rs.getString("cake_image"));
						row.setCake_name(rs.getString("cake_name"));
						row.setCake_description(rs.getString("cake_description"));
						row.setCake_category(rs.getString("cake_category"));
						row.setCake_quantity(rs.getInt("cake_quantity"));
						row.setCake_price(rs.getInt("cake_price")*item.getQuntity());
						row.setQuntity(item.getQuntity());
						products.add(row);
					}
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return products;
	}
	
	public int getTotalCartPrice(ArrayList<Cart> cartList) {
		int sum = 0;
		try {
			if(cartList.size() > 0) {
				for (Cart item: cartList) {
					query = "select cake_price from Products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					
					while (rs.next()) {
						sum += rs.getInt("cake_price") * item.getQuntity();
					}
				}
			}
			
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return sum;
	}
}




