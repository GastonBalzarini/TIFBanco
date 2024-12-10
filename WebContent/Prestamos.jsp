<%@page import="entidad.Cuentas"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Solicitud de Préstamo</title>
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
        
        #formulario {
            width: 400px;
            background: white;
            margin: auto;
            margin-top: 90px;
            box-shadow: 7px 13px 37px #000;
            padding: 20px 30px;
            color: black;
            text-align: center;
        }
		
        #formulario h2 {
            margin: 0;
            padding: 0;
            height: 40px;
            border-bottom: 1px solid #ddd;
            font-size: 20px;
            margin-bottom: 30px;
        }

        #text, #cuotas, #cuenta {
            width: 94%;
            border: 1px solid #60d8d2;
            margin-bottom: 15px;
            padding: 11px 10px;
            background: #ffffff;
            font-size: 14px;
            font-weight: bold;
        }

        #submitButton {
            width: 100%;
            height: 40px;
           background-color: beige;
            cursor: pointer;
            border: none;
            color: black;
            margin-top: 20px;

            border-bottom: 1px solid;
       
        }
        
	.centrar {
        display: flex;
        flex-direction: column;
        align-items: center; 
        justify-content: center; 
        gap: 45px; 
    }
        
    </style>
    <br>
	  <br>
	    <br>
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
  
  
    <!-- Formulario para solicitar préstamo -->
    <div id="formulario">
        <h2>Solicitud de Préstamo</h2>
        

        
        <form method= "post" action= "ServletSolicitarPrestamo">
            <label for="monto">Monto del Préstamo:</label><br>
            <input type="number" id="monto" name="monto" placeholder="Ingrese el monto" required min="1" maxlength ="9"><br>
            <label for="cuotas">Cantidad de Cuotas:</label><br>
            <select id="cuotas" name="cuotas" required>
                <option value="6">6 meses</option>
                <option value="12">12 meses</option>
                <option value="18">18 meses</option>
                <option value="24">24 meses</option>
            </select><br>

            <label for="cuenta">Cuenta de Depósito:</label><br>
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
</select><br>
            <input type="submit" class="button" id="submitButton" value="Enviar Solicitud">
            

<%
    String mensajeExito = (String) request.getAttribute("mensajeExito");
    if (mensajeExito != null) {
%>
    <div class="exitoMensaje"><%= mensajeExito %></div>
<% 
    }
%>
<%
    String mensajeError = (String) request.getAttribute("mensajeError");
    if (mensajeError != null) {
%>
    <div class="errorMensaje"><%= mensajeError %></div>
<% 
    }
%>

            
            </form>
            <br>
            
    </div>
</body>
</html>
