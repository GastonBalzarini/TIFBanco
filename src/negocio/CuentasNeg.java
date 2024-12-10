package negocio;

import java.util.ArrayList;

import entidad.Cuentas;

public interface CuentasNeg {

	public ArrayList<Cuentas> obtenerCuentas();
	
	public int eliminarCuenta(int NumeroCuenta);
	
	public void bajarCuentasDeUsuario(int DNI);
	
	public void transferirPrestamo(Cuentas cuenta);
	
	Cuentas obtenerCuentaPorNumero(int numeroCuenta);
	
	public ArrayList<Cuentas> ObtenerCuentasPorDni(int dni);

	public ArrayList<Cuentas> ObtenerCuentasPorNumCue(String CBU);

	public Cuentas ObtenerCuentasPorDniobj(int dni);

	public Cuentas obtenerNroCuentaPorCBU(String CBU);
	
	public Cuentas ObtenerCuentaPorUsuario(String usuario);
	
	public void modificarSaldo(String cbu, float monto);

	public void modificarSaldoNumCue(int id, float monto);

	float obtenerSaldo(int nc);
	
	public ArrayList<Cuentas> ObtenerCuentasPorDniLIKE(int dni);

}