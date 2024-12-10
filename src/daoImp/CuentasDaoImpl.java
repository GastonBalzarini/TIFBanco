package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import dao.CuentasDao;
import entidad.Cuentas;

public class CuentasDaoImpl implements CuentasDao {
    private Conexion cn;

    public CuentasDaoImpl() {
        
    }

    @Override
    public int obtenerUltimoNumeroCuenta() {
        cn = new Conexion();  // Asumiendo que "Conexion" es tu clase que maneja la conexi�n a la base de datos
        cn.Open(); // Abre la conexi�n
        String query = "SELECT MAX(NumeroCuenta) AS ultimoNumeroCuenta FROM Cuentas";
        int ultimoNumeroCuenta = 0;

        try (PreparedStatement pst = cn.connection.prepareStatement(query); 
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                ultimoNumeroCuenta = rs.getInt("ultimoNumeroCuenta");
                if (ultimoNumeroCuenta == 0) {
                    System.out.println("No hay cuentas en la base de datos, el �ltimo n�mero es 0.");
                } else {
                    System.out.println("�ltimo n�mero de cuenta: " + ultimoNumeroCuenta);
                }
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el �ltimo n�mero de cuenta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cierra la conexi�n
        }

        return ultimoNumeroCuenta;
    }


    public String obtenerUltimoCBU() {
        cn = new Conexion(); // Tu clase para la conexi�n
        cn.Open(); // Abre la conexi�n
        String query = "SELECT MAX(CBU) AS ultimoCBU FROM Cuentas";
        String ultimoCBU = "0"; // Valor inicial

        try (PreparedStatement pst = cn.connection.prepareStatement(query);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next()) {
                ultimoCBU = rs.getString("ultimoCBU"); // Obtiene el CBU como cadena
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el �ltimo CBU: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cierra la conexi�n
        }

        return ultimoCBU;
    }

    @Override
    public boolean agregarCuenta(Cuentas cuenta) {
        cn = new Conexion();  // Asumiendo que "Conexion" es tu clase que maneja la conexi�n a la base de datos
        cn.Open(); // Abre la conexi�n
        String query = "INSERT INTO Cuentas (DNI, TipoCuenta, NumeroCuenta, Cbu, Saldo, FechaCreacion) VALUES (?, ?, ?, ?, ?, ?)";
        boolean cuentaAgregada = false;

        try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
            pst.setInt(1, cuenta.getDniCliente());
            pst.setString(2, cuenta.getTipoCuenta());
            pst.setInt(3, cuenta.getNumeroCuenta());
            pst.setString(4, cuenta.getCbu());
            pst.setFloat(5, cuenta.getSaldo());
            pst.setDate(6, cuenta.getFechaCreacion());
            
            int filasAfectadas = pst.executeUpdate();
            if (filasAfectadas > 0) {
                cuentaAgregada = true;
            }
        } catch (Exception e) {
            System.out.println("Error al agregar la cuenta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cn.close(); // Cierra la conexi�n
        }

        return cuentaAgregada;
    }


