package negocioImp;

import java.util.ArrayList;

import dao.PrestamoDao;
import daoImp.PrestamoDaoImpl;
import entidad.Prestamos;
import negocio.PrestamoNeg;

public class PrestamoNegImpl implements PrestamoNeg{

	PrestamoDao pDao = new PrestamoDaoImpl();
	
	public ArrayList<Prestamos> obtenerPrestamosPendientes() {
		
		return pDao.obtenerPrestamosPendientes();
	}

	public ArrayList<Prestamos> obtenerPrestamosCliente(int cli) {
		
		return pDao.obtenerPrestamosCliente(cli);
	}
	
	@Override
	public int cambiarCondicionPrestamo(Prestamos obj) {
		
		return pDao.cambiarCondicionPrestamo(obj);
	}

	@Override
	public ArrayList<Prestamos> obtenerTodosLosPrestamos() {
		
		return pDao.obtenerTodosLosPrestamos();
	}

	@Override
	public ArrayList<Prestamos> ObtenerPretamosPorCondicion(int con) {
		// TODO Auto-generated method stub
		return pDao.ObtenerPretamosPorCondicion(con);
	}
	
	public int agregarPrestamo(Prestamos obj) {
			
			return pDao.agregarPrestamo(obj);
		}

	public int obtenerUltimoIDPrestamo(){
		return pDao.obtenerUltimoIDPrestamo();
	}
	
	public ArrayList<Prestamos> obtenerPrestamosClienteAceptados(int Cliente){
		return pDao.obtenerPrestamosClienteAceptados(Cliente);
	}
	
	public int RechazarPrestamo(int idPrestamo) {
		return pDao.RechazarPrestamo(idPrestamo);
	}

	@Override
	public int marcarPrestamoComoPago(int idPrestamo) {
		return pDao.marcarPrestamoComoPago(idPrestamo);
	}
	
	

}
