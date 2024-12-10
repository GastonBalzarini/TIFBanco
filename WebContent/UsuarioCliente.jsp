<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
        <%@ page import="java.util.ArrayList" %>
<%@ page import="entidad.Cliente" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cliente</title>
<div class="encabezado"></div>
<style>
	
</style>


<body style = "background-color:#E9F1FA ">

<style>
	.DiseñoBoton{
	background-color: beige;
}

  .botonSalir {
        position: absolute;
        top: 10px;
        right: 10px;
        width: 120px;
        height: 40px;
        	background-color: beige;
         }    
         
	#customers {
	  font-family: Arial, Helvetica, sans-serif;
	  border-collapse: collapse;
	  width: 100%;
	}
	
	#customers td, #customers th {
	  border: 1px solid #ddd;
	  padding: 8px;
	}
	
	
	#customers th {
	  padding-top: 12px;
	  padding-bottom: 12px;
	  text-align: left;
	  background-color: #04AA6D;
	  color: white;
	}
	
	
	.centrar {
        display: flex;
        flex-direction: column;
        align-items: center; 
        justify-content: center; 
        gap: 45px; 
    }
    
    .grupoBotones {
        display: flex;
        flex-wrap: wrap; 
        justify-content: center; 
        gap: 20px; 
        max-width: 480px; 
    }

    .grupoBotones button {
        width: 45%; /* Establece el ancho de cada botón a la mitad del contenedor */
        height: 50px;
    }
    
   
    
}

</style>

	

<input id="button" type="button" class="botonSalir" value="Salir" onclick="location.href='VentanaInicioSesion.jsp'">
</head>
<body>


		<div class="centrar">
		
		<!-- Bloque de Bienvenida con sesión -->
		    <div style="text-align: right; margin: 10px; font-size: 16px;">
		        <% 
		            HttpSession sesion = request.getSession(false); 
		            String usuarioSesion = (sesion != null) ? (String) sesion.getAttribute("nombreUsuario") : null;
		            if (usuarioSesion != null) {
		        %>
		            Bienvenido, <b><%= usuarioSesion %></b>
		        <% 
		            } else { 
		        %>
		            Bienvenido, <b>Invitado</b>
		        <% 
		            } 
		        %>
		    </div>





 <div class="grupoBotones">
<input id="btnCuentas" type="button" class= "DiseñoBoton" name="btnCuentas" value="Cuentas" onclick="location.href='ServletListarCuentaMisCuentas'" style="width: 217px; height: 50px; "> 
<input id="btnVerCuentas" type="button" class= "DiseñoBoton" name="btnRealizarTransferencia" value="Realizar Transferencia" onclick="location.href='ServletRealizarTransferencia'" style="width: 217px; height: 50px; ">
     
     
<input id="btnPrestamos" type="button" class= "DiseñoBoton" name="btnPrestamos" value="Prestamos" onclick="location.href='ServletSolicitarPrestamo'" style="width: 217px; height: 50px; ">
<input id="btnTransferencias" type="button" class= "DiseñoBoton" name="btnTranseferencias" value="Transferencias" onclick="location.href='ServletListarPrestamosCliente'" style="width: 217px; height: 50px; ">
<input id="btnPagarPrestamos" type="button" class= "DiseñoBoton" name="btnPagarPrestamos" value="Pagar Prestamos" onclick="location.href='ServletPagarPrestamos'" style="width: 217px; height: 50px; ">

	    </div>
	 </div>
	 

<body>
<form method="get" action="ServletListarClienteCuentaCliente">
    <input type="submit" class= "DiseñoBoton" value="Ver mi cuenta" />
</form>
<form>
<h1>Tu cuenta</h1>

<table id="customers">
	<thead>
	  <tr>
	    <th>Nombre</th>
	    <th>Apellido</th>
	    <th>E-Mail</th>
	    <th>DNI</th>
	    <th>CUIL</th>
	    <th>Fecha de nacimiento</th>
	    <th>Nacionalidad</th>
	  </tr>
	</thead>
	<tbody>
	  <%
	  	ArrayList<Cliente> listaCliente = (ArrayList<Cliente>) request.getAttribute ("listaClientes");
	  	if (listaCliente != null && !listaCliente.isEmpty()){
	  		for (Cliente cliente : listaCliente){
	  	
	  
	  
	  %>
	  <tr>
	    
	  </tr>
	  <tr>
		    <td><%= cliente.getNombre() %></td>
		    <td><%= cliente.getApellido() %></td>
		    <td><%= cliente.getCorreoElectronico() %></td>
		    <td><%= cliente.getDni() %></td>
		    <td><%= cliente.getCuil() %></td>
		    <td><%= cliente.getFechaNacimiento() %></td>
		    <td><%= cliente.getNacionalidad().getDescripcion() %></td>
	  </tr>
	  <tr>
  <%
	  		}
	  	}else {
	%>
	        <tr><td colspan="4">No hay clientes disponibles.</td></tr>
	<% 
	    }
	%>

	  
    			</tbody>
			</table>
		</form>
	</body>
</html>





</body>
</html>