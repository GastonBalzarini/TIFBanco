package negocioImp;

import negocio.InformesNeg;

import java.util.Map;

import dao.InformeDao;
import daoImp.InformeDaoImpl;

public class InformesNegImpl implements InformesNeg {
	InformeDao iDao = new InformeDaoImpl();
	
	public Map<String, Integer> obtenerInformesEstadisticos(String fechaInicio, String fechaFin){
		return iDao.obtenerInformesEstadisticos(fechaInicio, fechaFin);
	}
}
