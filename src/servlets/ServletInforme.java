package servlets;



import negocio.InformesNeg;
import negocioImp.InformesNegImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/ServletInforme")
public class ServletInforme extends HttpServlet {


    public ServletInforme() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Establece el tipo de contenido de la respuesta
        //response.setContentType("text/html;charset=UTF-8");
        
        InformesNeg iNeg = new InformesNegImpl();

        // Obtiene los parámetros de fecha
        String fechaInicio = request.getParameter("fechaInicio");
        String fechaFin = request.getParameter("fechaFin");

        try {
            // Obtiene los informes estadísticos
            //Map<String, Integer> informes = informeDao.obtenerInformesEstadisticos(fechaInicio, fechaFin);
            Map<String, Integer> informes = iNeg.obtenerInformesEstadisticos(fechaInicio, fechaFin);

            // Agrega los informes al request para ser accedidos en el JSP
            request.setAttribute("informes", informes);

            // Redirige al JSP para mostrar los informes
            RequestDispatcher dispatcher = request.getRequestDispatcher("VentanaUsuarioBanco.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Ocurrió un error al procesar la solicitud.");
        }
    }
}