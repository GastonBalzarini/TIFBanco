package dao;

import java.util.ArrayList;

import entidad.Localidad;

public interface LocalidadDao {
	
	public ArrayList<Localidad> obtenerLocalidades(int idPro);
}
