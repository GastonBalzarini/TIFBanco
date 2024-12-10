package daoImp;

import dao.UsuariosDao;
import entidad.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuariosDaoImpl implements UsuariosDao {

	private Conexion cn;
	
	public UsuariosDaoImpl() {
		
	}
	
	public Usuario BuscarUsuario(Usuario obj) {
		cn = new Conexion();
	    cn.Open();
	    Usuario reg = null; 
	    String query = "SELECT IDTipoDeUsuario FROM usuarios WHERE Usuario = ? AND Contraseña = ? AND estado = 1";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, obj.getUsuario());
	        pst.setString(2, obj.getContraseña());

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) { 
	            reg = new Usuario();
	            reg.setTipoUsuario(rs.getInt("IDTipoDeUsuario"));
	        }
	    } catch (Exception e) {
	        System.out.println("Error al buscar usuario: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return reg;
	}

	@Override
	public int agregarUsuario(Usuario obj) {
		cn = new Conexion();
		cn.Open(); 
		int filasAfectadas = 0;
		String query = "INSERT INTO Usuarios (Usuario,Contraseña,IDTipoDeUsuario) VALUES (?, ?, ?)"; 
		try (PreparedStatement pst = cn.connection.prepareStatement(query))
		{ 
		pst.setString(1, obj.getUsuario());
		pst.setString(2, obj.getContraseña()); 
		pst.setInt(3, obj.getTipoUsuario());
		
		filasAfectadas = pst.executeUpdate(); } 
		catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("Error al ingresar Usuario: " + e.getMessage());
		} 
		finally { cn.close(); }
		
		return filasAfectadas;
	}

	@Override
	public int modificarUsuario(Usuario obj) {
		cn = new Conexion();
	    cn.Open(); 
	    int filasAfectadas = 0;

	    String query = "UPDATE Usuarios SET Contraseña = ? WHERE Usuario = ?"; 

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, obj.getContraseña());     
	        pst.setString(2, obj.getUsuario());
	        
	        filasAfectadas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace(); 
	        System.out.println("Error al modificar Usuario: " + e.getMessage());
	    } finally {
	        cn.close();
	    }

	    return filasAfectadas;
	}
	  // Método para verificar si el nombre de usuario ya existe en la base de datos
    public boolean existeUsuario(String usuario) {
    	cn = new Conexion();
	    cn.Open(); 
	   	boolean existe = false;
        String sql = "SELECT COUNT(*) FROM Usuarios WHERE usuario = ?"; 
        try (PreparedStatement pst = cn.connection.prepareStatement(sql)) {
            
            // Asignar el valor del nombre de usuario al parámetro de la consulta
            pst.setString(1, usuario);
            
            // Ejecutar la consulta
            try (ResultSet rs = pst.executeQuery()) {
                // Si el conteo es mayor que 0, significa que el usuario ya existe
                if (rs.next()) {
                    existe = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return existe;
    }
    
    public Usuario BuscarContraseña(String usuario) {
		cn = new Conexion();
	    cn.Open();
	    Usuario reg = null; 
	    String query = "SELECT contraseña FROM usuarios WHERE Usuario = ?";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, usuario);

	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) { 
	            reg = new Usuario();
	            reg.setContraseña(rs.getString("contraseña"));
	        }
	    } catch (Exception e) {
	        System.out.println("Error al buscar usuario: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return reg;
	}
    
	public int bajaUsuario(int dni) {
	    cn = new Conexion();
	    cn.Open();
	    int filasAfectadas = 0;
	    String query = "UPDATE usuarios inner join clientes on usuarios.Usuario = clientes.Usuario SET usuarios.estado = 0 WHERE DNI = ?";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, dni); 
	        filasAfectadas = pst.executeUpdate(); 
	    } catch (Exception e) {
	        System.out.println("Error al actualizar el estado del usuario: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return filasAfectadas; 
	}

	public boolean EliminarUsuario(String usuario) {
	    cn = new Conexion();
	    cn.Open();
	    int filasAfectadas = 0;
	    boolean eliminado = false;  
	    String query = "DELETE FROM usuarios WHERE Usuario = ?";  

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, usuario); 
	        filasAfectadas = pst.executeUpdate();  
	        if (filasAfectadas > 0) {
	            eliminado = true;
	        }
	    } catch (Exception e) {
	        System.out.println("Error al eliminar el usuario: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return eliminado;  
	}
	
	
}
