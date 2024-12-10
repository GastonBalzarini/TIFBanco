package negocio;

import java.util.ArrayList;

import entidad.Transferencias;

public interface TransferenciaNeg {

	public int agregarTransferencia(Transferencias obj);
	
	public ArrayList<Transferencias> ObtenerTransferenciasSegunCuenta(int cuenta);
	
}
