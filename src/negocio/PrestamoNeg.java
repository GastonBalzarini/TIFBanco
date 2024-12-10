package negocio;

import java.util.ArrayList;

import entidad.Prestamos;

public interface PrestamoNeg {
	public ArrayList<Prestamos> obtenerPrestamosPendientes();
	public int cambiarCondicionPrestamo(Prestamos obj);
	public ArrayList<Prestamos> obtenerTodosLosPrestamos();
	public ArrayList<Prestamos> obtenerPrestamosCliente(int cli);
	public ArrayList<Prestamos> ObtenerPretamosPorCondicion(int con);
	public int agregarPrestamo(Prestamos obj);
	public int obtenerUltimoIDPrestamo();
	public ArrayList<Prestamos> obtenerPrestamosClienteAceptados(int Cliente);
	public int RechazarPrestamo(int idPrestamo);
	public int marcarPrestamoComoPago(int idPrestamo);
}
