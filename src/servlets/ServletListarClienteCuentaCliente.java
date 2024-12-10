package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import negocio.ClienteNeg;
import negocioImp.ClienteNegImpl;

/**
 * Servlet implementation class ServletListarClienteCuentaCliente
 */
@WebServlet("/ServletListarClienteCuentaCliente")
public class ServletListarClienteCuentaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarClienteCuentaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String usuario = (session != null) ? (String) session.getAttribute("nombreUsuario") : null;
		ClienteNeg clienteNeg = new ClienteNegImpl();
		ArrayList<Cliente> listaClientes = clienteNeg.obtenerClientesPorUsuario(usuario);
		System.out.println("Lista de clientes: " + listaClientes);  
		request.setAttribute("listaClientes", listaClientes); 
		request.getRequestDispatcher("UsuarioCliente.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
        session.setAttribute("nombreUsuario", request.getParameter("txtUsuario"));
				
	}

}
