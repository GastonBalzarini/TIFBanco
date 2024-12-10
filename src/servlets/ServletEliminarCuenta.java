package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.CuentasNeg;
import negocioImp.CuentasNegImpl;

@WebServlet("/ServletEliminarCuenta")
public class ServletEliminarCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletEliminarCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 CuentasNeg cNeg = new CuentasNegImpl();
		    int filas = 0;

		    if (request.getParameter("btnEliminarCuenta") != null) {
		        String ingreso = request.getParameter("cuentaId");
		        try {
		            int id = Integer.parseInt(ingreso);
		            filas = cNeg.eliminarCuenta(id);
		            if (filas != 0) {
		                request.setAttribute("mensajeEliminarCuenta", "Cuenta Eliminada.");
		            } else {
		                request.setAttribute("mensajeEliminarCuenta", "La cuenta no se encuentra en la base de datos.");
		            }
		        } catch (NumberFormatException e) {
		            request.setAttribute("mensajeEliminarCuenta", "Debe ingresar un número entero.");
		        }
		        request.getRequestDispatcher("BajaDeCuentas.jsp").forward(request, response);
		    }	
	}
		
	}
