package dao;

import java.util.ArrayList;

import entidad.Transferencias;

public interface TransferenciaDao {

	public ArrayList<Transferencias> ObtenerTransferenciasSegunCuenta(int cuenta);
	
	public int agregarTransferencia(Transferencias obj);
}
