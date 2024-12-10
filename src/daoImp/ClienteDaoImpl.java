package daoImp;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ClienteDao;
import entidad.Cliente;
import entidad.Cuentas;
import entidad.Localidad;
import entidad.Nacionalidad;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Usuario;

public class ClienteDaoImpl implements ClienteDao{

	private Conexion cn;
	
	public ClienteDaoImpl() {
		
	}
	
	public ArrayList<Cliente> obtenerClientes() {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cliente> listCli = new ArrayList<>();
		String query = "SELECT DNI,CUIL,Nombre,Apellido,FechaNacimiento,Direccion,nacionalidad.Descripcion,sexo.descripcion,CorreoElectronico,Telefono FROM clientes INNER JOIN nacionalidad ON clientes.IDNacionalidad = nacionalidad.IDNacionalidad INNER JOIN sexo on clientes.Sexo = sexo.id WHERE estado = 1";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setDni(rs.getInt("DNI"));
				cli.setCuil(rs.getString("CUIL"));
				cli.setNombre(rs.getString("Nombre"));
				cli.setApellido(rs.getString("Apellido"));
				cli.setFechaNacimiento(rs.getDate("FechaNacimiento"));
				cli.setDireccion(rs.getString("Direccion"));
				cli.setCorreoElectronico(rs.getString("CorreoElectronico"));
				cli.setTelefono(rs.getString("Telefono"));
				String nacionalidadDescripcion = rs.getString("Nacionalidad.Descripcion");
				Nacionalidad nacionalidad = new Nacionalidad();
				nacionalidad.setDescripcion(nacionalidadDescripcion);
				cli.setNacionalidad(nacionalidad);
				String sexoDescripcion = rs.getString("Sexo.Descripcion");
				Sexo sexo = new Sexo();
				sexo.setDescripcion(sexoDescripcion);
				cli.setSexo(sexo);
				listCli.add(cli);			
				}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listCli;
	}	


	public int eliminarCliente(int dni) {
	    cn = new Conexion();
	    cn.Open();
	    int filasAfectadas = 0;
	    String query = "UPDATE clientes SET estado = FALSE WHERE DNI = ? AND estado = 1";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, dni); 
	        filasAfectadas = pst.executeUpdate(); 
	    } catch (Exception e) {
	        System.out.println("Error al actualizar el estado del cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return filasAfectadas; 
	}
	
	public ArrayList<Cliente> obtenerClientesPorNombre(String nombre) {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cliente> listCli = new ArrayList<>();
		String query = "SELECT DNI,CUIL,Nombre,Apellido,FechaNacimiento,Direccion,nacionalidad.Descripcion,sexo.descripcion,CorreoElectronico,Telefono FROM clientes INNER JOIN nacionalidad ON clientes.IDNacionalidad = nacionalidad.IDNacionalidad INNER JOIN sexo ON clientes.Sexo = sexo.id WHERE estado = 1 AND Nombre like ? ";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setString(1, "%" + nombre + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setDni(rs.getInt("DNI"));
				cli.setCuil(rs.getString("CUIL"));
				cli.setNombre(rs.getString("Nombre"));				
				cli.setApellido(rs.getString("Apellido"));
				cli.setFechaNacimiento(rs.getDate("FechaNacimiento"));
				cli.setDireccion(rs.getString("Direccion"));
				cli.setCorreoElectronico(rs.getString("CorreoElectronico"));
				cli.setTelefono(rs.getString("Telefono"));
				String nacionalidadDescripcion = rs.getString("Nacionalidad.Descripcion");
				Nacionalidad nacionalidad = new Nacionalidad();
				nacionalidad.setDescripcion(nacionalidadDescripcion);
				cli.setNacionalidad(nacionalidad);
				String sexoDescripcion = rs.getString("Sexo.Descripcion");
				Sexo sexo = new Sexo();
				sexo.setDescripcion(sexoDescripcion);
				cli.setSexo(sexo);
				listCli.add(cli);		
				}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listCli;
	}

	public ArrayList<Cliente> obtenerClientesPorUsuario(String usuario) {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cliente> listCli = new ArrayList<>();
		String query = "SELECT DNI,CUIL,Nombre,Apellido,FechaNacimiento,Direccion,nacionalidad.Descripcion,Sexo,CorreoElectronico,Telefono FROM clientes INNER JOIN nacionalidad ON clientes.IDNacionalidad = nacionalidad.IDNacionalidad WHERE estado = 1 AND usuario = ?";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setString(1, usuario);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cliente cli = new Cliente();
				cli.setDni(rs.getInt("DNI"));
				cli.setCuil(rs.getString("CUIL"));
				cli.setNombre(rs.getString("Nombre"));				
				cli.setApellido(rs.getString("Apellido"));
				cli.setFechaNacimiento(rs.getDate("FechaNacimiento"));
				cli.setDireccion(rs.getString("Direccion"));
				cli.setCorreoElectronico(rs.getString("CorreoElectronico"));
				cli.setTelefono(rs.getString("Telefono"));
				String nacionalidadDescripcion = rs.getString("Nacionalidad.Descripcion");
				Nacionalidad nacionalidad = new Nacionalidad();
				nacionalidad.setDescripcion(nacionalidadDescripcion);
				cli.setNacionalidad(nacionalidad);
				String sexoDescripcion = rs.getString("Sexo");
				Sexo sexo = new Sexo();
				sexo.setDescripcion(sexoDescripcion);
				cli.setSexo(sexo);
				listCli.add(cli);		
				}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		System.out.println("Lista de clientes en DAO: " + listCli);
		return listCli;
	}
	
	public Cliente obtenerClientesPorUsuarioObj(String usuario) {
		cn = new Conexion();
		cn.Open();
		Cliente cliente = null;
		String query = "SELECT DNI,CUIL,Nombre,Apellido,FechaNacimiento,Direccion,nacionalidad.Descripcion,Sexo,CorreoElectronico,Telefono FROM clientes INNER JOIN nacionalidad ON clientes.IDNacionalidad = nacionalidad.IDNacionalidad WHERE estado = 1 AND usuario = ?";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setString(1, usuario);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cliente = new Cliente();
				cliente.setDni(rs.getInt("DNI"));
				cliente.setCuil(rs.getString("CUIL"));
				cliente.setNombre(rs.getString("Nombre"));				
				cliente.setApellido(rs.getString("Apellido"));
				cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
				cliente.setDireccion(rs.getString("Direccion"));
				cliente.setCorreoElectronico(rs.getString("CorreoElectronico"));
				cliente.setTelefono(rs.getString("Telefono"));
				String nacionalidadDescripcion = rs.getString("Nacionalidad.Descripcion");
				Nacionalidad nacionalidad = new Nacionalidad();
				nacionalidad.setDescripcion(nacionalidadDescripcion);
				cliente.setNacionalidad(nacionalidad);
				String sexoDescripcion = rs.getString("Sexo");
				Sexo sexo = new Sexo();
				sexo.setDescripcion(sexoDescripcion);
				cliente.setSexo(sexo);
				}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cliente;
	}
	
	
	
		public int modificarCliente(Cliente cliente) {
			cn = new Conexion();
		    cn.Open(); 
		    int filasAfectadas = 0;
		    
		    String query = "UPDATE clientes SET CUIL = ?, Nombre = ?, Apellido = ?, Sexo = ?, FechaNacimiento = ?, Direccion = ?, IDLocalidad = ?, CorreoElectronico = ?, Telefono = ?, IDNacionalidad = ?, IDProvincia = ? WHERE DNI = ?";

		    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
		        // datos que reemplazaran a los actuales
		        pst.setString(1, cliente.getCuil());  
		        pst.setString(2, cliente.getNombre()); 
		        pst.setString(3, cliente.getApellido()); 
		        pst.setInt(4, cliente.getSexo().getId()); 
		        pst.setDate(5, (Date) cliente.getFechaNacimiento()); 
		        pst.setString(6, cliente.getDireccion()); 
		        pst.setInt(7, cliente.getLocalidad().getIdLocalidad());
		        pst.setString(8, cliente.getCorreoElectronico());
		        pst.setString(9, cliente.getTelefono()); 
		        pst.setInt(10, cliente.getNacionalidad().getId());
		        pst.setInt(11, cliente.getProvincia().getIDProvincia()); 
		        pst.setInt(12, cliente.getDni());
		        
		        filasAfectadas = pst.executeUpdate();
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("Error al Modificar Cliente: " + e.getMessage());
		    } finally {
		        cn.close();
		    }

		    return filasAfectadas;
	}
	

	public Cliente BuscarCliente(Cliente obj) {
		cn = new Conexion();
	    cn.Open();
	    Cliente cliente = null; // Creamos un nuevo cliente para retornarlo
	    String query = "SELECT DNI, CUIL, Nombre, Apellido, sexo, FechaNacimiento, Direccion, IDLocalidad, correoElectronico, telefono, IDNacionalidad, usuario, IDProvincia FROM clientes WHERE estado = 1 AND DNI = ?";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, obj.getDni()); // Usamos el DNI para la consulta
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            cliente = new Cliente(); // Creamos un nuevo cliente
	            cliente.setDni(rs.getInt("DNI"));
	            cliente.setCuil(rs.getString("CUIL"));
	            cliente.setNombre(rs.getString("Nombre"));
	            cliente.setApellido(rs.getString("Apellido"));

	            if (cliente.getSexo() == null) {
	                cliente.setSexo(new Sexo()); 
	            }
	            cliente.getSexo().setId(rs.getInt("sexo"));

	            cliente.setFechaNacimiento(rs.getDate("FechaNacimiento"));
	            cliente.setDireccion(rs.getString("Direccion"));
	            cliente.setCorreoElectronico(rs.getString("CorreoElectronico"));
	            cliente.setTelefono(rs.getString("Telefono"));

	            if (cliente.getNacionalidad() == null) {
	                cliente.setNacionalidad(new Nacionalidad()); 
	            }
	            cliente.getNacionalidad().setId(rs.getInt("IDNacionalidad"));

	            if (cliente.getProvincia() == null) {
	                cliente.setProvincia(new Provincia()); 
	            }
	            cliente.getProvincia().setIDProvincia(rs.getInt("IDProvincia"));

	            if (cliente.getLocalidad() == null) {
	                cliente.setLocalidad(new Localidad());
	            }
	            cliente.getLocalidad().setIdLocalidad(rs.getInt("IDLocalidad"));

	            if(cliente.getUsuario()==null) {
	            	cliente.setUsuario(new Usuario());
	            }
	            cliente.getUsuario().setUsuario(rs.getString("Usuario"));
	        }
	    } catch (Exception e) {
	        System.out.println("Error al buscar el cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return cliente;
	}

	@Override
	public int agregarCliente(Cliente obj) {
		cn = new Conexion();
		cn.Open(); 
		int filasAfectadas = 0;
		String query = "INSERT INTO clientes (DNI, CUIL,Nombre,Apellido,Sexo,FechaNacimiento,Direccion,IDLocalidad,CorreoElectronico,Telefono,Usuario,IDNacionalidad,IDProvincia) VALUES (?, ?, ?, ?, ?, ?,?,?,?,?,?,?,?)"; 
		try (PreparedStatement pst = cn.connection.prepareStatement(query))
		{ 
		pst.setInt(1, obj.getDni());
		pst.setString(2, obj.getCuil()); 
		pst.setString(3, obj.getNombre());
		pst.setString(4, obj.getApellido());
		pst.setInt(5, obj.getSexo().getId());
		pst.setDate(6, (Date) obj.getFechaNacimiento()); 
		pst.setString(7, obj.getDireccion());
		pst.setInt(8, obj.getLocalidad().getIdLocalidad());
		pst.setString(9, obj.getCorreoElectronico());
		pst.setString(10, obj.getTelefono());
		pst.setString(11,obj.getUsuario().getUsuario());
		pst.setInt(12, obj.getNacionalidad().getId());
		pst.setInt(13, obj.getProvincia().getIDProvincia());
		filasAfectadas = pst.executeUpdate(); } 
		catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("Error al Agregar Cliente: " + e.getMessage());
		} 
		finally { cn.close(); }
		
		return filasAfectadas;
	}

	@Override
	public boolean existeDNI(int dni) {
		cn = new Conexion();
	    cn.Open();
	    boolean existe = false;
	    String query = "SELECT COUNT(*) FROM Clientes WHERE DNI = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, dni);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                existe = rs.getInt(1) > 0;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al verificar el DNI: " + e.getMessage());
	    } finally {
	        cn.close();
	    }
	    return existe;
	}

	
	

}
