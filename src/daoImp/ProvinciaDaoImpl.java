package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.ProvinciaDao;
import entidad.Provincia;

public class ProvinciaDaoImpl implements ProvinciaDao {

	private Conexion cn;
	
	public ProvinciaDaoImpl() {
		
	}
	
	public ArrayList<Provincia> ObtenerProvincias() {
		cn= new Conexion();
		cn.Open();
		ArrayList<Provincia> listPro = new ArrayList<>();
		String query = "SELECT IDProvincia, Nombre FROM Provincias";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			
			 while (rs.next()) {
	                Provincia Pro = new Provincia();
	                Pro.setIDProvincia(rs.getInt("IDProvincia"));
	                Pro.setNombre(rs.getString("Nombre"));
	                listPro.add(Pro); 
			 }
		
	    }catch (Exception e) {
            System.out.println("Error al obtener sexos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cerrar la conexión
        }
		return listPro;
	}

}
