package entidad;

public class Usuario {
	private String Usuario;
	private String Contraseña;
	private int TipoUsuario;
	
	public Usuario() {
		this.Usuario = "";
		this.Contraseña = "";
		this.TipoUsuario = 0;
	}
	
	public Usuario(String usuario, String contraseña, int tipoUsuario) {
		Usuario = usuario;
		Contraseña = contraseña;
		TipoUsuario = tipoUsuario;
	}
	///set y gets
	public String getUsuario() {
		return Usuario;
	}
	public void setUsuario(String usuario) {
		Usuario = usuario;
	}
	public String getContraseña() {
		return Contraseña;
	}
	public void setContraseña(String contraseña) {
		Contraseña = contraseña;
	}
	public int getTipoUsuario() {
		return TipoUsuario;
	}
	public void setTipoUsuario(int tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}
	
	
	
	
}
