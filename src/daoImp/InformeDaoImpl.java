package daoImp;

import dao.InformeDao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class InformeDaoImpl implements InformeDao {

    private Conexion conexion;

    public InformeDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public Map<String, Integer> obtenerInformesEstadisticos(String fechaInicio, String fechaFin) {
        Map<String, Integer> informes = new HashMap<>();
        Statement statement = null;

        try {
            // Abrimos la conexion
            conexion.Open();

            // Creamos un Statement
            statement = conexion.connection.createStatement();

      /*      // Consulta 1: Cantidad de clientes creados en el rango de fechas
            String queryClientes = "SELECT COUNT(*) AS total FROM clientes WHERE fecha_creacion BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            ResultSet rsClientes = statement.executeQuery(queryClientes);
            if (rsClientes.next()) {
                informes.put("Cantidad de clientes creados", rsClientes.getInt("total"));
            }*/

            // Consulta 2: Numero total de transferencias realizadas en el rango de fechas
            String queryTransferencias = "SELECT COUNT(*) AS total FROM transferencias WHERE Fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            ResultSet rsTransferencias = statement.executeQuery(queryTransferencias);
            if (rsTransferencias.next()) {
                informes.put("Numero total de transferencias realizadas", rsTransferencias.getInt("total"));
            }

            // Consulta 3: Transferencias mayores a $100,000 realizadas en el rango de fechas
            String queryTransferenciasMayores = "SELECT COUNT(*) AS total FROM transferencias WHERE Importe > 100000 AND Fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            ResultSet rsTransferenciasMayores = statement.executeQuery(queryTransferenciasMayores);
            if (rsTransferenciasMayores.next()) {
                informes.put("Transferencias mayores a $100,000", rsTransferenciasMayores.getInt("total"));
            }

            // Consulta 4: Prestamos pagados a tiempo en el rango de fechas
            String queryPrestamosPagados = "SELECT COUNT(*) AS total FROM prestamos WHERE Condicion = 4 AND Fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            ResultSet rsPrestamosPagados = statement.executeQuery(queryPrestamosPagados);
            if (rsPrestamosPagados.next()) {
                informes.put("Cantidad de prestamos pagados", rsPrestamosPagados.getInt("total"));
            }

            // Consulta 5: Prestamos con pagos pendientes en el rango de fechas
            String queryPrestamosPendientes = "SELECT COUNT(*) AS total FROM prestamos WHERE Condicion = 3 AND Fecha BETWEEN '" + fechaInicio + "' AND '" + fechaFin + "'";
            ResultSet rsPrestamosPendientes = statement.executeQuery(queryPrestamosPendientes);
            if (rsPrestamosPendientes.next()) {
                informes.put("Cantidad de prestamos pendientes", rsPrestamosPendientes.getInt("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return informes;
    }
}