package servlets;

import java.io.IOException;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImp.CuotaDaoImpl;
import entidad.Cuota;
import negocio.CuotaNeg;
import negocioImp.CuotaNegImpl;
import negocioImp.PrestamoNegImpl;
import negocio.PrestamoNeg;

@WebServlet("/ServletPagarCuota")
public class ServletPagarCuota extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
        int idCuota = Integer.parseInt(request.getParameter("idCuota"));
        double monto = Double.parseDouble(request.getParameter("monto"));
        CuotaNegImpl cuNeg = new CuotaNegImpl();

        // Lógica para descontar el monto de la cuenta del usuario y actualizar el estado de la cuota
        CuotaDaoImpl cuotaDao = new CuotaDaoImpl();
        boolean exito = cuotaDao.pagarCuota(idCuota,idPrestamo, monto, request);  // Se pasa request para manejar mensajes
       
        int cantCuotasPagadas = cuNeg.contarCuotasPagas(idPrestamo);
        
        List<Cuota> cuotas = cuNeg.obtenerCuotasPorPrestamo(idPrestamo);
        int cantCuotas =0;
        cantCuotas = cuotas.size();
        
        System.out.println("Cantidad de cuotas a pagar "+cantCuotas);
        System.out.println("Cantidad de cuotas pagadas "+cantCuotasPagadas);
        
        PrestamoNeg pNeg = new PrestamoNegImpl();
        if (cantCuotasPagadas == cantCuotas) {
        	pNeg.marcarPrestamoComoPago(idPrestamo);
        	//FUncion que guarga condicion pagada en prestamo
        }
        
        if (exito) {
            request.setAttribute("mensajeExito", "La cuota ha sido pagada exitosamente.");
        }

        request.getRequestDispatcher("VerCuotas.jsp").forward(request, response);
    }
}
