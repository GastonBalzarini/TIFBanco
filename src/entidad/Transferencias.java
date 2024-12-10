package entidad;

import java.sql.Date;

public class Transferencias {
	private int IDTransferencia;
	private Date Fecha;
	private float Importe;
	private int CuentaOrigen;
	private int CuentaDestino;
	
	public Transferencias(int iDTransferencia, Date fecha, float importe, int cuentaOrigen, int cuentaDestino) {
		IDTransferencia = iDTransferencia;
		Fecha = fecha;
		Importe = importe;
		CuentaOrigen = cuentaOrigen;
		CuentaDestino = cuentaDestino;
	}
	
	public Transferencias() {

	}


	public int getIdTransferencia() {
		return IDTransferencia;
	}


	public void setIdTransferencia(int idTransferencia) {
		IDTransferencia = idTransferencia;
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

	public int getCuentaOrigen() {
		return CuentaOrigen;
	}

	public void setCuentaOrigen(int cuentaOrigen) {
		CuentaOrigen = cuentaOrigen;
	}


	public int getCuentaDestino() {
		return CuentaDestino;
	}


	public void setCuentaDestino(int cuentaDestino) {
		CuentaDestino = cuentaDestino;
	}	
	
	
}
