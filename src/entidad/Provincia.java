package entidad;

public class Provincia {
	private int IDProvincia;
	private String nombre;
	
	
	public Provincia(int iDProvincia, String nombre) {
		IDProvincia = iDProvincia;
		this.nombre = nombre;
	}
	
	public Provincia() {

	}
	
	public int getIDProvincia() {
		return IDProvincia;
	}
	public void setIDProvincia(int iDProvincia) {
		IDProvincia = iDProvincia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
