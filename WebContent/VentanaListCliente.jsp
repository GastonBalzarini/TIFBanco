<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listar Clientes</title>
<link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />

</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>
	<h2>Clientes</h2>
	<form method ="post" action ="ServletListarCliente">
	<input class="texto" type="text" name="buscarCliente" placeholder="Ingrese el nombre" required>
	<input class="button" type="submit" value="Buscar">
	</form>
	<br>
	<form method ="get" action ="ServletListarCliente">
	<input class= "button" type="submit" value = "Ver todos">
	<br>
	</form>
	
	<%
		String mensaje = (String) request.getAttribute("mensaje");
		if (mensaje != null && !mensaje.isEmpty()) {
	%>
		<div class="alert alert-warning"><%= mensaje %></div>
	<% } %>
		
	<table id="table_id" class="atributos">
	<thead>
	  <tr>
	    	<th>DNI</th>
	        <th>Cuil</th>
	        <th>Nombre</th>
	        <th>Apellido</th>
	        <th>Nacionalidad</th>
	        <th>Sexo</th>
	        <th>Correo Electronico</th>
	        <th>Telefono</th>
	  </tr>
	  </thead>
	  <tbody>
	  <%
	  	ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute ("listaClientes");
	  	if (listaCliente != null && !listaCliente.isEmpty()){
	  		for (Cliente cliente : listaCliente){
	  	
	  
	  
	  %>
	  
	  <tr>
	    	<td><%= cliente.getDni() %></td>
	        <td><%= cliente.getCuil() %></td>
	        <td><%= cliente.getNombre() %></td>
	        <td><%= cliente.getApellido() %></td>
	        <td><%= cliente.getNacionalidad().getDescripcion() %></td>
	        <td><%= cliente.getSexo().getDescripcion() %></td>
	        <td><%= cliente.getCorreoElectronico() %></td>
	        <td><%= cliente.getTelefono() %></td>
	  </tr>
	  <%
	  		}
	  	}
	  %>
	  
	   </tbody>
	</table>
<script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/2.1.8/js/dataTables.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    $('#table_id').DataTable({
        "paging": true,               // Habilita la paginación
        "pageLength": 5,              // van a hacer el numero de filas que aparecera en la pagina
        "lengthChange": false,        // Desactiva o avtica el menú para elegir el número de filas por página
        "searching": false,           // Desactiva o activa la búsqueda
        "ordering": true,             // Habilita el ordenamiento
        "language": {
            "emptyTable": "No se encontraron registros", // Mensaje cuando la tabla está vacía
            "info": "Mostrando _START_ a _END_ de _TOTAL_ registros", // Texto de paginación personalizado
            "infoEmpty": "Mostrando 0 a 0 de 0 registros", // Texto cuando no hay registros
        }
    });
});
    </script>
</main>
</body>
</html>