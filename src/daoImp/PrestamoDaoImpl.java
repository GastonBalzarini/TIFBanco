package daoImp;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.PrestamoDao;
import entidad.Cliente;
import entidad.CondicionPrestamos;
import entidad.Cuentas;
import entidad.Prestamos;

public class PrestamoDaoImpl implements PrestamoDao{
	private Conexion cn;
	
	public PrestamoDaoImpl() {
		
	}
	
	@Override
	public ArrayList<Prestamos> obtenerPrestamosPendientes() {
		cn = new Conexion();
		cn.Open();
		ArrayList<Prestamos> listpre = new ArrayList<>();
		String query = "select IDPrestamo, Cliente,NumCuenta,fecha,ImportePedido,PlazoDePago, cp.descripcion from ((prestamos inner join clientes as c on Cliente = c.DNI) inner join condicionprestamos as cp on Condicion = cp.idCondicion) where prestamos.Estado = true AND prestamos.Condicion = 3";
		
		try(PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Prestamos obj = new Prestamos();
				obj.setIDPrestamo(rs.getInt("IDPrestamo"));
				obj.setCliente(rs.getInt("Cliente"));
				Cuentas cue = new Cuentas();
				cue.setNumeroCuenta(rs.getInt("NumCuenta"));
				obj.setCuenta(cue);
				obj.setFecha(rs.getDate("fecha"));
				obj.setImportePedido(rs.getFloat("ImportePedido"));
				obj.setPlazoDePago(rs.getInt("PlazoDePago"));
				CondicionPrestamos cp = new CondicionPrestamos();
				cp.setDescripcion(rs.getString("cp.descripcion"));
				obj.setCondicion(cp);
				listpre.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return listpre;
	}

	
	public int cambiarCondicionPrestamo(Prestamos obj) {
		cn = new Conexion();
		cn.Open();
		int filasAfectadas = 0;
		String query = "UPDATE Prestamos SET Condicion = ? WHERE IDPrestamo = ?";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        // datos que reemplazaran a los actuales
	        pst.setInt(1, obj.getCondicion().getIdCondicion());  
	        pst.setInt(2, obj.getIDPrestamo());
	        
	        filasAfectadas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al Modificar la condicion Prestamo: " + e.getMessage());
	    } finally {
	        cn.close();
	    }

	    return filasAfectadas;
		
	}

	public int RechazarPrestamo(int idPrestamo) {
		cn = new Conexion();
		cn.Open();
		int filasAfectadas = 0;
		String query = "UPDATE Prestamos SET Condicion = ? WHERE IDPrestamo = ?";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        // datos que reemplazaran a los actuales
	        pst.setInt(1, 2);  
	        pst.setInt(2, idPrestamo);
	        
	        filasAfectadas = pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Error al Modificar la condicion Prestamo: " + e.getMessage());
	    } finally {
	        cn.close();
	    }

