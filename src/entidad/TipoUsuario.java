package entidad;

public class TipoUsuario {
	private int IDTipoUsuario;
	private String descripcion;
	
	public TipoUsuario(int iDTipoUsuario, String descripcion) {
		IDTipoUsuario = iDTipoUsuario;
		this.descripcion = descripcion;
	}

	public int getIDTipoUsuario() {
		return IDTipoUsuario;
	}

	public void setIDTipoUsuario(int iDTipoUsuario) {
		IDTipoUsuario = iDTipoUsuario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
