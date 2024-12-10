package daoImp;

import dao.CuotaDao;
import entidad.Cuota;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

public class CuotaDaoImpl implements CuotaDao {

    private Conexion conexion;

    // Constructor que inicializa la conexión
    public CuotaDaoImpl() {
        this.conexion = new Conexion();
    }

    @Override
    public List<Cuota> obtenerCuotasPorPrestamo(int idPrestamo) {
        List<Cuota> cuotas = new ArrayList<>();
        Statement statement = null;

        try {
            // Abre la conexión
            conexion.Open();

            // Crea un Statement
            statement = conexion.connection.createStatement();

            // Consulta para obtener las cuotas del préstamo
            String query = "SELECT * FROM cuotas WHERE idPrestamo = " + idPrestamo;
            ResultSet rs = statement.executeQuery(query);

            // Itera sobre los resultados
            while (rs.next()) {
                Cuota cuota = new Cuota();
                cuota.setIdCuota(rs.getInt("idCuota"));
                cuota.setIdPrestamo(rs.getInt("idPrestamo"));
                cuota.setImporte( rs.getFloat("importe"));
                cuota.setFechaPago(rs.getDate("fechapago"));
                cuota.setPagado(rs.getBoolean("pagado"));

                cuotas.add(cuota);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Cierra el statement y la conexión
                if (statement != null) statement.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return cuotas;
    }

    public boolean pagarCuota(int idCuota,int idPrestamo, double monto, HttpServletRequest request) {
        PreparedStatement stmt = null;
        boolean operacionExitosa = false;

        try {
            // Abre la conexión
            conexion.Open();

            // 1. Verificar si la cuenta tiene saldo suficiente
            String sqlSaldo = "SELECT c.Saldo " +
                              "FROM cuentas c " +
                              "JOIN prestamos p ON c.DNI = p.Cliente " +
                              "JOIN cuotas q ON p.IDPrestamo = q.IDPrestamo " +
                              "WHERE q.IDCuota = ? AND q.IDPrestamo=?";

            stmt = conexion.connection.prepareStatement(sqlSaldo);
            stmt.setInt(1, idCuota);
            stmt.setInt(2, idPrestamo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                double saldoCuenta = rs.getDouble("Saldo");
                System.out.println("Saldo de cuenta: " + saldoCuenta);
                // Verificar si el saldo es suficiente
                if (saldoCuenta < monto) {
                    request.setAttribute("mensajeError", "Saldo insuficiente en la cuenta para realizar el pago.");
                    return false;  // Termina la ejecución si no hay saldo suficiente
                }
            } else {
                request.setAttribute("mensajeError", "No se encontró la cuenta asociada al préstamo.");
                return false;
            }

            // 2. Actualizar el saldo de la cuenta asociada
            String sqlActualizarSaldo = "UPDATE cuentas c " +
                                        "JOIN prestamos p ON c.DNI = p.Cliente " +
                                        "JOIN cuotas q ON p.IDPrestamo = q.IDPrestamo " +
                                        "SET c.Saldo = c.Saldo - ? " +
                                        "WHERE q.IDCuota = ? AND q.IDPrestamo = ?";
            stmt = conexion.connection.prepareStatement(sqlActualizarSaldo);
            stmt.setDouble(1, monto);
            stmt.setInt(2, idCuota);
            stmt.setInt(3, idPrestamo);
            int filasAfectadasSaldo = stmt.executeUpdate();
            System.out.println("Filas afectadas por la actualización del saldo: " + filasAfectadasSaldo);
            if (filasAfectadasSaldo == 0) {
                request.setAttribute("mensajeError", "No se pudo actualizar el saldo de la cuenta.");
                return false;
            }
            System.out.println(idCuota);
            // 3. Marcar la cuota como pagada y establecer la fecha de pago
            String sqlMarcarPagada = "UPDATE cuotas SET cuotas.Pagado = 1, FechaPago = ? WHERE IDCuota = ? AND IDPrestamo = ?";
            stmt = conexion.connection.prepareStatement(sqlMarcarPagada);
            stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));  // Establecer la fecha actual
            stmt.setInt(2, idCuota);
            stmt.setInt(3, idPrestamo);
            int filasAfectadasCuota = stmt.executeUpdate();
            System.out.println("Filas afectadas por la actualización de la cuota: " + filasAfectadasCuota);
            if (filasAfectadasCuota == 0) {
                request.setAttribute("mensajeError", "No se pudo marcar la cuota como pagada.");
                return false;
            }

            operacionExitosa = true;
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return operacionExitosa;
    }


    @Override
    public boolean agregarCuotas(ArrayList<Cuota> listaCuotas) {
        Statement statement = null;
        boolean isSuccess = false;

        try {
            // Abre la conexión
            conexion.Open();

            // Crea un Statement
            statement = conexion.connection.createStatement();

            // Itera sobre la lista de cuotas e inserta cada una
            for (Cuota cuota : listaCuotas) {
              
              

             // Obtén el importe y formatealo para usar punto como separador decimal
             float importe = cuota.getImporte();
             String importeFormateado = String.format(Locale.US, "%.2f", importe);

             // Construye la consulta
             String query = String.format(
                 "INSERT INTO cuotas (IDCuota, IDPrestamo,  Importe, Pagado) " +
                 "VALUES (%d, %d, %s, %d)", 
                 cuota.getIdCuota(),
                 cuota.getIdPrestamo(),
                
                 importeFormateado,  // Asegúrate de usar el importe formateado
                 cuota.isPagado() ? 1 : 0  // Convierte booleano a 0 o 1
             );                    		
         
                // Muestra la consulta en la consola para depuración
                System.out.println("Consulta SQL: " + query);
                
                statement.executeUpdate(query);
            }

            isSuccess = true;

        } catch (Exception e) {
            e.printStackTrace();
            isSuccess = false;
        } finally {
            try {
                // Cierra el statement y la conexión
                if (statement != null) statement.close();
                conexion.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return isSuccess;
    }
    
	public int contarCuotasPagas(int idPrestamo) {
	    conexion = new Conexion();  
	    conexion.Open(); // Abre la conexiï¿½n
	    String query = "SELECT COUNT(IDPrestamo) as cantidad FROM cuotas WHERE IDPrestamo = ? and pagado = 1";
	    int cantidadCuotasPagas = 0;

	    try (PreparedStatement pst = conexion.connection.prepareStatement(query)) {
	        pst.setInt(1, idPrestamo);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	            	cantidadCuotasPagas = rs.getInt("cantidad");
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error al contar las cuotas: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        conexion.close(); // Cierra la conexiï¿½n
	    }

	    return cantidadCuotasPagas;
	}



}
