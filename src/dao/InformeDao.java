package dao;

import java.util.Map;

public interface InformeDao
{
    public Map<String, Integer> obtenerInformesEstadisticos(String fechaInicio, String fechaFin);
}
