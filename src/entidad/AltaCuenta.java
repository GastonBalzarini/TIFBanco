package entidad;

import java.util.Date;

public class AltaCuenta {
	private int IDAItaCuenta;
	private int DNI;
	private Date Fecha;
	
	private static float Saldolnicial = 10000;
	
	public AltaCuenta(int iDAItaCuenta, int dNI, Date fecha, float saldolnicial) {
		IDAItaCuenta = iDAItaCuenta;
		DNI = dNI;
		Fecha = fecha;
		Saldolnicial = saldolnicial;
	}
	
	public AltaCuenta() {

	}

	public int getIDAItaCuenta() {
		return IDAItaCuenta;
	}


	public void setIDAItaCuenta(int iDAItaCuenta) {
		IDAItaCuenta = iDAItaCuenta;
	}


	public int getDNI() {
		return DNI;
	}


	public void setDNI(int dNI) {
		DNI = dNI;
	}


	public Date getFecha() {
		return Fecha;
	}


	public void setFecha(Date fecha) {
		Fecha = fecha;
	}


	public float getSaldolnicial() {
		return Saldolnicial;
	}


	public void setSaldolnicial(float saldolnicial) {
		Saldolnicial = saldolnicial;
	}
	
	
}
