<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Credit Management</title>
<style>
	*{margin:0;padding:0}
	body{width:100%;text-align:center;}
	table{width:60%;margin:0 auto;text-align:center;}
  	table th{background:silver;}
  	.header{height:80px; background:silver;text-align:center;}
</style>
</head>
<body>
<% String msg = (String)request.getAttribute("servermsg"); %>

  	<div class='header'><h1> Credit Management </h1></div>
	</br></br>
	
	<form action="TransferCredit" method="post">
		From : <input type="text" name="from" required/>
		TO :   <input type="text" name="to" required/>
		Amount: <input type="number" min="1" name="amount" required/>
		<input type="submit" value="Transfer">
	
	</form>
	
	</br>
	</br>
<% 
if(msg != null)
{
	out.print("<p style='color:red'>" + msg + "</p>");
}
%>
	
</body>
</html>