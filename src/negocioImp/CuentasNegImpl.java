package negocioImp;

import java.util.ArrayList;

import dao.CuentasDao;
import daoImp.CuentasDaoImpl;
import entidad.Cuentas;
import negocio.CuentasNeg;

public class CuentasNegImpl implements CuentasNeg{
	private CuentasDao cuentasDao;
	public CuentasNegImpl() {
        cuentasDao = new CuentasDaoImpl();
    }
	
	public ArrayList<Cuentas> obtenerCuentas() {
        return cuentasDao.ObtenerCuentas(); 
    }

	public int eliminarCuenta(int NumeroCuenta) {
		
		return cuentasDao.eliminarCuenta(NumeroCuenta);
	}
	
	public void bajarCuentasDeUsuario(int DNI) {
		cuentasDao.bajarCuentasDeUsuario(DNI);
	}

	@Override
	public void transferirPrestamo(Cuentas cuenta) {
		cuentasDao.transferirPrestamo(cuenta);
		
	}

	public Cuentas obtenerCuentaPorNumero(int numeroCuenta) {
		return cuentasDao.obtenerCuentaPorNumero(numeroCuenta);
	}

	@Override
	public ArrayList<Cuentas> ObtenerCuentasPorDni(int dni) {
		
		return cuentasDao.ObtenerCuentasPorDni(dni);
	}

	public ArrayList<Cuentas> ObtenerCuentasPorNumCue(String CBU) {
		
		return cuentasDao.ObtenerCuentasPorNumCue(CBU);
	}
	
	public Cuentas ObtenerCuentasPorDniobj(int dni) {
		
		return cuentasDao.ObtenerCuentasPorDniobj(dni);
	}
	
	@Override
	public Cuentas obtenerNroCuentaPorCBU(String CBU) {
		return cuentasDao.obtenerNroCuentaPorCBU(CBU) ;
	}

	@Override
	public Cuentas ObtenerCuentaPorUsuario(String usuario) {
		return cuentasDao.ObtenerCuentaPorUsuario(usuario) ;
	}

	@Override
	public void modificarSaldo(String cbu, float monto) {
		cuentasDao.modificarSaldo(cbu, monto);
		
	}

	@Override
	public float obtenerSaldo(int nc) {
		return cuentasDao.obtenerSaldo( nc);
		
	}

	@Override
	public void modificarSaldoNumCue(int id, float monto) {
		cuentasDao.modificarSaldoNumCue(id, monto);
		
	}

	@Override
	public ArrayList<Cuentas> ObtenerCuentasPorDniLIKE(int dni) {
		
		return cuentasDao.ObtenerCuentasPorDniLIKE(dni);
	}
}