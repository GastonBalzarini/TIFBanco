package negocio;

import java.util.Map;

public interface InformesNeg {
	public Map<String, Integer> obtenerInformesEstadisticos(String fechaInicio, String fechaFin);
}
