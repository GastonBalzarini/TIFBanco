package entidad;

import java.util.Date;

public class AltaPrestamo {
	private int IDAItaPrestamo;
	private int Cliente;
	private Date Fecha;
	private float Importe;
	
	public AltaPrestamo(int iDAItaPrestamo, int cliente, Date fecha, float importe) {
		IDAItaPrestamo = iDAItaPrestamo;
		Cliente = cliente;
		Fecha = fecha;
		Importe = importe;
	}
	
	public AltaPrestamo() {

	}

	public int getIDAItaPrestamo() {
		return IDAItaPrestamo;
	}

	public void setIDAItaPrestamo(int iDAItaPrestamo) {
		IDAItaPrestamo = iDAItaPrestamo;
	}

	public int getCliente() {
		return Cliente;
	}

	public void setCliente(int cliente) {
		Cliente = cliente;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public float getImporte() {
		return Importe;
	}

	public void setImporte(float importe) {
		Importe = importe;
	}
	
}
