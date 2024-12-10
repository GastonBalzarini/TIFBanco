package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionListener;

import entidad.Cliente;
import entidad.Cuentas;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Usuario;
import negocio.ClienteNeg;
import negocio.CuentasNeg;
import negocio.LocalidadNeg;
import negocio.NacionalidadNeg;
import negocio.ProvinciaNeg;
import negocio.SexoNeg;
import negocio.UsuariosNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.CuentasNegImpl;
import negocioImp.LocalidadNegImpl;
import negocioImp.NacionalidadNegImpl;
import negocioImp.ProvinciaNegImpl;
import negocioImp.SexoNegImpl;
import negocioImp.UsuariosNegImpl;

/**
 * Servlet implementation class ServletModificarCliente
 */
@WebServlet("/ServletModificarCliente")
public class ServletModificarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletModificarCliente() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		/*String dniStr = request.getParameter("dni");*/
		String dniStr = (String) session.getAttribute("DNIaBuscar");
		
		// Verificar que el DNI no sea null o vacío
	    if (dniStr == null || dniStr.isEmpty()) {
	        request.setAttribute("mensaje", "El DNI es obligatorio.");
	        request.getRequestDispatcher("VentanaBuscarClientMod.jsp").forward(request, response);
	    }

	    // Convertir el DNI a int
	    int dni = Integer.parseInt(dniStr);

        ClienteNeg cNeg = new ClienteNegImpl();
        Cliente obj = new Cliente();
    	obj.setDni(dni);

    	Cliente obj2 = cNeg.BuscarCliente(obj);

        request.setAttribute("cliente", obj2);
        request.setAttribute("dni", dniStr);
        
        
        
      ///Creamos objetos de los negocios
      SexoNeg sNeg = new SexoNegImpl();
      NacionalidadNeg nNeg = new NacionalidadNegImpl();
      ProvinciaNeg pNeg = new ProvinciaNegImpl();
      LocalidadNeg lNeg = new LocalidadNegImpl();
      
      ///Creamos los Array donde estaran almacenados los datos para los desplegables
      ArrayList<Sexo> listaSexos = new ArrayList<Sexo>();
      ArrayList<Nacionalidad> listaNacionalidad = new ArrayList<>();
      ArrayList<Provincia> listaProvincia = new ArrayList<>();
      ArrayList<Localidad> listaLocalidad = new ArrayList<>();
      // Obtenemos la listas desde la base de datos
      listaSexos = sNeg.obtenerSexos(); 
      listaNacionalidad = nNeg.ObtenerNacionalidad();
      listaProvincia = pNeg.obtenerProvincias();
      listaLocalidad = lNeg.obtenerLocalidades(obj2.getProvincia().getIDProvincia());

      // Pasamos la listas como un atributo para el JSP
      request.setAttribute("listaSexos", listaSexos);
      request.setAttribute("listaNac", listaNacionalidad);
      request.setAttribute("listaPro", listaProvincia);
      request.setAttribute("listaLoc", listaLocalidad);

      // Enviamos la solicitud al JSP para mostrar el formulario

      request.getRequestDispatcher("VentanaModCliente.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNeg cNeg = new ClienteNegImpl();
		UsuariosNeg uNeg = new UsuariosNegImpl();
		SexoNeg sNeg = new SexoNegImpl();
	    NacionalidadNeg nNeg = new NacionalidadNegImpl();
	    ProvinciaNeg pNeg = new ProvinciaNegImpl();
	    LocalidadNeg lNeg = new LocalidadNegImpl();
		
		Cliente cliente = new Cliente();
		Cliente obj2 = new Cliente();
		Sexo sex = new Sexo();
		Nacionalidad nac = new Nacionalidad();
		Provincia pro = new Provincia();
		Localidad loc = new Localidad();
		Usuario usu= new Usuario();
		
		String dniStr = request.getParameter("dni");
		int dni = Integer.parseInt(dniStr);
		cliente.setDni(dni);
		
		String nombre = request.getParameter("nombre");
		 if (Cliente.validarTexto(nombre, "nombre", request)) {
			    cliente.setNombre(nombre); // Asignar nombre si es valido.
			}
		 
		 String apellido = request.getParameter("apellido");
		 if (Cliente.validarTexto(apellido, "apellido", request)) {
			    cliente.setApellido(apellido); // Asignar apellido si es valido.
			} 
		
		cliente.setCuil(request.getParameter("cuil"));
		cliente.setCorreoElectronico(request.getParameter("email"));
		String fechaNacimientoStr = request.getParameter("fechaNacimiento");
	    if (fechaNacimientoStr != null && !fechaNacimientoStr.trim().isEmpty()) {
	    
	    	java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
	        cliente.setFechaNacimiento(fechaNacimiento);
	    }
	    cliente.setDireccion(request.getParameter("Direccion"));
		cliente.setTelefono(request.getParameter("telefono"));
		int sexo = Integer.parseInt(request.getParameter("sexo"));
	    sex.setId(sexo);
	    cliente.setSexo(sex);
	    
	    int nacionalidad = Integer.parseInt(request.getParameter("nacionalidad"));
	    nac.setId(nacionalidad);
	    cliente.setNacionalidad(nac);
	    
	    int idprovincia = Integer.parseInt(request.getParameter("filtroProvincia"));
	    String provincia = (request.getParameter("filtroProvincia"));
	    pro.setIDProvincia(idprovincia);
	    cliente.setProvincia(pro);
	    
	    String Local = request.getParameter("Localidad");
	    if (Local != null && !Local.isEmpty()) {
	    	int localidad = Integer.parseInt(Local);
	    	loc.setIdLocalidad(localidad);
	    	cliente.setLocalidad(loc);
        }
	    String usuario = request.getParameter("usuario");
	    usu.setUsuario(request.getParameter("usuario"));
	    
	    String nuevaContrasena = request.getParameter("contrasena");
	    if (nuevaContrasena != null && !nuevaContrasena.trim().isEmpty()) {
	    	usu.setContraseña(request.getParameter("contrasena"));
	    } else {
	    	 Usuario usuarioActual = uNeg.BuscarContraseña(usuario); //obtener usuario actual
	    	 usu.setContraseña(usuarioActual.getContraseña());

	    }
	    
	    cliente.setUsuario(usu);
		
		if(request.getParameter("btnModificar")!=null) {
			
		    int filasUsu = uNeg.modificarUsuario(usu);
		    
		    int filas = cNeg.modificarCliente(cliente);
		    if(filas!=0) {
		    	request.setAttribute("mensaje", "Cliente Modificado.");
				request.getRequestDispatcher("VentanaBuscarClientMod.jsp").forward(request, response);
			}
			
		}
		
		
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setApellido(request.getParameter("apellido"));
		
		ArrayList<Sexo> listaSexos = sNeg.obtenerSexos();
	    ArrayList<Nacionalidad> listaNacionalidad = nNeg.ObtenerNacionalidad();
	    ArrayList<Provincia> listaProvincia = pNeg.obtenerProvincias();
	    ArrayList<Localidad> listaLocalidad = (provincia != null && !provincia.isEmpty()) ? lNeg.obtenerLocalidades(Integer.parseInt(provincia)) : new ArrayList<>();

	    // Pasar las listas al JSP
	    request.setAttribute("listaSexos", listaSexos);
	    request.setAttribute("listaNac", listaNacionalidad);
	    request.setAttribute("listaPro", listaProvincia);
	    request.setAttribute("listaLoc", listaLocalidad);
	    request.setAttribute("cliente", cliente);
	    
	 // Redirigir al JSP
	    request.getRequestDispatcher("VentanaModCliente.jsp").forward(request, response);
	}

}
