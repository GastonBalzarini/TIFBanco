package servlets;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.PrestamoDao;
import daoImp.PrestamoDaoImpl;
import entidad.Cliente;
import entidad.Cuentas;
import entidad.Prestamos;
import entidad.Transferencias;
import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocio.PrestamoNeg;

import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;
import negocioImp.PrestamoNegImpl;


/**
 * Servlet implementation class ServletListarPrestamosCliente
 */
@WebServlet("/ServletPagarPrestamos")
public class ServletPagarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPagarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Instanciar los objetos de negocio necesarios
        ClienteNegImpl clienteNeg = new ClienteNegImpl();
        PrestamoNeg prestamoNeg = new PrestamoNegImpl();

        // Obtener la sesión actual, si existe
        HttpSession sesion = request.getSession(false);
        String usuarioSesion = (sesion != null) ? (String) sesion.getAttribute("nombreUsuario") : null;

        // Verificar si se obtuvo el usuario de la sesión
        if (usuarioSesion != null) {
            // Obtener el cliente asociado al usuario de sesión
            Cliente cliente = clienteNeg.obtenerClientesPorUsuarioObj(usuarioSesion);

            if (cliente != null) {
                int dni = cliente.getDni();

                // Obtener la lista de préstamos del cliente usando su DNI
                ArrayList<Prestamos> listaPrestamos = prestamoNeg.obtenerPrestamosClienteAceptados(dni);

                // Establecer la lista de préstamos como atributo de la request
                request.setAttribute("listaPrestamos", listaPrestamos);

                // Redirigir a la JSP para mostrar los préstamos
                request.getRequestDispatcher("VentanaPagarPrestamos.jsp").forward(request, response);
                return;
            }
        }
        // Si no hay sesión o cliente, redirigir al inicio de sesión
        response.sendRedirect("VentanaInicioSesion.jsp");
    }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del préstamo desde los parámetros de la solicitud
        String idPrestamoParam = request.getParameter("idPrestamo");

        // Validar que el parámetro no sea nulo ni vacío
        if (idPrestamoParam != null && !idPrestamoParam.isEmpty()) {
            try {
                // Convertir el parámetro a un número entero
                int idPrestamo = Integer.parseInt(idPrestamoParam);

                // Redirigir al ServletVerCuotas con el ID del préstamo como parámetro
                response.sendRedirect("ServletVerCuotas?idPrestamo=" + idPrestamo);
            } catch (NumberFormatException e) {
                // Manejar el caso en el que el parámetro no sea un número válido
                e.printStackTrace();
                request.setAttribute("error", "El ID del préstamo no es válido.");
                request.getRequestDispatcher("VerCuotas.jsp").forward(request, response);
            }
        } else {
            // Manejar el caso en el que no se envió el ID del préstamo
            request.setAttribute("error", "Debe seleccionar un préstamo para continuar.");
            request.getRequestDispatcher("VentanaPagarPrestamos.jsp").forward(request, response);
        }
    }


}
