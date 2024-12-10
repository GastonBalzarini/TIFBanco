package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoImp.ClienteDaoImpl;
import daoImp.CuentasDaoImpl;
import entidad.Cliente;
import entidad.CondicionPrestamos;
import entidad.Cuentas;
import entidad.Cuota;
import entidad.Prestamos;
import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;
import negocioImp.PrestamoNegImpl;

@WebServlet("/ServletSolicitarPrestamo")
public class ServletSolicitarPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletSolicitarPrestamo() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		CuentasNeg cueNeg = new CuentasNegImpl();
		ArrayList<Cuentas> listaCuentas = new ArrayList<>();
		ClienteNeg cNeg = new ClienteNegImpl();
		
		HttpSession session = request.getSession(); 
	    
	    Cliente cliente = cNeg.obtenerClientesPorUsuarioObj((String) session.getAttribute("nombreUsuario"));
	    
	    listaCuentas = cueNeg.ObtenerCuentasPorDni(cliente.getDni());
	    
	    request.setAttribute("listaCuentas", listaCuentas);
	    request.getRequestDispatcher("Prestamos.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Inicialización de clases necesarias
	    Prestamos prestamo = new Prestamos();
	    PrestamoNegImpl prestamoNeg = new PrestamoNegImpl();
	    ClienteNegImpl clienteNeg = new ClienteNegImpl();
	    CuentasNegImpl cuentasNeg = new CuentasNegImpl();

	    try {
	        // Obtener la sesión y validar que el usuario esté autenticado
	        HttpSession session = request.getSession(false); 
	        if (session == null || session.getAttribute("nombreUsuario") == null) {
	            response.sendRedirect("VentanaInicioSesion.jsp");
	            return;
	        }

	        String usuarioSesion = (String) session.getAttribute("nombreUsuario");
	        Cliente cliente = clienteNeg.obtenerClientesPorUsuarioObj(usuarioSesion);
	        if (cliente == null) {
	            throw new Exception("No se pudo obtener información del cliente.");
	        }

	        // Asignar valores al objeto `Prestamos`
	        int ultimoIDPrestamo = prestamoNeg.obtenerUltimoIDPrestamo();
	        int IdPrestamo = ultimoIDPrestamo+1;
	        
	        prestamo.setIDPrestamo(IdPrestamo);
	        prestamo.setCliente(cliente.getDni());

	        // Validar y procesar el monto solicitado
	        String montoStr = request.getParameter("monto");
	        if (montoStr == null || montoStr.isEmpty()) {
	            throw new IllegalArgumentException("El monto del préstamo es obligatorio.");
	        }
	        float monto = Float.parseFloat(montoStr);
	        if (monto <= 0) {
	            throw new IllegalArgumentException("El monto debe ser mayor a cero.");
	        }
	        prestamo.setImportePedido(monto);

	        // Validar y procesar las cuotas
	        String cuotasStr = request.getParameter("cuotas");
	        if (cuotasStr == null || cuotasStr.isEmpty()) {
	            throw new IllegalArgumentException("Debe seleccionar una cantidad de cuotas.");
	        }
	        int cuotas = Integer.parseInt(cuotasStr);
	        if (cuotas <= 0) {
	            throw new IllegalArgumentException("El número de cuotas debe ser mayor a cero.");
	        }
	        prestamo.setPlazoDePago(cuotas);
	        prestamo.setCuotas(cuotas);

	        // Validar y procesar la cuenta seleccionada
	        String numeroCuentaStr = request.getParameter("filtroCuenta");
	        if (numeroCuentaStr == null || numeroCuentaStr.isEmpty()) {
	            throw new IllegalArgumentException("Debe seleccionar una cuenta para el depósito.");
	        }
	        int numeroCuenta = Integer.parseInt(numeroCuentaStr);
	        Cuentas cuenta = cuentasNeg.obtenerCuentaPorNumero(numeroCuenta);
	        if (cuenta == null) {
	            throw new Exception("La cuenta seleccionada no es válida.");
	        }
	        prestamo.setCuenta(cuenta);

	        // Establecer valores adicionales
	        prestamo.setFecha(new java.sql.Date(new java.util.Date().getTime()));
	        CondicionPrestamos condicion = new CondicionPrestamos();
	        condicion.setIdCondicion(3); 
	        prestamo.setCondicion(condicion);

	        // Calcular importe a pagar y monto por mes
	        float importeAPagar = monto * 1.10f; // Interés del 10%
	        prestamo.setImporteAPagar(importeAPagar);
	        float montoPorMes = importeAPagar / cuotas;
	        prestamo.setMontoPorMes(montoPorMes);

	        // Agregar el préstamo a la base de datos
	        int resultado = prestamoNeg.agregarPrestamo(prestamo);
	        if (resultado <= 0) {
	            throw new Exception("Error al registrar el préstamo en la base de datos.");
	        }

	        // Redirigir al JSP con un mensaje de éxito
	        request.setAttribute("mensajeExito", "El préstamo se ha solicitado correctamente.");
	        request.getRequestDispatcher("Prestamos.jsp").forward(request, response);

	    } catch (IllegalArgumentException e) {
	        // Manejo de errores de validación
	        request.setAttribute("mensajeError", e.getMessage());
	        request.getRequestDispatcher("Prestamos.jsp").forward(request, response);
	    } catch (Exception e) {
	        // Manejo de errores generales
	        e.printStackTrace();
	        request.setAttribute("mensajeError", "Ocurrió un error al procesar la solicitud: " + e.getMessage());
	        request.getRequestDispatcher("Prestamos.jsp").forward(request, response);
	    }
	}

}
