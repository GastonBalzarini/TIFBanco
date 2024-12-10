package entidad;

import java.util.Date;

public class Prestamos {
	private int IDPrestamo;
	private int Cliente;
	private Cuentas cuenta;
	private Date Fecha;
	private float ImportePedido;
	private float ImporteAPagar;
	private int PlazoDePago;
	private float MontoPorMes;
	private CondicionPrestamos Condicion;
	private int Cuotas;
	
	public Prestamos() {
		
	}
	public Prestamos(int iDPrestamo, int cliente, Date fecha, float importePedido, float importeAPagar, int plazoDePago,
			float montoPorMes, int cuotas) {
		super();
		IDPrestamo = iDPrestamo;
		Cliente = cliente;
		Fecha = fecha;
		ImportePedido = importePedido;
		ImporteAPagar = importeAPagar;
		PlazoDePago = plazoDePago;
		MontoPorMes = montoPorMes;
		Cuotas = cuotas;
	}
	public int getIDPrestamo() {
		return IDPrestamo;
	}
	public void setIDPrestamo(int iDPrestamo) {
		IDPrestamo = iDPrestamo;
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
	public float getImportePedido() {
		return ImportePedido;
	}
	public void setImportePedido(float importePedido) {
		ImportePedido = importePedido;
	}
	public float getImporteAPagar() {
		return ImporteAPagar;
	}
	public void setImporteAPagar(float importeAPagar) {
		ImporteAPagar = importeAPagar;
	}
	public int getPlazoDePago() {
		return PlazoDePago;
	}
	public void setPlazoDePago(int plazoDePago) {
		PlazoDePago = plazoDePago;
	}
	public float getMontoPorMes() {
		return MontoPorMes;
	}
	public void setMontoPorMes(float montoPorMes) {
		MontoPorMes = montoPorMes;
	}
	public int getCuotas() {
		return Cuotas;
	}
	public void setCuotas(int cuotas) {
		Cuotas = cuotas;
	}
	public CondicionPrestamos getCondicion() {
		return Condicion;
	}
	public void setCondicion(CondicionPrestamos condicion) {
		Condicion = condicion;
	}
	public Cuentas getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuentas cuenta) {
		this.cuenta = cuenta;
	}
	
}
