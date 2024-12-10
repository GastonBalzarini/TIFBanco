package negocioImp;

import entidad.Usuario;
import negocio.UsuariosNeg;
import dao.UsuariosDao;
import daoImp.UsuariosDaoImpl;

public class UsuariosNegImpl implements UsuariosNeg{
	private UsuariosDao uDao = new UsuariosDaoImpl(); 
	
	public Usuario BuscarUsuario(Usuario obj) {
		
		return uDao.BuscarUsuario(obj);
	}

	@Override
	public int agregarUsuario(Usuario obj) {
		return uDao.agregarUsuario(obj);
	}

	@Override
	public int modificarUsuario(Usuario obj) {
		
		return uDao.modificarUsuario(obj);
	}

	public boolean existeUsuario(String usuario) {
	    
	    return uDao.existeUsuario(usuario); // Método que consulta en la base de datos.
	}

	@Override
	public Usuario BuscarContraseña(String usuario) {

		return uDao.BuscarContraseña(usuario);
	}

	@Override
	public int bajaUsuario(int dni) {
		return uDao.bajaUsuario(dni);
	}
	
	public boolean EliminarUsuario(String usuario) {
		return uDao.EliminarUsuario(usuario);
	}
	

}
