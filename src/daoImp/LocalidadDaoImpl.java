package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.LocalidadDao;
import entidad.Localidad;

public class LocalidadDaoImpl implements LocalidadDao{

	private Conexion cn;
	
	public LocalidadDaoImpl() {
		
	}
	
	public ArrayList<Localidad> obtenerLocalidades(int idPro) {
		cn= new Conexion();
		cn.Open();
		ArrayList<Localidad> listLoc = new ArrayList<>();
		String query = "SELECT idLocalidad, Nombre, IdProvincia FROM localidades WHERE IdProvincia = ?";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setInt(1, idPro);
			ResultSet rs = pst.executeQuery();
			
			 while (rs.next()) {
	                Localidad loc = new Localidad();
	                loc.setIdLocalidad(rs.getInt("idLocalidad"));
	                loc.setNombre(rs.getString("Nombre"));
	                listLoc.add(loc); 
			 }
		
	    }catch (Exception e) {
            System.out.println("Error al obtener Localidad: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cerrar la conexión
        }
		return listLoc;
	}

}
