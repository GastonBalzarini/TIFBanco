package entidad;

import java.util.Date;

public class Movimiento {
	  private int IDMovimiento;
	  private Date Fecha;
	  private String Detalle;
	  private float Importe;
	  private TipoMovimiento IdTipoMovimiento;
	  private int CuentaOrigen;
	  private int CuentaDestino;
	  
	public Movimiento() {

	}
	
	public Movimiento(int iDMovimiento, Date fecha, String detalle, float importe, TipoMovimiento idTipoMovimiento,
			int cuentaOrigen, int cuentaDestino) {
		super();
		IDMovimiento = iDMovimiento;
		Fecha = fecha;
		Detalle = detalle;
		Importe = importe;
		IdTipoMovimiento = idTipoMovimiento;
		CuentaOrigen = cuentaOrigen;
		CuentaDestino = cuentaDestino;
	}
	
	public int getIDMovimiento() {
		return IDMovimiento;
	}
	public void setIDMovimiento(int iDMovimiento) {
		IDMovimiento = iDMovimiento;
	}
	public Date getFecha() {
		return Fecha;
	}
	public void setFecha(Date fecha) {
		Fecha = fecha;
	}
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}
	public float getImporte() {
		return Importe;
	}
	public void setImporte(float importe) {
		Importe = importe;
	}
	public TipoMovimiento getIdTipoMovimiento() {
		return IdTipoMovimiento;
	}
	public void setIdTipoMovimiento(TipoMovimiento idTipoMovimiento) {
		IdTipoMovimiento = idTipoMovimiento;
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
