package servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import entidad.Cuentas;
import entidad.Transferencias;
import negocio.TransferenciaNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;
import negocioImp.TransferenciaNegImpl;


@WebServlet("/ServletRealizarTransferencia")
public class ServletRealizarTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletRealizarTransferencia() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false); 
	    String usuarioSesion = (sesion != null) ? (String) sesion.getAttribute("nombreUsuario") : null;
	    
	    ClienteNegImpl cli = new ClienteNegImpl();
	    Cliente cliente = cli.obtenerClientesPorUsuarioObj(usuarioSesion);
		int dni = cliente.getDni();
		
		CuentasNegImpl cue = new CuentasNegImpl();
		ArrayList<Cuentas> listaCuentas = new ArrayList<>();
        listaCuentas = cue.ObtenerCuentasPorDni(dni);
        
        String success = request.getParameter("success");
        String error = request.getParameter("error");
        if (success != null) {
            request.setAttribute("success", success);
        }
        
        if (error != null) {
            request.setAttribute("error", error);
        }
        
        request.setAttribute("listaCuentas", listaCuentas);
        request.getRequestDispatcher("VentanaRealizarTransferencia.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesion = request.getSession(false); 
	    String usuarioSesion = (sesion != null) ? (String) sesion.getAttribute("nombreUsuario") : null;
		CuentasNegImpl cuNeg = new CuentasNegImpl();

		Cuentas cuentaO = cuNeg.ObtenerCuentaPorUsuario(usuarioSesion); 
		Transferencias trans = new Transferencias();
		TransferenciaNegImpl tNeg = new TransferenciaNegImpl();
		
		//ObtenerCuentaPorUsuario(usuarioSesion)
		///String CBUOrigen = String.valueOf(cuentaO.getCbu());
		///int cuentaOrigen = cuentaO.getNumeroCuenta();
		String numeroCuenta = request.getParameter("filtroCuenta");
		int cuentaOrigen = Integer.parseInt(numeroCuenta);
		Cuentas c = cuNeg.obtenerCuentaPorNumero(cuentaOrigen);
		
		
		
		// CBU de la cuenta destino, se obtiene del input
		String CBUDestino = String.valueOf(request.getParameter("CBUDestino"));
		Cuentas cuentaD = cuNeg.obtenerNroCuentaPorCBU(CBUDestino);
		if (cuentaD == null) {
			response.sendRedirect("ServletRealizarTransferencia?error=No se encontro la cuenta destino");
			return;
		}
		int cuentaDestino = cuentaD.getNumeroCuenta();
		float montoTrans = Float.parseFloat(request.getParameter("transferencia"));
		float saldo = cuentaO.getSaldo();
		System.out.println(saldo);
	    // Validar saldo suficiente
	    if (cuentaO.getSaldo() < montoTrans) {
	        response.sendRedirect("ServletRealizarTransferencia?error=Saldo insuficiente para realizar la transferencia");
	        return;
	    }
	    
	    ///Validar que el monto no sea negativo 
	    if (montoTrans <= 0) {
	        response.sendRedirect("ServletRealizarTransferencia?error=No se acepta montos negativos");
	        return;
	    }
	    
	    // Registrar transferencia
	    trans.setCuentaOrigen(cuentaOrigen);
		trans.setCuentaDestino(cuentaDestino);
		trans.setFecha(new Date(System.currentTimeMillis()));
		trans.setImporte(montoTrans);
		tNeg.agregarTransferencia(trans);
		
		//Modificar saldos
		cuNeg.modificarSaldo(c.getCbu(),-montoTrans);
		cuNeg.modificarSaldo(CBUDestino,montoTrans);
		
		System.out.println("usuario " + usuarioSesion);

		request.setAttribute("success", "Transferencia realizada con éxito.");
		///request.getRequestDispatcher("VentanaRealizarTransferencia.jsp").forward(request, response);
		response.sendRedirect("ServletRealizarTransferencia?success=Transferencia realizada con exito");
	}

}
