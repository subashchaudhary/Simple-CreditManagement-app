package com.subashtech.creditmanagement;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/TransferCredit")
public class TransferCredit extends HttpServlet {
	 
	String from;
	String to;
	int transfer_amount;
	PrintWriter pw;
	RequestDispatcher dispatch;
	String serverMsg;
	String names[];
	String usernames[];
	  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		pw = response.getWriter();
		int count =0;
		MyConnection conn = new MyConnection();
		names = conn.getUsernames();
		for(String name : names) {
			if(name == null) {
				break;
			}
			count++;
		}
		String[] usernames = new String[count];
		int i=0;
		for(String name: names) {
			if(name == null)break;
			usernames[i] = name;
			i++;
		}
		for(String uname: usernames) {
			 
			System.out.println(uname);
			 
		}
		
		pw.print("<html><head><title>Credit Management</title>\r\n" + 
				"<style>\r\n" + 
				"	*{margin:0;padding:0}\r\n" + 
				"	body{width:100%;text-align:center;}\r\n" + 
				"	h1 {\r\n" + 
				"    padding: 15px;\r\n" + 
				"    font-size: 35px;\r\n" + 
				"    font-family: monospace;\r\n" + 
				"	}\r\n" + 
				"	.header {\r\n" + 
				"    height: 80px;\r\n" + 
				"    background: #63cc7e;\r\n" + 
				"    color: white;\r\n" + 
				"    text-align: center;}\r\n"+
				"	form {\r\n" + 
				"    font-size: 20px;}" + 
				"	input[type='number'] {padding: 5px;}"+
				"	select {padding:5px;}"+
				"	#transfer{background:#63cc7e; padding:5px; color:white;}"+
				"</style></head><body><div class='header'><h1> Credit Management </h1></div>\r\n" + 
				"	</br></br>");
		pw.print("<form action='TransferCredit' method='post'> From : <select name='from'>");
		for(String uname: usernames) {
			pw.print("<option value="+uname+">"+uname+"</option>");
		}
		pw.print("</select>");
		pw.print(" TO : <select name='to'>");
		for(String uname: usernames) {
			pw.print("<option value="+uname+">"+uname+"</option>");
		}
		pw.print("</select>");
		pw.print(" Amount : <input type='number' min='1' name='amount' required />");
		pw.print("<input type='submit' id='transfer' value='Transfer'>");
		pw.print("</form></body></html>");
		pw.flush();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		pw = response.getWriter();
		from = request.getParameter("from");
		to = request.getParameter("to");
		transfer_amount = Integer.parseInt(request.getParameter("amount"));
		System.out.println(from);
		System.out.println(to);
		System.out.println(transfer_amount);
		
		MyConnection conn = new MyConnection();
		
		if(from.equals(to)) {
			System.out.println("Cannot transfer to your own account");
			pw.print("<html><head><title>Credit Management</title>\r\n" + 
					"<style>\r\n" + 
					"	*{margin:0;padding:0}\r\n" + 
					"	body{width:100%;text-align:center;}\r\n" + 
					"	h1 {\r\n" + 
					"    padding: 15px;\r\n" + 
					"    font-size: 35px;\r\n" + 
					"    font-family: monospace;\r\n" + 
					"	}\r\n" + 
					"	.header {\r\n" + 
					"    height: 80px;\r\n" + 
					"    background: #63cc7e;\r\n" + 
					"    color: white;\r\n" + 
					"    text-align: center;}\r\n"+
					"	form {\r\n" + 
					"    font-size: 20px;}" + 
					"	input[type='number'] {padding: 5px;}"+
					"	select {padding:5px;}"+
					"	#transfer{background:#63cc7e; padding:5px; color:white;}"+
					"</style></head><body><div class='header'><h1> Credit Management </h1></div>\r\n" + 
					"	</br></br>");
			pw.print("<h2 style='color:red;text-align:center;margin-top:100px;'>Being Smart!!! Cannot Transfer to your Account.</h2></body></html>");
			pw.flush();
		}
		else {
			boolean res = conn.transferCredit(from, to, transfer_amount);
			if(res) {
				System.out.println("Transfer success");
				pw.print("<html><head><title>Credit Management</title>\r\n" + 
						"<style>\r\n" + 
						"	*{margin:0;padding:0}\r\n" + 
						"	body{width:100%;text-align:center;}\r\n" + 
						"	h1 {\r\n" + 
						"    padding: 15px;\r\n" + 
						"    font-size: 35px;\r\n" + 
						"    font-family: monospace;\r\n" + 
						"	}\r\n" + 
						"	.header {\r\n" + 
						"    height: 80px;\r\n" + 
						"    background: #63cc7e;\r\n" + 
						"    color: white;\r\n" + 
						"    text-align: center;}\r\n"+
						"	form {\r\n" + 
						"    font-size: 20px;}" + 
						"	input[type='number'] {padding: 5px;}"+
						"	select {padding:5px;}"+
						"	#transfer{background:#63cc7e; padding:5px; color:white;}"+
						"</style></head><body><div class='header'><h1> Credit Management </h1></div>\r\n" + 
						"	</br></br>");
				pw.print("<h2 style='color:green;text-align:center;margin-top:100px;'> Transfer Success of Amount: "+ transfer_amount+ "</h2></body></html>");
				pw.flush();
			}
			else {
				System.out.println("Transfer failed");
				pw.print("<html><head><title>Credit Management</title>\r\n" + 
						"<style>\r\n" + 
						"	*{margin:0;padding:0}\r\n" + 
						"	body{width:100%;text-align:center;}\r\n" + 
						"	h1 {\r\n" + 
						"    padding: 15px;\r\n" + 
						"    font-size: 35px;\r\n" + 
						"    font-family: monospace;\r\n" + 
						"	}\r\n" + 
						"	.header {\r\n" + 
						"    height: 80px;\r\n" + 
						"    background: #63cc7e;\r\n" + 
						"    color: white;\r\n" + 
						"    text-align: center;}\r\n"+
						"	form {\r\n" + 
						"    font-size: 20px;}" + 
						"	input[type='number'] {padding: 5px;}"+
						"	select {padding:5px;}"+
						"	#transfer{background:#63cc7e; padding:5px; color:white;}"+
						"</style></head><body><div class='header'><h1> Credit Management </h1></div>\r\n" + 
						"	</br></br>");
				pw.print("<h2 style='color:red;text-align:center;margin-top:100px;'>Opps!! Transfer Failed. </h2></br><p>May you don't have Enough Balance. </p><br></body></html>");
				pw.flush();
			}
		}
//		MyConnection conn = new MyConnection();
//		String names[] = conn.getUsernames();
//		for(String name : names) { 
// 			if(name == null) {
// 				break;
//			}
//			else if(!(from.equals(name))) {
//				serverMsg = "Not valid User";
//				request.setAttribute("servermsg", serverMsg);
//				dispatch = request.getRequestDispatcher("transfer.jsp");	
//				dispatch.forward(request, response);
//				break;
//			}
//			else if(!(to.equals(name)))
//			{
//				serverMsg = "Not valid User";
//				request.setAttribute("servermsg", serverMsg);
//				dispatch = request.getRequestDispatcher("transfer.jsp");	
//				dispatch.forward(request, response);
//
//			}
//			else {
////				boolean res = conn.transferCredit(from, to, transfer_amount);
////				if(res) {
// 					pw.print("Transfer Sucess");
////				}
//			}
		}

		
//		pw.flush();
	
//	}

}
