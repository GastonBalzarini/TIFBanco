
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;">
<title>Banco XX</title>
<link rel="StyleSheet" href="estilos.css" type="text/css" >
</head>
<body id="body">
   <div id="formulario">
		<h2>Inicio de Sesión</h2>
	    <form action="ServletUsuarios" method="Post" id="formIngr">
	        <label for="lblUsuario">Usuario:</label>
	        <input id="text" type="text" name="txtUsuario">
	        <br>
	        <label for="lblContraseña">Contraseña:</label>
	        <input id="text" type="password" name="txtContraseña">
	        <br>
	        <input id="button" type="submit" name="btnAceptar" value="Iniciar sesion">
	        <label id="mensaje" name="mensaje" style="color: red;">
	        <%String mensaje = (String) request.getAttribute("mensaje");
	        if(mensaje!=null){
	        %>
	        <%= mensaje %>
	        <%}%>
	        </label>
	    </form>
	   
	</div>
</body>
</html>
