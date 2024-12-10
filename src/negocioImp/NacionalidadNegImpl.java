package negocioImp;

import java.util.ArrayList;

import dao.NacionalidadDao;
import daoImp.NacionalidadDaoImpl;
import entidad.Nacionalidad;
import negocio.NacionalidadNeg;

public class NacionalidadNegImpl implements NacionalidadNeg {
	NacionalidadDao nDao = new NacionalidadDaoImpl();

	public ArrayList<Nacionalidad> ObtenerNacionalidad() {
		
		return nDao.ObtenerNacionalidad();
	}

}
