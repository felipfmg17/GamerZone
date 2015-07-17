<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import ="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Games</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/kikin.css">
</head>


<body>

<div >
	<h3>My Games</h3><br>
	<br>
	
	<a href="Control?action=catalog">Catalog</a> <a href="Control?action=login">Exit</a>
	
	<br><br>
</div>	
	<%ResultSet res=(ResultSet)request.getAttribute("MyGames"); %>
	
<div>
	<table border=1>
		<tr><td><b>IdPedido</b></td><td><b>Juego</b></td><td><b>Imagen</b></td><td><b>Precio</b></td>
		
		<%
		int total=0;
		if(res!=null){
			while(res.next()){
				total+=res.getInt(4);
				
		%>
				<tr>
				
				<td><%=res.getString(1) %></td>
				<td><%=res.getString(2) %></td>
				<td><img width=77 height=100 src="<%="imagenes/"+res.getString(3) %>"/></td>
				<td>$ <%=res.getString(4) %></td>
				<td> <a href="Control?action=delete&id=<%=res.getString(1)%>">Delete</a>
				
				</tr>
	
<%
			}
			
		}
%>		
		
	
	</table>

</div>	

<br>
<div>
Total: $ <%=total %>
</div>
	
</body>
</html>