<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/StyleAgregarCliente.css" type="text/css">
    <title>Baja de Cuentas</title>
</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>
    <form action="ServletEliminarCuenta" method="post">
        <h2>Baja de Cuentas</h2>
        <input class="texto" type="text" name="cuentaId" placeholder="ID de Cuenta" required>
        <button type="submit" name="btnEliminarCuenta">Dar de Baja</button>
        <label id="mensaje" name="mensaje" style="color: red;">
	        <%String mensaje = (String) request.getAttribute("mensajeEliminarCuenta");
	        if(mensaje!=null){
	        %>
	        <%= mensaje %>
	        <%}%>
	        </label>
    </form>
</main>
</body>
</html>
