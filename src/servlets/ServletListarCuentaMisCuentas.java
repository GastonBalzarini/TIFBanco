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
import entidad.Cuentas;
import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;

/**
 * Servlet implementation class ServletListarCuentaMisCuentas
 */
@WebServlet("/ServletListarCuentaMisCuentas")
public class ServletListarCuentaMisCuentas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarCuentaMisCuentas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		CuentasNeg cueNeg = new CuentasNegImpl();
		ArrayList<Cuentas> listaCuentas = new ArrayList<>();
		ClienteNeg cNeg = new ClienteNegImpl();
		
		HttpSession session = request.getSession(); 
	    
	    Cliente cliente = cNeg.obtenerClientesPorUsuarioObj((String) session.getAttribute("nombreUsuario"));
	    
	    listaCuentas = cueNeg.ObtenerCuentasPorDni(cliente.getDni());
	    
	    
	    
	    request.setAttribute("listaCuentas", listaCuentas);
	    request.getRequestDispatcher("VentanaMisCuentas.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cbuSeleccionado = request.getParameter("filtroCuenta");
		CuentasNeg cueNeg = new CuentasNegImpl();
		ArrayList <Cuentas> listaCuentasCBU = new ArrayList<>();	    
	    listaCuentasCBU = cueNeg.ObtenerCuentasPorNumCue(cbuSeleccionado);	    
	    request.setAttribute("listaCuentasCBU", listaCuentasCBU);
	    
	    //Cargo de nuevo el select
	    ArrayList<Cuentas> listaCuentas = new ArrayList<>();
		ClienteNeg cNeg = new ClienteNegImpl();
		
		HttpSession session = request.getSession(); 
	    
	    Cliente cliente = cNeg.obtenerClientesPorUsuarioObj((String) session.getAttribute("nombreUsuario"));
	    
	    listaCuentas = cueNeg.ObtenerCuentasPorDni(cliente.getDni());
	    
	    
	   
	    request.setAttribute("listaCuentas", listaCuentas);
	    request.getRequestDispatcher("VentanaMisCuentas.jsp").forward(request, response);
	
	}

}