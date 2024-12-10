package entidad;

import java.sql.Date;

public class Cuota {
    private int idCuota; // Identificador único de la cuota
    private int idPrestamo; // Relación con el préstamo al que pertenece la cuota
    private Date fechaPago; // Fecha de pago (puede ser tipo LocalDate para un manejo moderno)
    private float importe; // Monto de la cuota
    private boolean pagado; // Estado de pago: true (pagada), false (pendiente)

    // Constructor vacío
    public Cuota() {}

    // Constructor completo
    public Cuota(int idCuota, int idPrestamo, Date fechaPago, float importe, boolean pagado) {
        this.idCuota = idCuota;
        this.idPrestamo = idPrestamo;
        this.fechaPago = fechaPago;
        this.importe = importe;
        this.pagado = pagado;
    }

    // Métodos getter y setter para cada atributo
    public int getIdCuota() {
        return idCuota;
    }

    public void setIdCuota(int idCuota) {
        this.idCuota = idCuota;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date date) {
        this.fechaPago = date;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    // Representación en formato String para depuración o pruebas
    @Override
    public String toString() {
        return "Cuota{" +
               "idCuota=" + idCuota +
               ", idPrestamo=" + idPrestamo +
               ", fechaPago='" + fechaPago + '\'' +
               ", importe=" + importe +
               ", pagado=" + pagado +
               '}';
    }
}

