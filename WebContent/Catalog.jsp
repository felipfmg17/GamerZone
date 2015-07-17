<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalog</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/kikin.css">
</head>

<body>


<div><h3>Catalog</h3></div>

<div>	
	<br>
	Hello <%=session.getAttribute("sessionUser") %>
	
	<br>
	<br>
	<a href="Control?action=MyGames">My Games</a> <a href="Control?action=login">Exit</a>
	
</div>

<br>
<br>

<div >
	<table border="1">
		<tr><td><b>Nombre</b></td><td><b>Imagen</b></td><td><b>Consola</b></td><td><b>Precio</b></td>
		<% 
		ResultSet res=(ResultSet)request.getAttribute("catalog");
		if(res!=null){
			while(res.next()){
		%>
				<tr>
				
				<td><%=res.getString(1) %></td>
				<td><img width=77 height=100 src="<%="imagenes/"+res.getString(2) %>"/></td>
				<td><%=res.getString(3) %></td>
				<td>$ <%=res.getString(4) %></td>
				<td><a href="Control?action=buy&game=<%=res.getString(1) %>">Comprar</a></td>
				
				</tr>
		<%
			}
		}
		%>
	</table>
</div>
</body>
</html>