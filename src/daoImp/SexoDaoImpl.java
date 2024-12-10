package daoImp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.SexoDao;
import entidad.Sexo;

public class SexoDaoImpl implements SexoDao {

	private Conexion cn;
	
	public SexoDaoImpl() {
		
	}
	
	public ArrayList<Sexo> obtenerSexos() {
		cn = new Conexion();
        cn.Open(); 
        ArrayList<Sexo> ListaSexos = new ArrayList<Sexo>();
        String query = "SELECT id, descripcion FROM Sexo";

        try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Sexo sexo = new Sexo();
                sexo.setId(rs.getInt("id"));
                sexo.setDescripcion(rs.getString("descripcion"));
                ListaSexos.add(sexo); // Agregar el objeto Sexo a la lista
            }
        } catch (Exception e) {
            System.out.println("Error al obtener sexos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cerrar la conexión
        }

        return ListaSexos;
    }
	
}