	@Override
	public ArrayList<Cuentas> ObtenerCuentas() {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cuentas> listCue = new ArrayList<>();
		String query = "SELECT DNI, NumeroCuenta, CBU, Saldo, TipoCuenta FROM bdbanco.cuentas WHERE estado = 1";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cuentas cue = new Cuentas();
				cue.setDniCliente(rs.getInt("DNI"));
				cue.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				cue.setCbu(rs.getString("CBU"));
				cue.setSaldo(rs.getFloat("Saldo"));
				cue.setTipoCuenta(rs.getString("TipoCuenta"));
				listCue.add(cue);
			}
		}catch (Exception e) {
			System.out.println("Error al obtener cuentas: " + e.getMessage());
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listCue;
	}
	
	
	
	

	@Override
	public int eliminarCuenta(int NumeroCuenta) {
		cn = new Conexion();
	    cn.Open();
	    int filasAfectadas = 0;
	    String query = "UPDATE cuentas SET estado = FALSE WHERE NumeroCuenta = ? AND estado = 1";

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, NumeroCuenta); 
	        filasAfectadas = pst.executeUpdate(); 
	    } catch (Exception e) {
	        System.out.println("Error al actualizar el estado de la Cuenta: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }

	    return filasAfectadas;
	}

	@Override
	public Cuentas obtenerCuentaPorNumero(int numeroCuenta) {
	    Cuentas cuenta = null;
	    cn = new Conexion();
	    cn.Open();
	    
	    String query = "SELECT NumeroCuenta, CBU, TipoCuenta, Saldo, DNI, FechaCreacion FROM bdbanco.cuentas WHERE NumeroCuenta = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, numeroCuenta);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            cuenta = new Cuentas();
	            cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
	            cuenta.setCbu(rs.getString("CBU"));
	            cuenta.setTipoCuenta(rs.getString("TipoCuenta"));
	            cuenta.setSaldo(rs.getFloat("Saldo"));
	            cuenta.setDniCliente(rs.getInt("DNI"));
	            cuenta.setFechaCreacion(rs.getDate("FechaCreacion"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return cuenta;
	}
	
	
	
	

	@Override
	public boolean modificarCuenta(Cuentas cuenta) {
	    boolean actualizado = false;
	    cn = new Conexion();
	    cn.Open();

	    String query = "UPDATE bdbanco.cuentas SET CBU = ?, TipoCuenta = ?, Saldo = ? WHERE NumeroCuenta = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        // Establecer los par�metros en la consulta
	        pst.setString(1, cuenta.getCbu());
	        pst.setString(2, cuenta.getTipoCuenta());
	        pst.setFloat(3, cuenta.getSaldo());
	        pst.setInt(4, cuenta.getNumeroCuenta());

	        // Ejecutar la actualizaci�n y verificar si afect� alguna fila
	        actualizado = pst.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return actualizado;
	}

	public void bajarCuentasDeUsuario(int DNI) {
		cn = new Conexion();
		cn.Open(); 
		String query = "UPDATE cuentas SET estado = 0 WHERE DNI = ?"; 
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, DNI); 
	        pst.executeUpdate(); 
	    } catch (Exception e) {
	        System.out.println("Error al bajar cuentas del cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }
	}

	@Override
	public void transferirPrestamo(Cuentas cuenta) {
		cn = new Conexion();
		cn.Open(); 
		String query = "UPDATE cuentas SET Saldo = ? WHERE numerocuenta = ?"; 
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setFloat(1, cuenta.getSaldo());
	        pst.setInt(2, cuenta.getNumeroCuenta());
	        pst.executeUpdate(); 
	    } catch (Exception e) {
	        System.out.println("Error al depositar el saldo: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); 
	    }
		
	}

	@Override
	public ArrayList<Cuentas> ObtenerCuentasPorDni(int dni) {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cuentas> listCue = new ArrayList<>();
		String query = "SELECT DNI, NumeroCuenta, CBU, Saldo, TipoCuenta FROM bdbanco.cuentas WHERE estado = 1 AND DNI=? ";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setInt(1, dni);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cuentas cue = new Cuentas();
				cue.setDniCliente(rs.getInt("DNI"));
				cue.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				cue.setCbu(rs.getString("CBU"));
				cue.setSaldo(rs.getFloat("Saldo"));
				cue.setTipoCuenta(rs.getString("TipoCuenta"));
				listCue.add(cue);
			}
		}catch (Exception e) {
			System.out.println("Error al obtener cuentas: " + e.getMessage());
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listCue;
	}
	
	public Cuentas ObtenerCuentasPorDniobj(int dni) {
	    cn = new Conexion();
	    Cuentas cue = null;
	    
	    try {
	        cn.Open();
	        String query = "SELECT DNI, NumeroCuenta, CBU, Saldo, TipoCuenta FROM bdbanco.cuentas WHERE estado = 1 AND DNI = ?";
	        try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	            pst.setInt(1, dni);
	            ResultSet rs = pst.executeQuery();
	             if (rs.next()) {
	                cue = new Cuentas();
	                cue.setDniCliente(rs.getInt("DNI"));
	                cue.setNumeroCuenta(rs.getInt("NumeroCuenta"));
	                cue.setCbu(rs.getString("CBU"));
	                cue.setSaldo(rs.getFloat("Saldo"));
	                cue.setTipoCuenta(rs.getString("TipoCuenta"));
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error al obtener cuentas: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    return cue;
	}
	

	public int contarCuentasPorCliente(int dniCliente) {
	    cn = new Conexion();  
	    cn.Open(); // Abre la conexi�n
	    String query = "SELECT COUNT(*) AS cantidad FROM Cuentas WHERE DNI = ?";
	    int cantidadCuentas = 0;

	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, dniCliente);
	        try (ResultSet rs = pst.executeQuery()) {
	            if (rs.next()) {
	                cantidadCuentas = rs.getInt("cantidad");
	            }
	        }
	    } catch (Exception e) {
	        System.out.println("Error al contar las cuentas del cliente: " + e.getMessage());
	        e.printStackTrace();
	    } finally {
	        cn.close(); // Cierra la conexi�n
	    }

	    return cantidadCuentas;
	}
	
	public ArrayList<Cuentas> ObtenerCuentasPorNumCue(String CBU) {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cuentas> listCue = new ArrayList<>();
		String query = "SELECT DNI, NumeroCuenta, CBU, Saldo, TipoCuenta FROM bdbanco.cuentas WHERE estado = 1 AND NumeroCuenta=? ";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setString(1, CBU);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cuentas cue = new Cuentas();
				cue.setDniCliente(rs.getInt("DNI"));
				cue.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				cue.setCbu(rs.getString("CBU"));
				cue.setSaldo(rs.getFloat("Saldo"));
				cue.setTipoCuenta(rs.getString("TipoCuenta"));
				listCue.add(cue);
			}
		}catch (Exception e) {
			System.out.println("Error al obtener cuentas: " + e.getMessage());
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listCue;
	}
	
	public void modificarSaldo(String cbu, float monto) {
	    cn = new Conexion();
	    cn.Open();
	    String query = "UPDATE cuentas SET Saldo = Saldo + ? WHERE CBU = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setFloat(1, monto); // Monto a sumar o restar
	        pst.setString(2, cbu); // CBU del usuario
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	}
	
	public void modificarSaldoNumCue(int id, float monto) {
	    cn = new Conexion();
	    cn.Open();
	    String query = "UPDATE cuentas SET Saldo = Saldo + ? WHERE NumeroCuenta = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	    	System.out.println("Monto agregado: " + monto);
	    	pst.setFloat(1, monto); // Monto a sumar o restar
	        pst.setInt(2, id); // id de la cuenta
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	}

	public Cuentas ObtenerCuentaPorUsuario(String usuario) {
		cn = new Conexion();
		cn.Open();
		Cuentas cuenta = new Cuentas();
		String query = "SELECT NumeroCuenta, CBU, Saldo, cuentas.DNI FROM cuentas INNER JOIN clientes ON cuentas.DNI = clientes.DNI WHERE clientes.Usuario = ? ";
		
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setString(1, usuario);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				cuenta.setCbu(rs.getString("CBU"));
				cuenta.setSaldo(rs.getFloat("Saldo"));
				cuenta.setDniCliente(rs.getInt("cuentas.DNI"));
			}
		}catch (Exception e) {
			System.out.println("No se encontró una cuenta para el usuario: " + e.getMessage());
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuenta;
	}

	public Cuentas obtenerNroCuentaPorCBU(String CBU) {
	    Cuentas cuenta = null;
	    cn = new Conexion();
	    cn.Open();
	    
	    String query = "SELECT NumeroCuenta, CBU, TipoCuenta, Saldo, DNI, FechaCreacion FROM bdbanco.cuentas WHERE CBU = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setString(1, CBU);
	        ResultSet rs = pst.executeQuery();
	        
	        if (rs.next()) {
	            cuenta = new Cuentas();
	            cuenta.setNumeroCuenta(rs.getInt("NumeroCuenta"));
	            cuenta.setCbu(rs.getString("CBU"));
	            cuenta.setTipoCuenta(rs.getString("TipoCuenta"));
	            cuenta.setSaldo(rs.getFloat("Saldo"));
	            cuenta.setDniCliente(rs.getInt("DNI"));
	            cuenta.setFechaCreacion(rs.getDate("FechaCreacion"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }
	    
	    return cuenta;
	}

	@Override
	public float obtenerSaldo(int numeroCuenta) {
	    float saldo = 0;
	    cn = new Conexion();
	    cn.Open();

	    String query = "SELECT Saldo FROM bdbanco.cuentas WHERE NumeroCuenta = ?";
	    try (PreparedStatement pst = cn.connection.prepareStatement(query)) {
	        pst.setInt(1, numeroCuenta);
	        ResultSet rs = pst.executeQuery();

	        if (rs.next()) {
	            saldo = rs.getFloat("Saldo");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        cn.close();
	    }

	    return saldo;
	}


	public ArrayList<Cuentas> ObtenerCuentasPorDniLIKE(int dni) {
		cn = new Conexion();
		cn.Open();
		ArrayList <Cuentas> listCue = new ArrayList<>();
		String query = "SELECT DNI, NumeroCuenta, CBU, Saldo, TipoCuenta FROM bdbanco.cuentas WHERE estado = 1 AND CAST(dni AS CHAR) LIKE ?";
		try (PreparedStatement pst = cn.connection.prepareStatement(query)){
			pst.setString(1, "%" + dni + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Cuentas cue = new Cuentas();
				cue.setDniCliente(rs.getInt("DNI"));
				cue.setNumeroCuenta(rs.getInt("NumeroCuenta"));
				cue.setCbu(rs.getString("CBU"));
				cue.setSaldo(rs.getFloat("Saldo"));
				cue.setTipoCuenta(rs.getString("TipoCuenta"));
				listCue.add(cue);
			}
		}catch (Exception e) {
			System.out.println("Error al obtener cuentas: " + e.getMessage());
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return listCue;
	}

}
