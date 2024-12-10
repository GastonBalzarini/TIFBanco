package servlets;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daoImp.CuentasDaoImpl;
import entidad.Cuentas;

@WebServlet("/ServletAltaCuenta")
public class ServletAltaCuenta extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CuentasDaoImpl cuentasDao = new CuentasDaoImpl();

 
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // Obtener el último número de cuenta
            int ultimoNumeroCuenta = cuentasDao.obtenerUltimoNumeroCuenta();
            int nuevoNumeroCuenta = ultimoNumeroCuenta + 1;

            // Obtener el último CBU como String
            String ultimoCBU = cuentasDao.obtenerUltimoCBU();
            BigInteger ultimoCBUNum = new BigInteger(ultimoCBU); 
            BigInteger nuevoCBU = ultimoCBUNum.add(BigInteger.ONE);


            // Configurar los atributos para el JSP
            request.setAttribute("ultimoNumeroCuenta", nuevoNumeroCuenta);
            request.setAttribute("nuevoCBU", nuevoCBU);

            // Redirigir a la página JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("AltaCuentas.jsp");
            dispatcher.forward(request, response);
        }
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener parámetros del formulario
            String DNI = request.getParameter("Cliente_cu");
            String tipoCuenta = request.getParameter("TipoCuenta_cu");
            //String cbu = cuentasDao.obtenerUltimoCBU()+1;
            
            String ultimoCBU = cuentasDao.obtenerUltimoCBU();
            BigInteger ultimoCBUNum = new BigInteger(ultimoCBU); 
            BigInteger nuevoCBU = ultimoCBUNum.add(BigInteger.ONE);
            String cbu = nuevoCBU.toString();
            
            // Crear un nuevo objeto de cuenta
            Cuentas cuenta = new Cuentas();

            // Calcular el nuevo número de cuenta
            int nuevoNumeroCuenta = cuentasDao.obtenerUltimoNumeroCuenta()+1; 

            // Asignar valores al objeto cuenta
            cuenta.setDniCliente(Integer.parseInt(DNI));
            cuenta.setTipoCuenta(tipoCuenta);
            cuenta.setNumeroCuenta(nuevoNumeroCuenta);
            cuenta.setCbu(cbu);
            cuenta.setSaldo(10000.0f); // Saldo inicial fijo de $10,000
            
            System.out.println("CBU" + cbu+".");

            // Establecer la fecha de creación
            Date fechaActual = Date.valueOf(LocalDate.now());
            cuenta.setFechaCreacion(fechaActual);

            // Validar el número de cuentas asignadas al cliente
            int cuentasCliente = cuentasDao.contarCuentasPorCliente(Integer.parseInt(DNI));
            if (cuentasCliente >= 3) {
                // Si el cliente ya tiene 3 cuentas, mostrar un mensaje de error
                request.setAttribute("mensaje", "Error: Un cliente no puede tener más de 3 cuentas.");
            } else {
                // Agregar la cuenta a la base de datos
                boolean cuentaAgregada = cuentasDao.agregarCuenta(cuenta);

                if (cuentaAgregada) {
                    request.setAttribute("mensaje", "Cuenta agregada correctamente.");
                } else {
                    request.setAttribute("mensaje", "Error: No se pudo agregar la cuenta.");
                }
            }

            // Volver al formulario con el mensaje
            doGet(request, response);
        } catch (NumberFormatException e) {
            // Manejar excepciones y enviar un mensaje de error
            request.setAttribute("mensaje", "Error: Debe ingresar numeros.");
            doGet(request, response);
        }
    }
}

