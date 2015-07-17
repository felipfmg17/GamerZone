<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GameZone Login</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/kikin.css">
</head>
<body>

<h2>GameZone</h2>
<br><br>

<div class="panel-heading">

<form method="post" action="Control">

User  <input type="text" name="User"><br><br>
Password <input type="password" name="Password"><br><br>
<input type="submit">

</form>
<br>

</div>

<div >
<%if( request.getParameter("error")!=null){  %>
<%=request.getParameter("error") %>
<%} %>
</div>


</body>
</html>