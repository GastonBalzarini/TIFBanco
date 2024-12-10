package negocioImp;

import java.util.ArrayList;

import dao.CondicionPrestamosDao;
import daoImp.CondicionPrestamosDaoImpl;
import entidad.CondicionPrestamos;
import negocio.CondicionPrestamosNeg;

public class CondicionPrestamosNegImpl implements CondicionPrestamosNeg{
	CondicionPrestamosDao cpDao = new CondicionPrestamosDaoImpl();
	
	public CondicionPrestamosNegImpl() {
		
	}

	@Override
	public ArrayList<CondicionPrestamos> obtenerCondiciones() {
		
		return cpDao.obtenerCondiciones();
	}
	
	

}
