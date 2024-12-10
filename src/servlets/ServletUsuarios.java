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
import entidad.Usuario;
import negocio.ClienteNeg;
import negocio.UsuariosNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.UsuariosNegImpl;

@WebServlet("/ServletUsuarios")
public class ServletUsuarios extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    public ServletUsuarios() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuariosNeg usuaN = new UsuariosNegImpl();
        Usuario obj = new Usuario();

        obj.setUsuario(request.getParameter("txtUsuario"));
        obj.setContraseña(request.getParameter("txtContraseña"));
        
        // Verifica que los campos no estén vacíos
        if (obj.getUsuario() == null || obj.getUsuario().isEmpty() || obj.getContraseña() == null || obj.getContraseña().isEmpty()) {
            request.setAttribute("mensaje", "Usuario y contraseña son obligatorios.");
            request.getRequestDispatcher("VentanaInicioSesion.jsp").forward(request, response);
            return;
        }

        Usuario nuevobj = usuaN.BuscarUsuario(obj);

        if (nuevobj == null) {
            request.setAttribute("mensaje", "Datos Incorrectos.");
            request.getRequestDispatcher("VentanaInicioSesion.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("nombreUsuario", request.getParameter("txtUsuario"));

            if (nuevobj.getTipoUsuario() == 1) {
                request.getRequestDispatcher("VentanaUsuarioBanco.jsp").forward(request, response);
            } else if (nuevobj.getTipoUsuario() == 2) {
                request.getRequestDispatcher("UsuarioCliente.jsp").forward(request, response);
            }
        }
    
    
    
    }
}

