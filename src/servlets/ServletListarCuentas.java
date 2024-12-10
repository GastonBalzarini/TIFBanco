
package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentasNeg;
import negocioImp.CuentasNegImpl;  
import entidad.Cuentas;
import excepciones.BuscarNombreConNumerosException;


@WebServlet("/ServletListarCuentas")
public class ServletListarCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListarCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CuentasNeg cuentasNeg = new CuentasNegImpl();
        ArrayList<Cuentas> listaCuentas = cuentasNeg.obtenerCuentas();
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("ListarCuentas.jsp").forward(request, response);
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String DNIStr = request.getParameter("buscarCuenta");
	    if (DNIStr == null || DNIStr.isEmpty()) {
	        request.setAttribute("error", "Debe ingresar un DNI para buscar.");
	        request.getRequestDispatcher("ListarCuentas.jsp").forward(request, response);
	    }

	    try {
	        int dni = Integer.parseInt(DNIStr);
	        CuentasNeg cue = new CuentasNegImpl();
	        ArrayList<Cuentas> listaCuentasNom = cue.ObtenerCuentasPorDniLIKE(dni);
	        
	        if (listaCuentasNom.isEmpty()) {
	            request.setAttribute("mensaje", "No se encontraron cuentas para el DNI ingresado.");
	        } else {
	            request.setAttribute("listaCuentas", listaCuentasNom);
	        }
	    } catch (NumberFormatException e) {
	        request.setAttribute("error", "El DNI debe ser un número válido.");
	    }

	    request.getRequestDispatcher("ListarCuentas.jsp").forward(request, response);
	}
}