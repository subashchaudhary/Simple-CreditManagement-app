 package com.subashtech.creditmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

//JDBC Connection
public class MyConnection {
 
	User user;
	ArrayList<User> ulist;
	Connection con;
	Statement stmt;
	PreparedStatement pstmt;
	ResultSet result;
	int res;
	public MyConnection() {
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:Mysql://localhost:3306/creditmanagement","root","subash");  
			 
			if(con != null) {
				System.out.println("Success");
			} 

		}
		catch(Exception e)
		{
			System.out.println("Connection error");
			System.out.println(e); 
			 
		}
		 
	}
	
	
//users lists
	
	public ArrayList<User> getUsers() {
		String sql = "SELECT * FROM users";
		User user;

		try {
			pstmt =(PreparedStatement)con.prepareStatement(sql); 
 			result = pstmt.executeQuery();
			 
			ulist = new ArrayList<User>();
			while(result.next()) {
				
				String id = result.getString(1);
				String name = result.getString(2);
				String email = result.getString(3);
				String amount = result.getString(4);
				user = new User(id,name,email,amount);
				System.out.println(ulist);
				ulist.add(user);
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("in exception");
		}
		
		return ulist;
	}
	
	//Transaction function
	public ArrayList<Transfer> getTransfer(){
		
		String sql = "SELECT * FROM transfer";
		Transfer transfer;
		ArrayList<Transfer> tlist= new ArrayList<Transfer>();

		try {
			pstmt =(PreparedStatement)con.prepareStatement(sql); 
 			result = pstmt.executeQuery();
			 
			while(result.next()) {
				
				int id = result.getInt(1);
				String from = result.getString(2);
				String to = result.getString(3);
				int amount = result.getInt(4);
				boolean status = result.getBoolean(5);
				transfer = new Transfer(id,from,to,amount,status);
				System.out.println("transfer lists");
				System.out.println(transfer);
				tlist.add(transfer);
				
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("in exception");
		}
		 
		return tlist;
		
	}
	
	//get users names from database
	
	public String[] getUsernames() {
		String names[] = new String[10];
		String sql = "Select name from users";
		try {
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			result = pstmt.executeQuery();
			 
			int i=0;
			while(result.next()) {
				names[i] = result.getString(1);
				i++;
			}
			for(String name : names) {
				System.out.println(name);
			}
		}catch(Exception e) {
			System.out.println("Error while fetching names from database");
		}
		return names;
	}
	public int getCurrentCredit(String name) {
		int currentamount=0;
		String sql = "select current_credit from users where name = ?";
		try {
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			pstmt.setString(1, name);
			result = pstmt.executeQuery();
//			System.out.println(result);
			if(result.next()) {
				currentamount = result.getInt(1);
			}
		}catch(Exception e) {
			System.out.println("Error during getting current credit amount");
		}
		return currentamount;
	}
	public int subtractCredit(String name, int transfer_amount) {
		MyConnection conn = new MyConnection();
		
		//get the current credit from database
		int current_amount = conn.getCurrentCredit(name);
		
		//subtract the transfer amount from total amount
		int remaining_amount = current_amount - transfer_amount;
		String sql = "update users set current_credit =? where name= ?";
 		try {
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			pstmt.setInt(1, remaining_amount);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();
//			System.out.println(result);
			if(result == 1) {
				System.out.println("Credit updated");
			}
		}catch(Exception e) {
			System.out.println("Error during updating current credit amount");
		}
		
		
		return remaining_amount;
	}
	public boolean updateCredit(String name,int transfer_amount) {
		MyConnection conn = new MyConnection();
		
		//get the current credit from database
		int current_amount = conn.getCurrentCredit(name);
		 
		int total = current_amount + transfer_amount;
		String sql = "update users set current_credit = ? where name = ?";
		try {
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			pstmt.setInt(1, total);
			pstmt.setString(2, name);
			int result = pstmt.executeUpdate();
//			System.out.println(result);
			if(result == 1) {
				System.out.println("Credit updated");
				return true;
			}
		}catch(Exception e) {
			System.out.println("Error during updating current credit amount");
		}
		
		return false;
		
		
	}
	//transfer credit
	public boolean transferCredit(String from, String to, int transfer_amount) {
		MyConnection conn = new MyConnection();

		int current_amount_from = conn.getCurrentCredit(from);
		if(current_amount_from < transfer_amount) return false;
		String sql = "insert into transfer(transfer_from, transfer_to, transfer_amount, transfer_progress) values(?,?,?,?)";
		try {
			pstmt = (PreparedStatement)con.prepareStatement(sql);
			pstmt.setString(1, from);
			pstmt.setString(2, to);
			pstmt.setInt(3, transfer_amount);
			boolean status = conn.updateCredit(to, transfer_amount);
			//if the amount is not updated;
			if(status == false) {
				return false;
			}
			pstmt.setBoolean(4, status);
			if(status == true) {
				res = pstmt.executeUpdate();
				if(res == 1) {
					System.out.println("transfer sucess");
	 				conn.subtractCredit(from, transfer_amount);
	 				return true;
				}
			}
			
		}catch(Exception e) {
			System.out.println("Error while treansfering credit");
		}
		return false;
	}
		
	
	//Testing
	public static void main(String arg[]) {
		MyConnection con = new MyConnection();
//		int amount = con.getCurrentCredit("subash");
//		System.out.println("current amount:"+amount);
		
//		con.updateCredit("subash", 5000, 500);
		
//		con.transferCredit("subash", "rohan", 200);
	
//		boolean res = con.transferCredit("subash", "rohan", 100);
//		if(res) {
//			System.out.println("successfully transfer ");
//		}
		
		con.getUsernames();
 	}
}
