package com.subashtech.creditmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
 import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

 

public class CreditManagementImp implements CreditManagement{
	
	ArrayList<User> ulist = new ArrayList<User>();
	Connection con;
 	
	ResultSet result;
	PreparedStatement pstmt;
	Statement stmt;
	
	 
	public ArrayList<User> userList() {
		MyConnection obj = new MyConnection();
		obj.getUsers();
		 
		
		return ulist;
	}
	
	//user details
	
	public String UserDetails(String name, String email) {
		String sql = "select * from user where email = ?";
		try {
			pstmt = (PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, email);
 			int result = pstmt.executeUpdate();
 			if(result >0)
			{
				System.out.println("result access");
			}
			else{
				System.out.println("some errors");
			}

		} catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return toString();
	}
	
	//credit transfer
	
	public int creditTransfer(String fromUser, String toUser, int amount) {
		String sql = "insert into transfer values(fromUser, toUser, amount)";
 		 
		return amount;
	}

}
