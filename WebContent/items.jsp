<%@page import="model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
if (request.getParameter("username") != null) 
{ 
 Item itemObj = new Item(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hidItemIDUpdate") == "") 
 { 
 stsMsg = itemObj.insertItem(request.getParameter("username"), 
 request.getParameter("password"), 
 request.getParameter("address"), 
 Integer.parseInt(request.getParameter("contac_no")),
 request.getParameter("email")); 
 } 
else//Update----------------------
 {
	System.out.println(request.getParameter("hidItemIDUpdate"));
 stsMsg = itemObj.updateItem(Integer.parseInt(request.getParameter("hidItemIDUpdate")), 
 request.getParameter("username"), 
 request.getParameter("password"), 
 request.getParameter("address"), 
 Integer.parseInt(request.getParameter("contac_no")),
 request.getParameter("email")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hidItemIDDelete") != null) 
{ 
 Item itemObj = new Item(); 
 String stsMsg = 
 itemObj.deleteItem(request.getParameter("hidItemIDDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 

<div class="container"><div class="row"><div class="col-6"> 

	<h1>User Management</h1>
	
	<form method="post" id="formItem" name="formItem" >
 		User Name: 
 		<input id="username" name="username" type="text" 
 					class="form-control form-control-sm"/>
 					
 		<br> Password: 
 		<input id="password" name="password" type="text" 
 					class="form-control form-control-sm"/>
 					
 		<br> Address: 
 		<input id="address" name="address" type="text" 
 					class="form-control form-control-sm"/>
 					
 		<br> Contact No: 
 		<input id="contac_no" name="contac_no" type="text" 
 					class="form-control form-control-sm"/>
 					
 		<br> Email: 
 		<input id="email" name="email" type="text" 
 					class="form-control form-control-sm"/>			
 					
 		<br>
 		<input id="btnSave" name="btnSave" type="submit" value="Save" 
 					class="btn btn-primary"/>
 		<input type="hidden" id="hidItemIDUpdate" 
 					name="hidItemIDUpdate" value=""/>
	</form>

	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>

	<br>
	<div id="divItemsGrid">
 			<%
 					Item itemObj = new Item(); 
 					out.print(itemObj.readItems()); 
 			%>
	</div>
</div> </div> </div> 
</body>
</html>