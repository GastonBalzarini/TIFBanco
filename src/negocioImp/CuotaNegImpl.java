package negocioImp;

import java.util.ArrayList;
import java.util.List;

import dao.CuotaDao;
import daoImp.CuotaDaoImpl;
import entidad.Cuota;
import negocio.CuotaNeg;

public class CuotaNegImpl implements CuotaNeg {
    private CuotaDao cuotasDAO = new CuotaDaoImpl();

    @Override
    public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        return cuotasDAO.obtenerCuotasPorPrestamo(idPrestamo);
    }

	@Override
	public boolean agregarCuotas(ArrayList<Cuota> listaCuotas) {
		// TODO Auto-generated method stub
		return cuotasDAO.agregarCuotas(listaCuotas);
	}

	@Override
	public int contarCuotasPagas(int idPrestamo) {
		return cuotasDAO.contarCuotasPagas(idPrestamo) ;
	}
}
