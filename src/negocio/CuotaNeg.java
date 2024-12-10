package negocio;



import java.util.ArrayList;
import java.util.List;

import entidad.Cuota;

public interface CuotaNeg {
    List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo);
    boolean agregarCuotas(ArrayList<Cuota> listaCuotas);
    public int contarCuotasPagas(int idPrestamo);
    
}
