package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocio.UsuariosNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;
import negocioImp.UsuariosNegImpl;

@WebServlet("/ServletEliminarCliente")
public class ServletEliminarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletEliminarCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNeg cNeg = new ClienteNegImpl();
		CuentasNeg cuNeg = new CuentasNegImpl(); 
		UsuariosNeg uNeg = new UsuariosNegImpl();
		int filas = 0;
		if(request.getParameter("btnEliminar")!=null) {
			String ingreso = request.getParameter("clienteDNI");
			int dni = Integer.parseInt(ingreso);
			filas = cNeg.eliminarCliente(dni);
			cuNeg.bajarCuentasDeUsuario(dni);
			uNeg.bajaUsuario(dni);
			
			if(filas!=0) {
				
				
				request.setAttribute("mensajeEliminar", "Cliente Eliminado.");
				request.getRequestDispatcher("VentanaBajaCliente.jsp").forward(request, response);
			}
			else {
				request.setAttribute("mensajeEliminar", "El cliente no se encuentra en la base de datos");
				request.getRequestDispatcher("VentanaBajaCliente.jsp").forward(request, response);
			}
		}
	}

}
