package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Cuota;
import negocioImp.CuotaNegImpl;

@WebServlet("/ServletVerCuotas")
public class ServletVerCuotas extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletVerCuotas() {
        super();
        // TODO Auto-generated constructor stub
    }
    // Instancia del negocio o DAO
    private CuotaNegImpl cuotasNeg = new CuotaNegImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del préstamo desde el parámetro
        String idPrestamoStr = request.getParameter("idPrestamo");

        if (idPrestamoStr != null) {
            try {
                int idPrestamo = Integer.parseInt(idPrestamoStr);

                // Obtener la lista de cuotas asociadas
                List<Cuota> cuotas = cuotasNeg.obtenerCuotasPorPrestamo(idPrestamo);

                // Pasar la lista de cuotas al JSP
                request.setAttribute("listaCuotas", cuotas);

                // Redirigir al JSP para mostrar las cuotas
                RequestDispatcher dispatcher = request.getRequestDispatcher("VerCuotas.jsp");
                dispatcher.forward(request, response);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp"); // Página de error
            }
        } else {
            response.sendRedirect("error.jsp"); // Página de error
        }
    }
}
