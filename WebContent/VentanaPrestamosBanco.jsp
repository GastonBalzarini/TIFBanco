<%@page import="entidad.Prestamos"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="StyleSheet" href="css/StyleUsuarioBanco.css" type="text/css" >
<title>Prestamos</title>
<link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>
	<h2>Avisos de Préstamos</h2>
	<% 
			ArrayList<Prestamos> listaPrestamos = null;
			if(request.getAttribute("listaPrestamo")!= null)
			{
				listaPrestamos = (ArrayList<Prestamos>) request.getAttribute("listaPrestamo");
			}
	%>
    <table id="table_id" class="atributos">
  <thead>
  <tr>
    	<th>ID Solicitud</th>
        <th>Cliente</th>
        <th>ID de Cuenta</th>
        <th>Fecha de Solicitud</th>
        <th>Importe Solicitado</th>
        <th>Plazo (meses)</th>
        <th>Condicion</th>
        <th>Acciones</th>
  </tr>
  </thead>
  <tbody>
  <%if(listaPrestamos !=null)
	  for(Prestamos p : listaPrestamos){
	%>
  <tr>
    	<td><%=p.getIDPrestamo() %></td>
        <td><%=p.getCliente() %></td>
        <td><%=p.getCuenta().getNumeroCuenta() %></td>
        <td><%=p.getFecha() %></td>
        <td><%=p.getImportePedido() %></td>
        <td><%=p.getPlazoDePago() %></td>
        <td><%=p.getCondicion().getDescripcion() %></td>
        <td>
        <form action="ServletSolicitudPrestamo" method="post">
        
          <input type="hidden" name="PlazoDePago" value="<%=p.getPlazoDePago() %>">
        <input type="hidden" name="id_prestamo" value="<%=p.getIDPrestamo() %>">
        <input type="hidden" name="NumCuenta" value="<%=p.getCuenta().getNumeroCuenta() %>">
        <input type="hidden" name="Importe" value="<%=p.getImportePedido()%>">
        <input type="hidden" name="id_solicitud" value="<%=p.getIDPrestamo() %>">
        <input class="button" type="submit" name="btnAprobar" value="Aprobar">
        <input class= "button2" type="submit" name="btnRechazar" value="Rechazar">
        </form>
        </td>
  		</tr><%
    	}%>
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
<c:if test="${not empty condicion}">
    <div class="alert alert-success" style="${not empty condicion ? '' : 'display:none;'}">${condicion}</div>
</c:if>
</main>
</body>
</html>