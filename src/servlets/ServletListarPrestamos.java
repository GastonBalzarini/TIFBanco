package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.CondicionPrestamos;
import entidad.Prestamos;
import negocio.CondicionPrestamosNeg;
import negocio.PrestamoNeg;
import negocioImp.CondicionPrestamosNegImpl;
import negocioImp.PrestamoNegImpl;

@WebServlet("/ServletListarPrestamos")
public class ServletListarPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletListarPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CondicionPrestamosNeg cpNeg = new CondicionPrestamosNegImpl();
		PrestamoNeg pNeg = new PrestamoNegImpl();
		ArrayList<CondicionPrestamos> listaCon = new ArrayList<>();
		ArrayList<Prestamos> listPrestamos = new ArrayList<>();
		
		listaCon = cpNeg.obtenerCondiciones();
		listPrestamos = pNeg.obtenerTodosLosPrestamos();
		
		request.setAttribute("listaCondicion", listaCon);
		request.setAttribute("listaPrestamos", listPrestamos);
		request.getRequestDispatcher("VentanaListarPrestamoBanco.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CondicionPrestamosNeg cpNeg = new CondicionPrestamosNegImpl();
		PrestamoNeg pNeg = new PrestamoNegImpl();
		ArrayList<CondicionPrestamos> listaCon = new ArrayList<>();
		ArrayList<Prestamos> listPrestamos = new ArrayList<>();
		
		if(request.getParameter("filtroCon")!="Todos") {
			int con = Integer.parseInt(request.getParameter("filtroCon"));
			
			listPrestamos = pNeg.ObtenerPretamosPorCondicion(con);
			listaCon = cpNeg.obtenerCondiciones();
			
			request.setAttribute("listaCondicion", listaCon);
			request.setAttribute("listaPrestamos", listPrestamos);
			request.getRequestDispatcher("VentanaListarPrestamoBanco.jsp").forward(request, response);
			
		}
		listaCon = cpNeg.obtenerCondiciones();
		listPrestamos = pNeg.obtenerTodosLosPrestamos();
		
		request.setAttribute("listaCondicion", listaCon);
		request.setAttribute("listaPrestamos", listPrestamos);
		request.getRequestDispatcher("VentanaListarPrestamoBanco.jsp").forward(request, response);
	}

}