	    return filasAfectadas;
		
	}

	
	
	@Override
	public ArrayList<Prestamos> obtenerTodosLosPrestamos() {
		cn = new Conexion();
		cn.Open();
		ArrayList<Prestamos> listpre = new ArrayList<>();
		String query = "select IDPrestamo, Cliente,NumCuenta,fecha,ImportePedido,ImporteAPagar,PlazoDePago, cp.descripcion from ((prestamos inner join clientes as c on Cliente = c.DNI) inner join condicionprestamos as cp on Condicion = cp.idCondicion) where prestamos.Estado = true";
		
		try(PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				Prestamos obj = new Prestamos();
				obj.setIDPrestamo(rs.getInt("IDPrestamo"));
				obj.setCliente(rs.getInt("Cliente"));
				Cuentas cue = new Cuentas();
				cue.setNumeroCuenta(rs.getInt("NumCuenta"));
				obj.setCuenta(cue);
				obj.setFecha(rs.getDate("fecha"));
				obj.setImportePedido(rs.getFloat("ImportePedido"));
				obj.setImporteAPagar(rs.getFloat("ImporteAPagar"));
				obj.setPlazoDePago(rs.getInt("PlazoDePago"));
				CondicionPrestamos cp = new CondicionPrestamos();
				cp.setDescripcion(rs.getString("cp.descripcion"));
				obj.setCondicion(cp);
				listpre.add(obj);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			cn.close();
		}
		return listpre;
	}

	@Override
	public ArrayList<Prestamos> ObtenerPretamosPorCondicion(int con) {
		cn = new Conexion();
		cn.Open();
		ArrayList<Prestamos> listpre = new ArrayList<>();
		String query = "select IDPrestamo, Cliente,NumCuenta,fecha,ImportePedido,ImporteAPagar,PlazoDePago, cp.descripcion from ((prestamos inner join clientes as c on Cliente = c.DNI) inner join condicionprestamos as cp on Condicion = cp.idCondicion) where prestamos.Estado = true AND prestamos.Condicion = ?";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, con);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            Prestamos obj = new Prestamos();
	            obj.setIDPrestamo(rs.getInt("IDPrestamo"));
	            obj.setCliente(rs.getInt("Cliente"));
	            
	            Cuentas cue = new Cuentas();
	            cue.setNumeroCuenta(rs.getInt("NumCuenta"));
	            obj.setCuenta(cue);
	            
	            obj.setFecha(rs.getDate("fecha"));
	            obj.setImportePedido(rs.getFloat("ImportePedido"));
	            obj.setImporteAPagar(rs.getFloat("ImporteAPagar"));
	            obj.setPlazoDePago(rs.getInt("PlazoDePago"));
	            
	            CondicionPrestamos cp = new CondicionPrestamos();
	            cp.setDescripcion(rs.getString("descripcion"));
	            obj.setCondicion(cp);
	            
	            listpre.add(obj);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return listpre;
	}
	
	public int obtenerUltimoIDPrestamo() {
	    cn = new Conexion();  
	    cn.Open();  
	    int ultimoID = 0;  
	    String query = "SELECT MAX(IDPrestamo) FROM Prestamos"; 
	    
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        ResultSet rs = pst.executeQuery();        
	        if (rs.next()) {
	            ultimoID = rs.getInt(1);  
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();  
	        System.out.println("Error al Obtener el último ID de Prestamo: " + e.getMessage());
	    } finally {
	        cn.close();  
	    }

	    return ultimoID;  
	}
	
	public int agregarPrestamo(Prestamos obj) {
	    cn = new Conexion();
	    cn.Open(); 
	    int filasAfectadas = 0;
	    
	    String query = "INSERT INTO prestamos (IdPrestamo, Cliente, Fecha, ImportePedido, ImporteAPagar, PlazoDePago, MontoPorMes, Cuotas, Condicion, NumCuenta) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"; 

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {

	        pst.setInt(1, obj.getIDPrestamo());  
	        pst.setInt(2, obj.getCliente());
	        pst.setDate(3, (Date) obj.getFecha());
	        pst.setFloat(4, obj.getImportePedido());
	        pst.setFloat(5, obj.getImporteAPagar());
	        pst.setInt(6, obj.getPlazoDePago());
	        pst.setFloat(7, obj.getMontoPorMes());
	        pst.setInt(8, obj.getCuotas());
	        pst.setInt(9, obj.getCondicion().getIdCondicion());
	        pst.setInt(10, obj.getCuenta().getNumeroCuenta());
	        filasAfectadas = pst.executeUpdate(); } 
				catch (SQLException e) {
					e.printStackTrace(); 
					System.out.println("Error al Agregar Cliente: " + e.getMessage());
				} 
				finally { cn.close(); }
				
				return filasAfectadas;
	}

	
	public ArrayList<Prestamos> obtenerPrestamosCliente(int Cliente) {
		cn = new Conexion();
		cn.Open();
		ArrayList<Prestamos> listpre = new ArrayList<>();
		String query = "select IDPrestamo, Cliente,NumCuenta,fecha,ImportePedido,ImporteAPagar,PlazoDePago, MontoPorMes, cp.descripcion from ((prestamos inner join clientes as c on Cliente = c.DNI) inner join condicionprestamos as cp on Condicion = cp.idCondicion) where prestamos.Estado = true AND prestamos.Cliente = ?";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, Cliente);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            Prestamos obj = new Prestamos();
	            obj.setIDPrestamo(rs.getInt("IDPrestamo"));
	            obj.setCliente(rs.getInt("Cliente"));
	            
	            Cuentas cue = new Cuentas();
	            cue.setNumeroCuenta(rs.getInt("NumCuenta"));
	            obj.setCuenta(cue);
	            
	            obj.setFecha(rs.getDate("fecha"));
	            obj.setImportePedido(rs.getFloat("ImportePedido"));
	            obj.setImporteAPagar(rs.getFloat("ImporteAPagar"));
	            obj.setPlazoDePago(rs.getInt("PlazoDePago"));
	            obj.setMontoPorMes(rs.getFloat("MontoPorMes"));  
	            CondicionPrestamos cp = new CondicionPrestamos();
	            cp.setDescripcion(rs.getString("descripcion"));
	            obj.setCondicion(cp);
	            
	            listpre.add(obj);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return listpre;
	}
	
	public ArrayList<Prestamos> obtenerPrestamosClienteAceptados(int Cliente) {
		cn = new Conexion();
		cn.Open();
		ArrayList<Prestamos> listpre = new ArrayList<>();
		String query = "select IDPrestamo, Cliente,NumCuenta,fecha,ImportePedido,ImporteAPagar,PlazoDePago, MontoPorMes, cp.descripcion from ((prestamos inner join clientes as c on Cliente = c.DNI) inner join condicionprestamos as cp on Condicion = cp.idCondicion) where prestamos.Estado = true AND prestamos.Condicion = 1 AND prestamos.Cliente = ?";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, Cliente);

	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            Prestamos obj = new Prestamos();
	            obj.setIDPrestamo(rs.getInt("IDPrestamo"));
	            obj.setCliente(rs.getInt("Cliente"));
	            
	            Cuentas cue = new Cuentas();
	            cue.setNumeroCuenta(rs.getInt("NumCuenta"));
	            obj.setCuenta(cue);
	            
	            obj.setFecha(rs.getDate("fecha"));
	            obj.setImportePedido(rs.getFloat("ImportePedido"));
	            obj.setImporteAPagar(rs.getFloat("ImporteAPagar"));
	            obj.setPlazoDePago(rs.getInt("PlazoDePago"));
	            obj.setMontoPorMes(rs.getFloat("MontoPorMes"));  
	            CondicionPrestamos cp = new CondicionPrestamos();
	            cp.setDescripcion(rs.getString("descripcion"));
	            obj.setCondicion(cp);
	            
	            listpre.add(obj);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return listpre;
	}
	
	
	public int marcarPrestamoComoPago(int idPrestamo) {
	    cn = new Conexion();
	    cn.Open();
	    int filasAfectadas = 0;
	    String query = "UPDATE prestamos SET condicion = 4 WHERE IDPrestamo = ?";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, idPrestamo); 
	        filasAfectadas = pst.executeUpdate(); 
	    } catch (Exception e) {
	        System.out.println("Error al actualizar el prestamo: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return filasAfectadas; 
	}
}
