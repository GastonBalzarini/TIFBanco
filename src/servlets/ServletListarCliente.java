package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cliente;
import entidad.Cuentas;
import excepciones.BuscarNombreConNumerosException;
import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;

/**
 * Servlet implementation class ServletListarCliente
 */
@WebServlet("/ServletListarCliente")
public class ServletListarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNeg clienteNeg = new ClienteNegImpl();
        ArrayList<Cliente> listaClientes = clienteNeg.obtenerClientes();
        request.setAttribute("listaClientes", listaClientes);
        request.getRequestDispatcher("VentanaListCliente.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BuscarNombreConNumerosException {
		
		String nombre = request.getParameter("buscarCliente");
		Cliente cli = new Cliente();
		try {
		cli.validarNombre(nombre);
		if(nombre !=null && !nombre.trim().isEmpty()) {
			ClienteNeg clienteNeg = new ClienteNegImpl();
			ArrayList <Cliente> listaClientes = clienteNeg.obtenerClientePorNombre(nombre);
			 if (listaClientes.isEmpty()) {
			        request.setAttribute("mensaje", "No se encontraron clientes.");
			    } else {
			        request.setAttribute("listaClientes", listaClientes);
			    }
	        request.getRequestDispatcher("VentanaListCliente.jsp").forward(request, response);
			}
		}catch(BuscarNombreConNumerosException e){
			request.setAttribute("mensaje", e.getMessage());
			 request.getRequestDispatcher("VentanaListCliente.jsp").forward(request, response);
		}
	}
}
