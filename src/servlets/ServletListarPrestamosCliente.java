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
import entidad.Prestamos;
import entidad.Transferencias;
import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocio.PrestamoNeg;
import negocio.TransferenciaNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;
import negocioImp.PrestamoNegImpl;
import negocioImp.TransferenciaNegImpl;

/**
 * Servlet implementation class ServletListarPrestamosCliente
 */
@WebServlet("/ServletListarPrestamosCliente")
public class ServletListarPrestamosCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListarPrestamosCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNegImpl cli = new ClienteNegImpl();
		CuentasNeg cueNeg = new CuentasNegImpl();
		ArrayList<Cuentas> listaCuentas = new ArrayList<>();
		HttpSession sesion = request.getSession(false); 
	    String usuarioSesion = (sesion != null) ? (String) sesion.getAttribute("nombreUsuario") : null;
	    Cliente cliente = cli.obtenerClientesPorUsuarioObj(usuarioSesion);
		int dni = cliente.getDni();
	    
		PrestamoNeg prestNeg = new PrestamoNegImpl();
        ArrayList<Prestamos> listaPrestamos = prestNeg.obtenerPrestamosCliente(dni);
        request.setAttribute("listaPrestamos", listaPrestamos);
        
        listaCuentas = cueNeg.ObtenerCuentasPorDni(cliente.getDni());
        request.setAttribute("listaCuentas", listaCuentas);
        CuentasNegImpl cue = new CuentasNegImpl();
        Cuentas cuentas = cue.ObtenerCuentasPorDniobj(dni);
        int cuenta = cuentas.getNumeroCuenta();
 		System.out.println("Cuenta del cliente: " + cuenta);
        
		request.getRequestDispatcher("VentanaTranseferencia.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TransferenciaNeg tran = new TransferenciaNegImpl();
		ArrayList <Transferencias> listaCuentasTran = new ArrayList<>();
        CuentasNegImpl cue = new CuentasNegImpl();
		String cbuSeleccionado = request.getParameter("filtroCBU");
 		System.out.println("CBU: " + cbuSeleccionado);
 		int numCue = Integer.parseInt(cbuSeleccionado);
		listaCuentasTran = tran.ObtenerTransferenciasSegunCuenta(numCue);
		request.setAttribute("listaTransferencias", listaCuentasTran);
		
		///Vuelvo a cargar el select
		ArrayList<Cuentas> listaCuentas = new ArrayList<>();
		ClienteNeg cNeg = new ClienteNegImpl();
			
		HttpSession session = request.getSession(); 
		    
		Cliente cliente = cNeg.obtenerClientesPorUsuarioObj((String) session.getAttribute("nombreUsuario"));
		    
		listaCuentas = cue.ObtenerCuentasPorDni(cliente.getDni());
		    
		    
		    
	    request.setAttribute("listaCuentas", listaCuentas);
		
	    //vuelvo a cargar la primer tabla
	    
	    int dni = cliente.getDni();
	    
		PrestamoNeg prestNeg = new PrestamoNegImpl();
        ArrayList<Prestamos> listaPrestamos = prestNeg.obtenerPrestamosCliente(dni);
        request.setAttribute("listaPrestamos", listaPrestamos);
		
		request.getRequestDispatcher("VentanaTranseferencia.jsp").forward(request, response);
		
	}

}
