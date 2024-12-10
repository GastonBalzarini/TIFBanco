<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="css/StyleAgregarCliente.css" type="text/css">
    <title>Modificar Cuentas</title>
</head>
<body>
<header>
    <jsp:include page="MenuBanco.jsp" />
</header>
<main>
    <h2>Modificar Cuentas</h2>

   

    <!-- Formulario para modificar cuenta -->
    <form action="ServletModificarCuenta" method="post">
        <input class="texto" type="text" name="numeroDeCuenta" placeholder="Número de Cuenta" required>
        <input class="texto" type="text" name="nuevoSaldo" placeholder="Nuevo Saldo">
        
        <select class="texto" name="nuevoTipo">
            <option value="" disabled selected>Selecciona un tipo de cuenta</option>
            <option value="Caja de ahorro">Caja de ahorro</option>
            <option value="Cuenta corriente">Cuenta corriente</option>
        </select>

        <button type="submit">Modificar Cuenta</button>
        
         <!-- Mostrar mensaje dinámico con <label> -->
    <label id="mensaje" name="mensaje" style="<%
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null && mensaje.equals("Cuenta modificada exitosamente.")) {
            out.print("color: green;");
        } else {
            out.print("color: red;");
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
