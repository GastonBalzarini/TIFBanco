<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cuentas" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="ISO-8859-1">
    <title>Listar Cuentas</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>


    <h2>Lista de Cuentas</h2>
	<form method ="post" action ="ServletListarCuentas">
	<input class="texto" type="number" name="buscarCuenta" placeholder="Ingrese el DNI" required>
	<input class="button" type="submit" value="Buscar">
	</form>
	<br>
	<form method ="get" action ="ServletListarCuentas">
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
                <th>DNI Cliente</th>
                <th>ID de Cuenta</th>
                <th>CBU</th>
                <th>Saldo</th>
                <th>Tipo de Cuenta</th>
                
            </tr>
        </thead>
        <tbody>
        <% 
        ArrayList<Cuentas> listaCuentas = (ArrayList<Cuentas>) request.getAttribute("listaCuentas");
        if (listaCuentas != null && !listaCuentas.isEmpty()) {
        	for (Cuentas cuenta : listaCuentas) {
       	
          %> 	
              <tr>
                <td><%= cuenta.getDniCliente()%></td>
                <td><%= cuenta.getNumeroCuenta()%></td>
                <td><%= cuenta.getCbu() %></td>
                <td><%= cuenta.getSaldo() %></td>
                <td><%= cuenta.getTipoCuenta() %></td>
                
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