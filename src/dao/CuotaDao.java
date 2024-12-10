package dao;

import java.util.ArrayList;
import java.util.List;
import entidad.Cuota;

public interface CuotaDao {
    List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean agregarCuotas(ArrayList<Cuota> listaCuotas);
    public int contarCuotasPagas(int idPrestamo);
}
