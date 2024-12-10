package negocioImp;

import java.util.ArrayList;

import dao.TransferenciaDao;
import daoImp.TransferenciaDaoImpl;
import entidad.Transferencias;
import negocio.TransferenciaNeg;

public class TransferenciaNegImpl implements TransferenciaNeg{
	private TransferenciaDao transDao = new TransferenciaDaoImpl();
	
	
	public ArrayList<Transferencias> ObtenerTransferenciasSegunCuenta(int cuenta){
		return transDao.ObtenerTransferenciasSegunCuenta(cuenta);
		
	}
	
	public int agregarTransferencia(Transferencias obj) {
		return transDao.agregarTransferencia(obj);
	}
	
	
	
	
}
