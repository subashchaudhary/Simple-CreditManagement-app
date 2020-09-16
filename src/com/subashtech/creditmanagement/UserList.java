package com.subashtech.creditmanagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebServlet("/UserList")
public class UserList extends HttpServlet {
       
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter pw = response.getWriter();
		//database connection
  		MyConnection con = new MyConnection();
  		ArrayList<User> ulist = con.getUsers();
  		System.out.println(ulist);
  		pw.print("<html><head><title>Credit Management</title>"
  				+ "<style>	*{margin:0;padding:0}\r\n" + 
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
  				"	} table{width:60%;margin:0 auto;text-align:center; font-size:25px;font-family:monospace;}"
  				+ "table th{background:#63cc7e;color:white;}</style></head>");
  		pw.print("<body><div class='header'><h1> Credit Management </h1></div>");
  		pw.print("<h1> All User list </h1><table border='1'>");
  		pw.print("<tr><th>id</th> <th> name </th> <th> email </th> <th> current_credit</th><tr>");
        for(User user: ulist) {
        	pw.print("<tr>");
            pw.println("<td>"+user.getId()+"</td>");
            pw.println("<td>"+user.getName()+"</td>");
            pw.println("<td>"+user.getEmail()+"</td>");
            pw.println("<td>"+user.getAmount()+"</td></tr");


        }
		 
		pw.print("</table></body></html>");
		 System.out.println("response form server: ");
 		
		 
		 
	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		doGet(request, response);
	}

}
