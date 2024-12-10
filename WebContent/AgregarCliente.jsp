<%@page import="entidad.Localidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Sexo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="StyleSheet" href="css/StyleAgregarCliente.css" type="text/css">
    <script src="js/cargarLocalidades.js"></script>
  
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/es.js"></script>
  
    <title>Agregar Cliente</title>



<script>
    document.addEventListener("DOMContentLoaded", function () {
        flatpickr("#fechaNacimiento", {
            dateFormat: "Y-m-d", // Formato compatible con java.sql.Date
            maxDate: "today",// Evita seleccionar fechas futuras
            locale: "es"
        });
    });
</script>

    
    
    
</head>

<body>
<header><jsp:include page="MenuBanco.jsp" />

   
</header>
<main>
<form Action="ServletAgregarCliente" method="Post">
<h2>Agregar Cliente</h2>

<c:if test="${not empty mensajeExito}">
    <div class="alert alert-success" style="${not empty mensajeExito ? '' : 'display:none;'}">${mensajeExito}</div>
</c:if>



    <input class="texto" type="text" placeholder="Nombre" name="nombre" value="<%= request.getAttribute("nombre") != null ? request.getAttribute("nombre") : "" %>" required>
    <c:if test="${not empty nombreError}">
	<div class="alert alert-danger" style="${not empty nombreError ? '' : 'display:none;'}">${nombreError}</div>
	</c:if>

    <input class="texto" type="text" placeholder="Apellido" name="apellido" value="<%= request.getAttribute("apellido") != null ? request.getAttribute("apellido") : "" %>" required>
    <c:if test="${not empty apellidoError}">
	<div class="alert alert-danger" style="${not empty apellidoError ? '' : 'display:none;'}">${apellidoError}</div>
	</c:if>

    <input class="email" type="email" placeholder="Email" name="email" value="<%= request.getAttribute("email") != null ? request.getAttribute("email") : "" %>" required>

    <input class="texto" type="number" placeholder="Dni" name="dni" maxlength="8" value="<%= request.getAttribute("dni") != null ? request.getAttribute("dni") : "" %>" required>
    <c:if test="${not empty errorDNI}">
	<div class="alert alert-danger" style="${not empty errorDNI ? '' : 'display:none;'}">${errorDNI}</div>
	</c:if>

    <input class="texto" type="number" placeholder="Cuil" name="cuil" maxlength="12" value="<%= request.getAttribute("cuil") != null ? request.getAttribute("cuil") : "" %>" required>

    <input class="texto" type="date" id="fechaNacimiento" placeholder="Fecha de Nacimiento" name="fechaNacimiento" value="<%= request.getAttribute("fechaNacimiento") != null ? request.getAttribute("fechaNacimiento") : "" %>" required>
  
  
    
    
    <input class="texto" type="text" placeholder="Direccion" name="Direccion" value="<%= request.getAttribute("Direccion") != null ? request.getAttribute("Direccion") : "" %>" required>

    <input class="texto" type="tel" placeholder="Telefono" name="telefono" value="<%= request.getAttribute("telefono") != null ? request.getAttribute("telefono") : "" %>" required>
<select name="sexo" id="filtroSexo" required>
    <option value="" disabled selected>Seleccione Sexo</option>
    <%
    ArrayList<Sexo> listaSexo = (ArrayList<Sexo>) request.getAttribute("listaSexos");
    String sexoSeleccionado = (String) request.getAttribute("sexoSeleccionado"); // Asegúrate de que sea un String

    if (listaSexo != null) {
        for (Sexo sexo : listaSexo) { 
    %>
        <option value="<%= sexo.getId() %>" <%= (sexoSeleccionado != null && sexoSeleccionado.equals(String.valueOf(sexo.getId())) ? "selected" : "") %>>
            <%= sexo.getDescripcion() %>
        </option>
    <% 
        }
    }  
    %>
</select>

<select id="filtroNacionalidad" name="nacionalidad" required>
    <option value="" disabled selected>Seleccione Nacionalidad</option>
    <%
    ArrayList<Nacionalidad> listaNacionalidad = (ArrayList<Nacionalidad>) request.getAttribute("listaNac");
    String nacionalidadSeleccionada = (String) request.getAttribute("nacionalidadSeleccionada");

    if (listaNacionalidad != null) {
        for (Nacionalidad nac : listaNacionalidad) { 
    %>
        <option value="<%= nac.getId() %>" <%= (nacionalidadSeleccionada != null && nacionalidadSeleccionada.equals(String.valueOf(nac.getId())) ? "selected" : "") %>>
            <%= nac.getDescripcion() %>
        </option>
    <% 
        }
    }  
    %>
</select>
   <select name="filtroProvincia" onchange="this.form.submit()" required>
    <option value="" disabled selected>Seleccione Provincia</option>
    <% 
    ArrayList<Provincia> listaProvincia = (ArrayList<Provincia>) request.getAttribute("listaPro");
    if (listaProvincia != null) {
        for (Provincia pro : listaProvincia) {
    %>
        <option value="<%= pro.getIDProvincia() %>" <%= (request.getParameter("filtroProvincia") != null && request.getParameter("filtroProvincia").equals(String.valueOf(pro.getIDProvincia())) ? "selected" : "") %>>
            <%= pro.getNombre() %>
        </option>
    <% 
        }
    } 
    %>
</select>

<select name="Localidad" required>
    <option value="" disabled selected>Seleccione Localidad</option>
    <% 
    ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) request.getAttribute("listaLoc");
    if (listaLocalidad != null) {
        for (Localidad loc : listaLocalidad) {
    %>
        <option value="<%= loc.getIdLocalidad() %>" <%= (request.getParameter("Localidad") != null && request.getParameter("Localidad").equals(String.valueOf(loc.getIdLocalidad())) ? "selected" : "") %>>
            <%= loc.getNombre() %>
        </option>
    <% 
        }
    } 
    %>
</select>
    
<input class="texto" type="text" placeholder="Usuario" name="usuario" value="<%= request.getAttribute("usuario") != null ? request.getAttribute("usuario") : "" %>" required>

<div class="alert alert-danger" style="${not empty errorUsuario ? '' : 'display:none;'}">${errorUsuario}</div>

<input class="texto" type="password" placeholder="Contraseña" name="contrasena" value="<%= request.getAttribute("contrasena") != null ? request.getAttribute("contrasena") : "" %>" required>
<input class="texto" type="password" placeholder="Vuelva a ingresar la Contraseña" name="contrasenaRepetir" value="<%= request.getAttribute("contrasenaRepetir") != null ? request.getAttribute("contrasenaRepetir") : "" %>" required>
<c:if test="${not empty errorContrasena}">
<div class="alert alert-danger" style="${not empty errorContrasena ? '' : 'display:none;'}">${errorContrasena}</div>
</c:if>
<input name="AgregarCliente" class="button" type="submit" value="Agregar">


</form>
</main>
</body>
</html>