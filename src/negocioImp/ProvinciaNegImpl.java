package negocioImp;

import java.util.ArrayList;

import dao.ProvinciaDao;
import daoImp.ProvinciaDaoImpl;
import entidad.Provincia;
import negocio.ProvinciaNeg;

public class ProvinciaNegImpl implements ProvinciaNeg{
	ProvinciaDao pDao = new ProvinciaDaoImpl();
	public ArrayList<Provincia> obtenerProvincias() {
		
		return pDao.ObtenerProvincias();
	}

}
