<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/StyleAgregarCuenta.css" type="text/css">
    <title>Alta de Cuentas</title>
</head>
<body>
<header><jsp:include page="MenuBanco.jsp" /></header>
<main>
    <form action="ServletAltaCuenta" method="post">
        <h2>Alta de Cuenta</h2>

    
        <input class="texto" type="text" name="Cliente_cu" placeholder="DNI Cliente" maxlength= "8" required>
        <input type="hidden" name="idAlta_cu" id="idAlta_cu" value="">

        <select class="texto" name="TipoCuenta_cu" required>
            <option value="" disabled selected>Tipo de Cuenta</option>
            <option value="Caja de Ahorro">Caja de Ahorro</option>
            <option value="Cuenta Corriente">Cuenta Corriente</option>
        </select>

   <label class="texto" id="NumeroCuenta_cu">
    N° de Cuenta: 
    <span>
        <%= 
            (request.getAttribute("ultimoNumeroCuenta") != null) 
            ? request.getAttribute("ultimoNumeroCuenta") 
            : "No disponible" 
        %>
    </span>
</label>

<br/>

<label class="texto" id="CBU_cu">
    CBU: 
    <span>
        <%= 
            (request.getAttribute("nuevoCBU") != null) 
            ? request.getAttribute("nuevoCBU") 
            : "No disponible" 
        %>
    </span>
</label>

        <button class="button" type="submit">Agregar Cuenta</button>
        
            <label id="mensaje" name="mensaje" style="<%
            String mensaje = (String) request.getAttribute("mensaje");
            if (mensaje != null) {
                out.print(mensaje.equals("Cuenta agregada correctamente.") ? "color: green;" : "color: red;");
            }
        %>">
            <%
                if (mensaje != null) {
                    out.print(mensaje);
                }
            %>
        </label>
        
    </form>
</main>
</body>
</html>
