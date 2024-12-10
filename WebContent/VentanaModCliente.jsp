<%@page import="entidad.Localidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entidad.Sexo"%>
<%@page import="entidad.Cliente"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/StyleAgregarCliente.css" type="text/css">
 
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">

    <script>
        function confirmarModificacion() {
            const confirmacion = confirm("¿Estás seguro de que deseas modificar este cliente?");
            return confirmacion; // Solo envía el formulario si el usuario confirma.
        }
    </script>

<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr/dist/l10n/es.js"></script>

<title>Modificar cliente</title>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        flatpickr("#fechaNacimiento", {
            dateFormat: "Y-m-d", // Formato compatible con java.sql.Date
            maxDate: "today",
            locale: "es"    // Evita seleccionar fechas futuras
        });
    });
</script>
</head>
<body>
	
	<header><jsp:include page="MenuBanco.jsp" /></header>
	<main>
	
		
		<form action="ServletModificarCliente" method="Post" onsubmit="return confirmarModificacion()">
			<%
				String mensaje = (String) request.getAttribute("mensaje");
				if (mensaje != null) {
			%>
				<div class="mensaje" style="color: <%= mensaje.contains("error") ? "red" : "green" %>;">
					<%= mensaje %>
				</div>
			<%
				}
				
			    Cliente cliente = (Cliente) request.getAttribute("cliente");
			%>
		    <h2>Modificar cliente</h2>
			<input class="texto" type="text" placeholder="Nombre" name="nombre" value="<%= cliente.getNombre() != null ? cliente.getNombre() : "" %>" required>
			<c:if test="${not empty nombreError}">
			<div class="alert alert-danger" style="${not empty nombreError ? '' : 'display:none;'}">${nombreError}</div>
			</c:if>

    		<input class="texto" type="text" placeholder="Apellido" name="apellido" value="<%= cliente.getApellido() != null ? cliente.getApellido() : "" %>" required>
			<c:if test="${not empty apellidoError}">
			<div class="alert alert-danger" style="${not empty apellidoError ? '' : 'display:none;'}">${apellidoError}</div>
			</c:if>
    		<input class="email" type="email" placeholder="Email" name="email" value="<%= cliente.getCorreoElectronico() != null ? cliente.getCorreoElectronico() : "" %>" required>
			<input class="texto" type="number" placeholder="Dni" name="dni" value="<%= String.valueOf(cliente.getDni()) != null ? String.valueOf(cliente.getDni()) : "" %>" required readonly>
			<input class="texto" type="number" placeholder="Cuil" name="cuil" value="<%= cliente.getCuil() != null ? cliente.getCuil() : "" %>" required>
			<input class="texto" type="date" id="fechaNacimiento" placeholder="Fecha de Nacimiento" name="fechaNacimiento" value="<%= cliente.getFechaNacimiento() != null ? cliente.getFechaNacimiento() : "" %>" required>
			<input class="texto" type="text" placeholder="Direccion" name="Direccion" value="<%= cliente.getDireccion() != null ? cliente.getDireccion() : "" %>" required>
			<input class="texto" type="tel" placeholder="Telefono" name="telefono" value="<%= cliente.getTelefono() != null ? cliente.getTelefono() : "" %>" required>
			
			<select name="sexo" id="filtroSexo" required>
    		<option value="" disabled selected>Seleccione Sexo</option>
    		<%
			ArrayList<Sexo> listaSexo = (ArrayList<Sexo>) request.getAttribute("listaSexos");
			String sexoSeleccionado = (cliente.getSexo() != null && String.valueOf(cliente.getSexo().getId()) != null)
                          ? String.valueOf(cliente.getSexo().getId())
                          : "";

			if (listaSexo != null) {
    			for (Sexo sexo : listaSexo) {
			%>
        		<option value="<%= sexo.getId() %>" <%= sexoSeleccionado.equals(String.valueOf(sexo.getId())) ? "selected" : "" %>>
           		<%= sexo.getDescripcion() %>
        		</option>
			<%
    			}
			} else {
			%>
    			<option value="" disabled>No hay opciones de sexo disponibles</option>
			<%
					}
			%>
			</select>
			<select id="filtroNacionalidad" name="nacionalidad" required>
    		<option value="" disabled selected>Seleccione Nacionalidad</option>
    		<%
			ArrayList<Nacionalidad> listaNacionalidades = (ArrayList<Nacionalidad>) request.getAttribute("listaNac");
			String nacionalidadSeleccionada = (cliente.getNacionalidad() != null && String.valueOf(cliente.getNacionalidad().getId()) != null)
                                  ? String.valueOf(cliente.getNacionalidad().getId())
                                  : "";

			if (listaNacionalidades != null) {
    			for (Nacionalidad nacionalidad : listaNacionalidades) {
			%>
        		<option value="<%= nacionalidad.getId() %>" <%= nacionalidadSeleccionada.equals(String.valueOf(nacionalidad.getId())) ? "selected" : "" %>>
            	<%= nacionalidad.getDescripcion() %>
        		</option>
			<%
    			}
			} else {
			%>
    			<option value="" disabled>No hay opciones de nacionalidad disponibles</option>
			<%
			}
			%>
			</select>
			<select name="filtroProvincia" onchange="this.form.submit()" required>
    		<option value="" disabled selected>Seleccione Provincia</option>
    		<%
			ArrayList<Provincia> listaProvincias = (ArrayList<Provincia>) request.getAttribute("listaPro");
			String provinciaSeleccionada = (cliente.getProvincia() != null && String.valueOf(cliente.getProvincia().getIDProvincia()) != null)
                               ? String.valueOf(cliente.getProvincia().getIDProvincia())
                               : "";

			if (listaProvincias != null) {
    		for (Provincia provincia : listaProvincias) {
			%>
        		<option value="<%= provincia.getIDProvincia() %>" <%= provinciaSeleccionada.equals(String.valueOf(provincia.getIDProvincia())) ? "selected" : "" %>>
            	<%= provincia.getNombre() %>
        		</option>
			<%
    			}
			} else {
			%>
    		<option value="" disabled>No hay opciones de provincia disponibles</option>
			<%
			}
			%>
			</select>
			<select name="Localidad" required>
    		<option value="" disabled selected>Seleccione Localidad</option>
    		<% 
    		ArrayList<Localidad> listaLocalidad = (ArrayList<Localidad>) request.getAttribute("listaLoc");
    		String LocalidadSeleccionada = String.valueOf(cliente.getLocalidad().getIdLocalidad() );
    		if (listaLocalidad != null) {
        		for (Localidad loc : listaLocalidad) {
    		%>
        		<option value="<%= loc.getIdLocalidad() %>" <%= (LocalidadSeleccionada != null && LocalidadSeleccionada.equals(String.valueOf(loc.getIdLocalidad())) ? "selected" : "") %>>
            	<%= loc.getNombre() %>
       			</option>
    			<% 
       				 }
    			} 
    			%>
			</select>
			<input class="texto" type="text" placeholder="usuario" name="usuario" value="<%= cliente.getUsuario().getUsuario() != null ? cliente.getUsuario().getUsuario() : "" %>" required readonly>
			<input class="texto" type="password"  name ="contrasena" placeholder="Nueva Contraseña">
			<input class="button" type="submit" name="btnModificar" value="Modificar">
		</form>
	</main>
</body>
</html>