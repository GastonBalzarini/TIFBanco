<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link rel="StyleSheet" href="css/StyleUsuari
oBanco.css" type="text/css" >
<title>Insert title here</title>
</head>
<body>
<header class="encabezado">
	<div class="contenedor-encabezado">
	<img class="imagen-menu" src="imagenes/LOGO.png" alt="nav"/>
	<h1 style="color:#ff5733;"> TuBancoOnline </h1>
	<ul class="contenedor-links-menu">             
                
				<li class="links-menu">
            		<a style="color:#000;" class="links-menu" href="VentanaUsuarioBanco.jsp"> Home </a>
          		</li>
          		
                <li class="nav-item dropdown">
                
                 
                
                    <a style="color:#000;" class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       Administrar Cuentas
                    </a>
                    
                    
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                      <a class="dropdown-item" href="ServletAltaCuenta">Agregar cuenta</a>

                        <a class="dropdown-item" href="BajaDeCuentas.jsp">Baja de cuentas</a>
                        <a class="dropdown-item" href="ModificarCuentas.jsp">Modificar Cuentas</a>
                        <a class="dropdown-item" href="ServletListarCuentas">Listar Cuentas</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a style="color:#000;" class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       Administrar Clientes
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="ServletAgregarCliente">Alta de clientes</a>
                        <a class="dropdown-item" href="VentanaBajaCliente.jsp">Baja de clientes</a>
                        <a class="dropdown-item" href="VentanaBuscarClientMod.jsp">Modificar clientes</a>
                        <a class="dropdown-item" href="ServletListarCliente">Listar clientes</a>
                    </div>
                </li>
                
                <li class="nav-item dropdown">
                    <a style="color:#000;" class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       Reportes de prestamos
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="ServletSolicitudPrestamo">Solicitud de prestamos</a>
                        <a class="dropdown-item" href="ServletListarPrestamos">Mostrar todos los prestamos</a>
                    </div>
                </li>
                
                <li class="nav-item dropdown">
                	<a style="color:#000;" class= "nav-link dropdown-toggle links-menu" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                       <%= session.getAttribute("nombreUsuario") != null ? session.getAttribute("nombreUsuario") : "Usuario" %>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="VentanaInicioSesion.jsp">Cerrar Sesion</a>
                    </div>
                </li>
            </ul>
	</div>

	</header>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</html>