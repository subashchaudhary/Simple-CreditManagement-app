package com.subashtech.creditmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/TransferList")
public class TransferList extends HttpServlet {
	
	PrintWriter pw;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MyConnection con = new MyConnection();
  		ArrayList<Transfer> tlist = con.getTransfer();
  		System.out.println(tlist);
		pw = response.getWriter();
		pw.print("<html><head><title>Credit Management</title>"
  				+ "<style>*{margin:0;padding:0}\r\n" + 
  				"	body{width:100%;text-align:center;}\r\n" + 
  				"	h1 {\r\n" + 
  				"    padding: 15px;\r\n" + 
  				"    font-size: 35px;\r\n" + 
  				"    /* font-weight: 900; */\r\n" + 
  				"    font-family: monospace;\r\n" + 
  				"	}\r\n" + 
  				"	.header {\r\n" + 
  				"    height: 80px;\r\n" + 
  				"    background: #63cc7e;\r\n" + 
  				"    color: white;\r\n" + 
  				"    text-align: center;\r\n" + 
  				"	} table{width:60%;margin:0 auto;text-align:center;font-faily:monospace;font-size:25px;}"
  				+ "table th{background:#63cc7e; color:white;}</style></head>");
  		pw.print("<body><div class='header'><h1> Credit Management </h1></div><h1> All Transfer Details </h1><table border='1'>");
  		pw.print("<tr><th>id</th> <th> Transfer from </th> <th> Transfer to </th> <th> Transfer Amount</th><th> status</th><tr>");
        for(Transfer transfer: tlist) {
        	pw.print("<tr>");
            pw.println("<td>"+transfer.getId()+"</td>");
            pw.println("<td>"+transfer.getFrom()+"</td>");
            pw.println("<td>"+transfer.getTo()+"</td>");
            pw.println("<td>"+transfer.getAmount()+"</td>");
            pw.println("<td>"+transfer.isStatus()+"</td></tr");


        }
		 
		pw.print("</table></body></html>");
		pw.flush();
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	}

}
