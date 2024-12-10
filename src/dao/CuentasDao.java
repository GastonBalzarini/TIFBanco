package dao;

import java.util.ArrayList;

import entidad.Cuentas;


public interface CuentasDao {
	
	public ArrayList<Cuentas> ObtenerCuentas();
	
	public int eliminarCuenta(int NumeroCuenta);

	public int obtenerUltimoNumeroCuenta();

	boolean agregarCuenta(Cuentas cuenta);

	Cuentas obtenerCuentaPorNumero(int numeroCuenta);

	boolean modificarCuenta(Cuentas cuenta);

	public void bajarCuentasDeUsuario(int DNI);
	
	public void transferirPrestamo(Cuentas cuenta);
	
	public ArrayList<Cuentas> ObtenerCuentasPorDni(int dni);
	
	public ArrayList<Cuentas> ObtenerCuentasPorNumCue(String CBU);
	
	Cuentas ObtenerCuentasPorDniobj(int dni);

	public Cuentas obtenerNroCuentaPorCBU(String CBU);
	
	public Cuentas ObtenerCuentaPorUsuario(String usuario);
	
	public void modificarSaldo(String cbu, float monto);

	public void modificarSaldoNumCue(int id, float monto);

	float obtenerSaldo(int numeroCuenta);
	
	public ArrayList<Cuentas> ObtenerCuentasPorDniLIKE(int dni);
	
}
