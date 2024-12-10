package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CondicionPrestamosDao;
import entidad.CondicionPrestamos;


public class CondicionPrestamosDaoImpl implements CondicionPrestamosDao{
	private Conexion cn;
	
	public CondicionPrestamosDaoImpl() {
		
	}

	@Override
	public ArrayList<CondicionPrestamos> obtenerCondiciones() {
		cn= new Conexion();
		cn.Open();
		ArrayList<CondicionPrestamos> listCon = new ArrayList<>();
		String query = "SELECT idCondicion, descripcion FROM condicionprestamos";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			
			 while (rs.next()) {
	                CondicionPrestamos Con = new CondicionPrestamos();
	                Con.setIdCondicion(rs.getInt("idCondicion"));
	                Con.setDescripcion(rs.getString("descripcion"));
	                listCon.add(Con); 
			 }
		
	    }catch (Exception e) {
            System.out.println("Error al obtener Condiciones: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cerrar la conexión
        }
		return listCon;
	}

}
