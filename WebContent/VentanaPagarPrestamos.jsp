<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Prestamos" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Cliente</title>
	<link rel="stylesheet" href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.css" />
    <!-- Estilos para el diseño -->
    <style>
        body {
            background-color: #E9F1FA;
        }

        .DiseñoBoton {
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
        
        .botonAtras {
            position: absolute;
            top: 10px;
               left: 10px;
            width: 120px;
            height: 40px;
            background-color: beige;
            cursor: pointer;
        }

        .customers {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        .customers td, .customers th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        .customers tr:nth-child(even) {
            background-color: #00ABE4;
        }

        .customers tr:hover {
            background-color: #ddd;
        }

        .customers th {
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
            width: 30%;
            height: 40px;
        }
        
        button {
            background-color: #04AA6D;
            color: white;
            border: none;
            padding: 10px 15px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<!-- Botón para salir -->
<input id="button" type="button" class="botonSalir" value="Salir" onclick="location.href='VentanaInicioSesion.jsp'">
<input type="button" id="btnAtras" class="botonAtras" value="Atras" onclick="location.href='UsuarioCliente.jsp'">

<!-- Formulario principal -->

    <div class="centrar">
        <!-- Bienvenida al usuario -->
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

        <!-- Grupo de botones -->
        
    </div>
    <br>
    <br>
    <!-- Tabla de préstamos -->
    <table id="table_id"class="customers">
        <thead>
            <tr>
                <th>ID Préstamo</th>
                <th>Fecha</th>
                <th>Importe Pedido</th>
                <th>Importe a Pagar</th>
                <th>Plazo de Pago</th>
                <th>Monto por Mes</th>
                <th>Condición</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% 
                // Obtener la lista de préstamos desde el atributo de la solicitud
                List<entidad.Prestamos> prestamos = (List<entidad.Prestamos>) request.getAttribute("listaPrestamos");

                // Validar que la lista de préstamos no sea nula ni esté vacía
                if (prestamos != null && !prestamos.isEmpty()) {
                    for (entidad.Prestamos prestamo : prestamos) { 
            %>
            <tr>
                <td><%= prestamo.getIDPrestamo() %></td>
                <td><%= prestamo.getFecha() != null ? prestamo.getFecha().toString() : "N/A" %></td>
                <td><%= prestamo.getImportePedido() %></td>
                <td><%= prestamo.getImporteAPagar() %></td>
                <td><%= prestamo.getPlazoDePago() %> meses</td>
                <td><%= prestamo.getMontoPorMes() %></td>
                <td><%= prestamo.getCondicion() != null ? prestamo.getCondicion().getDescripcion().toString() : "Sin definir" %></td>
                <td>
                    <!-- Botón para pagar cuota -->
                    <form action="ServletVerCuotas" method="get">
                        <input type="hidden" name="idPrestamo" value="<%= prestamo.getIDPrestamo() %>">
                        <button type="submit">Pagar Cuota</button>
                    </form>
                </td>
            </tr>
            <% 
                    } // Fin del bucle for
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
</body>
</html>

