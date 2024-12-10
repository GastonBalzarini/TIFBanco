<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="entidad.Cuentas"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis cuentas</title>
<style>
        body {
            background-color: #E9F1FA;
             font-family: Arial, Helvetica, sans-serif;
        }
        
        .button{
        	width: 100%;
    		height: 40px;
   			background: #61caca6b;
    		border: none;
    		color: black;
    		margin-bottom: 16px;
    		font-weight: bold;
    		border-bottom: 1px solid;
        }
        
        .botonSalir {
            position: absolute;
            top: 10px;
            right: 10px;
            width: 120px;
            height: 40px;
            background-color: beige;
            cursor: pointer;
        }
        
         .botonAtras {
            position: absolute;
            top: 10px;
               left: 10px;
            width: 120px;
            height: 40px;
            background-color: beige;
            cursor: pointer;
        }
        
		#cuenta {
        width: 94%;
        border: 1px solid #60d8d2;
        margin-bottom: 15px;
        padding: 11px 10px;
        background: #ffffff;
        font-size: 14px;
        font-weight: bold;
    }
    
    .centrar {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin-top: 50px; 
    }
    
    select {
        margin-top: 20px; 
        padding: 10px;
        font-size: 14px;
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
		
		#customers tr:nth-child(even){background-color: #f2f2f2;}
		
		#customers tr:hover {background-color: #ddd;}
		
		#customers th {
		  padding-top: 12px;
		  padding-bottom: 12px;
		  text-align: left;
		  background-color: #04AA6D;
		  color: white;
		}

</style>
</head>
<body>



  <input type="button" id="btnSalir" class="botonSalir" value="Salir" onclick="location.href='VentanaInicioSesion.jsp'">
  <input type="button" id="btnAtras" class="botonAtras" value="Atras" onclick="location.href='UsuarioCliente.jsp'">
	
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
	
	<h1>Tu cuenta</h1>
	<label for ="cuenta"> Seleccione la cuenta:</label>
	<form method= "post" action= "ServletListarCuentaMisCuentas">
            <select name="filtroCuenta" id="cuenta" required>
    			<option value="" disabled selected>Seleccione Cuenta</option>
    			<%
    			ArrayList<Cuentas> listaCuentas = (ArrayList<Cuentas>) request.getAttribute("listaCuentas");   			
    			if (listaCuentas != null) {
        			for (Cuentas cue : listaCuentas) { 
   				 %>
        			<option value="<%= cue.getNumeroCuenta() %>">
           			<%= cue.getCbu() %>
        			</option>
   					<% 
       					 }
    					}  
   					 %>
		</select>
    <button type="submit" class="button">Seleccionar</button>
</form>
<h1>Tu cuenta</h1>

<table id="customers">
  <tr>
    <th>DNI</th>
    <th>Fecha Creacion</th>
    <th>Numero de cuenta</th>
    <th>Tipo de cuenta</th>
    <th>Saldo</th>
  </tr>
  <tr>
     	  <%
      ArrayList<Cuentas> listaCuentasCBU = (ArrayList<Cuentas>) request.getAttribute("listaCuentasCBU");
      if (listaCuentasCBU != null && !listaCuentasCBU.isEmpty()) {
           for (Cuentas cue : listaCuentasCBU){
	  	
	  %>
  </tr>
  <tr>
    <td><%= cue.getDniCliente() %></td>
    <td><%= cue.getNumeroCuenta() %></td>
    <td><%= cue.getCbu() %></td>
    <td><%= cue.getTipoCuenta() %></td>  
    <td>$<%= cue.getSaldo() %></td>
  </tr>
  <tr>
  
 	 <%
	  		}
	  	}else {
	%>
	        <tr><td colspan="5">No hay clientes disponibles.</td></tr>
	<% 
	    }
	%>
  
  
</table>


</body>





</html>