package servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImp.ProvinciaDaoImpl;
import daoImp.LocalidadDaoImpl;
import entidad.Cliente;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Usuario;
import excepciones.BuscarNombreConNumerosException;
import negocio.ClienteNeg;
import negocio.LocalidadNeg;
import negocio.NacionalidadNeg;
import negocio.ProvinciaNeg;
import negocio.SexoNeg;
import negocio.UsuariosNeg;
import negocioImp.ClienteNegImpl;
import negocioImp.LocalidadNegImpl;
import negocioImp.NacionalidadNegImpl;
import negocioImp.ProvinciaNegImpl;
import negocioImp.SexoNegImpl;
import negocioImp.UsuariosNegImpl;

@WebServlet("/ServletAgregarCliente")
public class ServletAgregarCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletAgregarCliente() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        // Pasamos la listas como un atributo para el JSP
        request.setAttribute("listaSexos", listaSexos);
        request.setAttribute("listaNac", listaNacionalidad);
        request.setAttribute("listaPro", listaProvincia);
        
        String IdProvincia = request.getParameter("filtroProvincia");
        if (IdProvincia != null && !IdProvincia.isEmpty()) {
        	int IdPro = Integer.parseInt(IdProvincia);
        	listaLocalidad = lNeg.obtenerLocalidades(IdPro);
        	request.setAttribute("listaLoc", listaLocalidad);
        }
        

        // Enviamos la solicitud al JSP para mostrar el formulario
        request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ClienteNeg cNeg = new ClienteNegImpl();
		UsuariosNeg uNeg = new UsuariosNegImpl();
		
		
		if(request.getParameter("AgregarCliente")!=null) {
			String contrasena = request.getParameter("contrasena");
			String contrasenaRepetir = request.getParameter("contrasenaRepetir");
			int verificarDNI = Integer.parseInt(request.getParameter("dni"));
			 String usuario = request.getParameter("usuario");
			   
			if (contrasena == null || contrasenaRepetir == null || !contrasena.equals(contrasenaRepetir)) {
			    request.setAttribute("errorContrasena", "Las contraseñas no coinciden.");
			}
			else if (cNeg.existeDNI(verificarDNI)) {
				request.setAttribute("errorDNI", "El DNI ya se encuentra en la base de datos.");
			} else if (uNeg.existeUsuario(usuario)) {
	            request.setAttribute("errorUsuario", "El nombre de usuario ya está en uso.");
	        } else {
			Cliente obj = new Cliente();
			Sexo sex = new Sexo();
			Nacionalidad nac = new Nacionalidad();
			Provincia pro = new Provincia();
			Localidad loc = new Localidad();
			Usuario usu= new Usuario();
			
			// obtenemos los datos que se llenaron
			///String nombrePattern = "^[a-zA-Z\\s]+$";
			 String nombre = request.getParameter("nombre");
			 if (Cliente.validarTexto(nombre, "nombre", request)) {
				    obj.setNombre(nombre); // Asignar nombre si es valido.
				}
			 
			 String apellido = request.getParameter("apellido");
			 if (Cliente.validarTexto(apellido, "apellido", request)) {
				    obj.setApellido(apellido); // Asignar apellido si es valido.
				}     
		    

		    obj.setCorreoElectronico(request.getParameter("email"));
		    int dni = Integer.parseInt(request.getParameter("dni"));
		    obj.setDni(dni);
		    obj.setCuil(request.getParameter("cuil"));
		 
		    String fechaNacimientoStr = request.getParameter("fechaNacimiento");
		    if (fechaNacimientoStr != null && !fechaNacimientoStr.trim().isEmpty()) {
		    
		    	java.sql.Date fechaNacimiento = java.sql.Date.valueOf(fechaNacimientoStr);
		        obj.setFechaNacimiento(fechaNacimiento);
		    }
		    
		    
		    obj.setDireccion(request.getParameter("Direccion"));
		    obj.setTelefono(request.getParameter("telefono"));
		    int sexo = Integer.parseInt(request.getParameter("sexo"));
		    sex.setId(sexo);
		    obj.setSexo(sex);
		    
		    int nacionalidad = Integer.parseInt(request.getParameter("nacionalidad"));
		    nac.setId(nacionalidad);
		    obj.setNacionalidad(nac);
		    
		    int idprovincia = Integer.parseInt(request.getParameter("filtroProvincia"));
		    pro.setIDProvincia(idprovincia);
		    obj.setProvincia(pro);
		    int localidad = Integer.parseInt(request.getParameter("Localidad"));
		    loc.setIdLocalidad(localidad);
		    obj.setLocalidad(loc);
		    
		    usu.setUsuario(request.getParameter("usuario"));
		    usu.setContraseña(request.getParameter("contrasena"));
		    usu.setTipoUsuario(2);
		    
		    int filaUsuario = uNeg.agregarUsuario(usu);
		    int filasCliente = 0;
		    
		    if (filaUsuario != 0) {
	            // Ahora, agregamos el cliente
	            obj.setUsuario(usu);  
	            filasCliente = cNeg.agregarCliente(obj);

	            if (filasCliente != 0) {
	                request.setAttribute("mensajeExito", "Cliente y usuario agregados exitosamente.");
	            } else {
	                uNeg.EliminarUsuario(usu.getUsuario());
	                request.setAttribute("errorCliente", "Error al agregar el cliente. El usuario ha sido eliminado.");
	            }
	        } else {
	            request.setAttribute("errorUsuario", "Error al agregar el usuario.");
	        }
		    
		    String usuarioNombre = (request.getParameter("usuario"));
		    
		    if(filasCliente!=0) {
		    	request.setAttribute("mensajeExito", "Cliente agregado exitosamente.");
		    	SexoNeg sNeg = new SexoNegImpl();
			    NacionalidadNeg nNeg = new NacionalidadNegImpl();
			    ProvinciaNeg pNeg = new ProvinciaNegImpl();
			    LocalidadNeg lNeg = new LocalidadNegImpl();

			    ArrayList<Sexo> listaSexos = sNeg.obtenerSexos();
			    ArrayList<Nacionalidad> listaNacionalidad = nNeg.ObtenerNacionalidad();
			    ArrayList<Provincia> listaProvincia = pNeg.obtenerProvincias();
			    ArrayList<Localidad> listaLocalidad = new ArrayList<>();
			    String IdProvincia = request.getParameter("filtroProvincia");
		        if (IdProvincia != null && !IdProvincia.isEmpty()) {
		        	int IdPro = Integer.parseInt(IdProvincia);
		        	listaLocalidad = lNeg.obtenerLocalidades(IdPro);
		        	request.setAttribute("listaLoc", listaLocalidad);
		        }
			    // Pasar las listas al JSP
			    request.setAttribute("listaSexos", listaSexos);
			    request.setAttribute("listaNac", listaNacionalidad);
			    request.setAttribute("listaPro", listaProvincia);
			    request.setAttribute("listaLoc", listaLocalidad);
				request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
			}
		    uNeg.EliminarUsuario(usuarioNombre);
		}
		}
		// obtenemos los datos que se llenaron
	    String nombre = request.getParameter("nombre");
	    String apellido = request.getParameter("apellido");
	    String email = request.getParameter("email");
	    String dni = request.getParameter("dni");
	    String cuil = request.getParameter("cuil");
	    String fechaNacimiento = request.getParameter("fechaNacimiento");
	    String Direccion = request.getParameter("Direccion");
	    String telefono = request.getParameter("telefono");
	    String sexo = request.getParameter("sexo");
	    String nacionalidad = request.getParameter("nacionalidad");
	    String provincia = request.getParameter("filtroProvincia");
	    String localidad = request.getParameter("Localidad");
	    String usuario = request.getParameter("usuario");
	    String contrasena = request.getParameter("contrasena");
	    String contrasenaRepetir = request.getParameter("contrasenaRepetir");

	    // Luego los mandamos de guardar de vuelta a los inputs
	    request.setAttribute("nombre", nombre);
	    request.setAttribute("apellido", apellido);
	    request.setAttribute("email", email);
	    request.setAttribute("dni", dni);
	    request.setAttribute("cuil", cuil);
	    request.setAttribute("fechaNacimiento", fechaNacimiento);
	    request.setAttribute("Direccion", Direccion);
	    request.setAttribute("telefono", telefono);
	    request.setAttribute("sexoSeleccionado", sexo);
	    request.setAttribute("nacionalidadSeleccionada", nacionalidad);
	    request.setAttribute("provinciaSeleccionada", provincia);
	    request.setAttribute("localidadSeleccionada", localidad);
	    request.setAttribute("usuario", usuario);
	    request.setAttribute("contrasena", contrasena);
	    request.setAttribute("contrasenaRepetir", contrasenaRepetir);

	    // Obtener listas de sexos, nacionalidades, provincias y localidades para los descolgables.
	    SexoNeg sNeg = new SexoNegImpl();
	    NacionalidadNeg nNeg = new NacionalidadNegImpl();
	    ProvinciaNeg pNeg = new ProvinciaNegImpl();
	    LocalidadNeg lNeg = new LocalidadNegImpl();

	    ArrayList<Sexo> listaSexos = sNeg.obtenerSexos();
	    ArrayList<Nacionalidad> listaNacionalidad = nNeg.ObtenerNacionalidad();
	    ArrayList<Provincia> listaProvincia = pNeg.obtenerProvincias();
	    ArrayList<Localidad> listaLocalidad = (provincia != null && !provincia.isEmpty()) ? lNeg.obtenerLocalidades(Integer.parseInt(provincia)) : new ArrayList<>();

	    // Pasar las listas al JSP
	    request.setAttribute("listaSexos", listaSexos);
	    request.setAttribute("listaNac", listaNacionalidad);
	    request.setAttribute("listaPro", listaProvincia);
	    request.setAttribute("listaLoc", listaLocalidad);

	    // Redirigir al JSP
	    request.getRequestDispatcher("AgregarCliente.jsp").forward(request, response);
	}}
