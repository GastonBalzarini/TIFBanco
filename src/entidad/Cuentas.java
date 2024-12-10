package entidad;

import java.sql.Date;

import excepciones.BuscarDniConLetrasException;

public class Cuentas {
	private int NumeroCuenta;
	private String CBU;
	private String TipoCuenta;
	private Float Saldo;
	private int DNI;
	private Date FechaCreacion;
	 private boolean estado;
	 
	 // Constructor por defecto
    public Cuentas() {
        this.Saldo = 10000.0f; // Monto inicial fijo
        this.estado = true; // Activo por defecto
    }

    // Constructor con parámetros
    public Cuentas(String cbu, String tipoCuenta, int dniCliente, Date fechaCreacion) {
        this.CBU = cbu;
        this.TipoCuenta = tipoCuenta;
        this.Saldo = 10000.0f; // Monto inicial fijo
        this.DNI = dniCliente;
        this.FechaCreacion = fechaCreacion;
        this.estado = true; // Activo por defecto
    }



    public int getNumeroCuenta() {
        return NumeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.NumeroCuenta = numeroCuenta;
    }

    public String getCbu() {
        return CBU;
    }

    public void setCbu(String cbu) {
        this.CBU = cbu;
    }

    public String getTipoCuenta() {
        return TipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.TipoCuenta = tipoCuenta;
    }

    public Float getSaldo() {
        return Saldo;
    }

    public void setSaldo(Float saldo) {
        this.Saldo = saldo;
    }

    public int getDniCliente() {
        return DNI;
    }

    public void setDniCliente(int dniCliente) {
        this.DNI = dniCliente;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.FechaCreacion = fechaCreacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public static boolean validarDNI (String dni) throws BuscarDniConLetrasException{
    	
    	for (int i= 0; i<dni.length(); i++) {
    		if(Character.isLetter(dni.charAt(i))) {
    			BuscarDniConLetrasException exc1 = new BuscarDniConLetrasException();
        		throw exc1;
    		}
    	}
    	return true;   	
    }
}