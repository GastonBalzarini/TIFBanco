<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="css/StyleAgregarCliente.css" type="text/css">
	
    <script>
        function confirmarEliminacion() {
            const confirmacion = confirm("¿Estás seguro de que deseas eliminar este cliente?");
            return confirmacion; // Solo envía el formulario si el usuario confirma.
        }
    </script>
	
<title>Baja de cliente</title>
</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>
    <form action="ServletEliminarCliente" method="post" onsubmit="return confirmarEliminacion()">
        <h2>Baja de Cliente</h2>
        <input class="texto" type="number" name="clienteDNI" placeholder="DNI del Cliente"   maxlength="8" required>
        <input class="button" type="submit" value="Dar de baja" name="btnEliminar">
        <label id="mensaje" name="mensaje" style="color: red;">
	        <% String mensaje = (String) request.getAttribute("mensajeEliminar");
	        if(mensaje!=null){ %>
	        	<%= mensaje %>
	        <% } %>
	        </label>
    </form>
</main>
</body>
</html>