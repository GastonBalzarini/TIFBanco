package servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoImp.CuentasDaoImpl;
import entidad.Cuentas;

@WebServlet("/ServletModificarCuenta")
public class ServletModificarCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasDaoImpl cuentasDao = new CuentasDaoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del formulario
        String numeroCuentaStr = request.getParameter("numeroDeCuenta");
        String nuevoSaldoStr = request.getParameter("nuevoSaldo");
        String nuevoTipo = request.getParameter("nuevoTipo");

        try {
            // Convertir parámetros
            int numeroCuenta = Integer.parseInt(numeroCuentaStr);
            Float nuevoSaldo = null;
            if (nuevoSaldoStr != null && !nuevoSaldoStr.isEmpty()) {
                nuevoSaldo = Float.parseFloat(nuevoSaldoStr);
            }

            // Buscar la cuenta
            Cuentas cuenta = cuentasDao.obtenerCuentaPorNumero(numeroCuenta);
            if (cuenta != null) {
                // Actualizar los campos de la cuenta
                if (nuevoSaldo != null) {
                    cuenta.setSaldo(nuevoSaldo);
                }
                if (nuevoTipo != null && !nuevoTipo.isEmpty()) {
                    cuenta.setTipoCuenta(nuevoTipo);
                }

                // Actualizar en la base de datos
                boolean actualizacionExitosa = cuentasDao.modificarCuenta(cuenta);
                if (actualizacionExitosa) {
                    request.setAttribute("mensaje", "Cuenta modificada exitosamente.");
                } else {
                    request.setAttribute("mensaje", "Error al modificar la cuenta.");
                }
            } else {
                request.setAttribute("mensaje", "La cuenta no fue encontrada.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("mensaje", "Error: los datos ingresados no son válidos.");
        }

        // Redirigir al JSP con el mensaje
        RequestDispatcher dispatcher = request.getRequestDispatcher("ModificarCuentas.jsp");
        dispatcher.forward(request, response);
    }
}
