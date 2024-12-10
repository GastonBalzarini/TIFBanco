<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/StyleAgregarCliente.css" type="text/css">
<title>Modificar cliente</title>
</head>
<body>
	
	<header><jsp:include page="MenuBanco.jsp" /></header>
	<main>
		
		<form action="ServletBuscarClienteMod" method="Post">
			<h2>Modificar cliente</h2>
			<input class="texto" type="text" name="buscarCliente" placeholder="DNI del cliente" maxlength="8"required>
			<input class="button" type="submit" name="Buscar" value="Buscar por DNI">
			
			<label id="mensaje" name="mensaje" style="color: red;">
	        <%String mensaje = (String) request.getAttribute("mensaje");
	        if(mensaje!=null){
	        %>
	        <%= mensaje %>
	        <%}%>
	        </label>
		</form>

	</main>
</body>
</html>