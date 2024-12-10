package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.Cliente;
import excepciones.BuscarDniConLetrasException;
import negocio.ClienteNeg;
import negocioImp.ClienteNegImpl;

@WebServlet("/ServletBuscarClienteMod")
public class ServletBuscarClienteMod extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ServletBuscarClienteMod() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BuscarDniConLetrasException {
		if (request.getParameter("Buscar") != null) {
            ClienteNeg cNeg = new ClienteNegImpl();
            Cliente cli = new Cliente();
            String DNI = request.getParameter("buscarCliente");

            try {
                cli.validarDNI(DNI);
                Cliente obj = new Cliente(Integer.parseInt(DNI));
                HttpSession session = request.getSession();
                session.setAttribute("DNIaBuscar", DNI);

                Cliente obj2 = cNeg.BuscarCliente(obj);

                if (obj2 == null) {
                    request.setAttribute("mensaje", "El DNI ingresado no existe");
                    request.getRequestDispatcher("VentanaBuscarClientMod.jsp").forward(request, response);
                } else {
                    request.setAttribute("cliente", obj2);
                    request.setAttribute("dni", DNI);
                    response.sendRedirect("ServletModificarCliente");
                }
            } catch (BuscarDniConLetrasException e) {
            	request.setAttribute("mensaje", e.getMessage());
            	request.getRequestDispatcher("VentanaBuscarClientMod.jsp").forward(request, response);
            }
        }
    }

}
