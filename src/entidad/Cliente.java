package entidad;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import entidad.Nacionalidad;
import excepciones.BuscarDniConLetrasException;
import excepciones.BuscarNombreConNumerosException;
import excepciones.ValidarTextosException;

public class Cliente {
    private int dni;
    private String cuil;
    private String nombre;
    private String apellido;
    private Sexo sexo;                  // Clase Sexo
    private Nacionalidad nacionalidad;   // Clase Nacionalidad
    private Date fechaNacimiento;
    private String direccion;
    private Provincia provincia;
    private Localidad localidad;         // Clase Localidad
    private String correoElectronico;
    private String telefono;
    private Usuario usuario;             // Clase Usuario

    // Constructor
    public Cliente() {
        
    }
    
    public Cliente(int dni) {
    	this.dni = dni;
        this.sexo = new Sexo();                  
        this.nacionalidad = new Nacionalidad();  
        this.localidad = new Localidad();        
        this.provincia = new Provincia();        
        this.usuario = new Usuario();            
    }



	// Getters y Setters
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
    
    public static boolean validarDNI (String dni) throws BuscarDniConLetrasException{
		
    	Boolean auxLetra = false;
    	
    	for (int i= 0; i<dni.length(); i++) {
    		if(Character.isLetter(dni.charAt(i))) {
    			auxLetra = true;
    		}
    	}
    	
    	if (auxLetra == true) {
    		BuscarDniConLetrasException exc1 = new BuscarDniConLetrasException();
    		throw exc1;
    	}
    	
    	
    	
    	return false;
    	
    	
    	
    }
   
    public static boolean validarNombre (String nombre) throws BuscarNombreConNumerosException{
		
    	Boolean auxLetra = false;
    	
    	for (int i= 0; i<nombre.length(); i++) {
    		if(Character.isDigit(nombre.charAt(i))) {
    			auxLetra = true;
    		}
    	}
    	
    	if (auxLetra == true) {
    		BuscarNombreConNumerosException exc1 = new BuscarNombreConNumerosException();
    		throw exc1;
    	}
    	
    	
    	
    	return false;
    }
    
    public static boolean validarTexto(String texto, String tipo, HttpServletRequest request) {
        try {
            if (texto == null || texto.trim().isEmpty()) {
                throw new IllegalArgumentException("El texto no puede estar vacio.");
            }
            for (int i = 0; i < texto.length(); i++) {
                if (Character.isDigit(texto.charAt(i))) {
                    throw new ValidarTextosException("El texto no debe contener numeros.");
                }
            }
            return true; // Texto valido.
        } catch (ValidarTextosException | IllegalArgumentException e) {
            request.setAttribute(tipo + "Error", e.getMessage());
            return false; // Texto no valido.
        }
    }

}
