package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import dao.NacionalidadDao;
import entidad.Nacionalidad;

public class NacionalidadDaoImpl implements NacionalidadDao {
	private Conexion cn;
	
	public NacionalidadDaoImpl() {
		
	}

	public ArrayList<Nacionalidad> ObtenerNacionalidad() {
		cn= new Conexion();
		cn.Open();
		ArrayList<Nacionalidad> listNac = new ArrayList<>();
		String query = "SELECT idNacionalidad, descripcion FROM Nacionalidad";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			
			 while (rs.next()) {
	                Nacionalidad Nac = new Nacionalidad();
	                Nac.setId(rs.getInt("idNacionalidad"));
	                Nac.setDescripcion(rs.getString("descripcion"));
	                listNac.add(Nac); 
			 }
		
	    }catch (Exception e) {
            System.out.println("Error al obtener sexos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cerrar la conexión
        }
		return listNac;
	}

}
