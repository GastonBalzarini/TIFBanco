<%@page import="entidad.CondicionPrestamos"%>
<%@page import="entidad.Prestamos"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="css/StyleUsuarioBanco.css" type="text/css" >
<title>Listado de prestamos</title>
<link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>
	<h2>Listar Prestamos</h2>
	<label>Filtrar por Condicion:</label>
	<form action="ServletListarPrestamos" method="post">
	<select id="filtroCondicion" name="filtroCon" onchange="this.form.submit()">
            <option value="Todos">Todos</option>
            <%
	  		ArrayList<CondicionPrestamos> listaCondicion = (ArrayList<CondicionPrestamos>) request.getAttribute ("listaCondicion");
	  			if (listaCondicion != null && !listaCondicion.isEmpty()){
	  				for (CondicionPrestamos con : listaCondicion){
	  		%>
	  					<option value="<%= con.getIdCondicion() %>" <%= (request.getParameter("condicion") != null && request.getParameter("condicion").equals(String.valueOf(con.getIdCondicion())) ? "selected" : "") %>>
            				<%= con.getDescripcion() %></option>
    		<% 
        		}
    				} 
   				%>
    </select>
    </form>
	
    <table id="table_id" class="atributos">
	<thead>
	  <tr>
	    	<th>Id Prestamo</th>
	        <th>Cliente</th>
	        <th>Numero de Cuenta</th>
	        <th>Fecha de solicitud</th>
	        <th>Importe solicitado</th>
	        <th>Importe a pagar</th>
	        <th>Plazo de pago (meses)</th>
	        <th>Condicion</th>
	  </tr>
	  </thead>
	  <tbody>
	  <%
	  	ArrayList<Prestamos> listaPrestamos = (ArrayList<Prestamos>) request.getAttribute ("listaPrestamos");
	  	if (listaPrestamos != null && !listaPrestamos.isEmpty()){
	  		for (Prestamos pre : listaPrestamos){
	  	
	  
	  
	  %>
	  
	  <tr>
	    	<td><%= pre.getIDPrestamo() %></td>
	        <td><%= pre.getCliente() %></td>
	        <td><%= pre.getCuenta().getNumeroCuenta() %></td>
	        <td><%= pre.getFecha() %></td>
	        <td><%= pre.getImportePedido() %></td>
	        <td><%= pre.getImporteAPagar() %></td>
	        <td><%= pre.getPlazoDePago() %></td>
	        <td><%= pre.getCondicion().getDescripcion() %></td>
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