package daoImp;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.TransferenciaDao;
import entidad.Transferencias;

public class TransferenciaDaoImpl implements TransferenciaDao{

	private Conexion cn;
	
	public TransferenciaDaoImpl() {
		
	}
	
	public ArrayList<Transferencias> ObtenerTransferenciasSegunCuenta(int cuenta) {
	    cn = new Conexion();
	    ArrayList<Transferencias> listTrans = new ArrayList<>();
	    try {
	        cn.Open();
	        String query = "SELECT * FROM transferencias WHERE CuentaOrigen = ? OR CuentaDestino = ?";
	        
	        try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	            pst.setInt(1, cuenta);
	            pst.setInt(2, cuenta);
	            ResultSet rs = pst.executeQuery();
	            while (rs.next()) {
	                Transferencias tra = new Transferencias();
	                tra.setIdTransferencia(rs.getInt("IDTransferencia"));
	                tra.setFecha(rs.getDate("Fecha"));
	                tra.setImporte(rs.getFloat("Importe"));
	                tra.setCuentaOrigen(rs.getInt("CuentaOrigen"));
	                tra.setCuentaDestino(rs.getInt("CuentaDestino"));
	                listTrans.add(tra);
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error al obtener transferencias: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return listTrans;
	}
	
		public int agregarTransferencia(Transferencias obj) {
		cn = new Conexion();
		cn.Open(); 
		int filasAfectadas = 0;
		String query = "INSERT INTO transferencias (Fecha,Importe,CuentaOrigen,CuentaDestino)  VALUES (?, ?, ?, ?)"; 
		try (PreparedStatement pst = cn.connection.prepareStatement(query))
		{ 
		pst.setDate(1, (Date) obj.getFecha()); 
		pst.setFloat(2, obj.getImporte());
		pst.setInt(3, obj.getCuentaOrigen());
		pst.setInt(4, obj.getCuentaDestino());
		
		filasAfectadas = pst.executeUpdate(); } 
		catch (SQLException e) {
			e.printStackTrace(); 
			System.out.println("Error al Agregar transferencia: " + e.getMessage());
		} 
		finally { cn.close(); }
		
		return filasAfectadas;
	}
	
}
