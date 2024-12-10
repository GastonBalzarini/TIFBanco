package negocioImp;

import java.util.ArrayList;

import dao.LocalidadDao;
import daoImp.LocalidadDaoImpl;
import entidad.Localidad;
import negocio.LocalidadNeg;

public class LocalidadNegImpl implements LocalidadNeg{
	LocalidadDao lDao = new LocalidadDaoImpl();
	
	public ArrayList<Localidad> obtenerLocalidades(int IdPro){
		
		return lDao.obtenerLocalidades(IdPro);
	}

}
