package entidad;

import java.util.Date;

public class Localidad {
    private int idLocalidad;
    private String nombre;
    private Provincia provincia;
    
    

    // Constructor
    public Localidad(int idLocalidad, String nombre, Provincia provincia) {
        this.idLocalidad = idLocalidad;
        this.nombre = nombre;
        this.provincia = provincia;
    }
    
    

    public Localidad() {
    	
	}

	// Getters y Setters
    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }
}
