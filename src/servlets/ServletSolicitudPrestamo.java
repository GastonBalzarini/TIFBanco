package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.CondicionPrestamos;
import entidad.Cuota;
import entidad.Cuentas;
import entidad.Prestamos;
import negocio.CuentasNeg;
import negocio.CuotaNeg;
import negocio.PrestamoNeg;
import negocioImp.CuentasNegImpl;
import negocioImp.CuotaNegImpl;
import negocioImp.PrestamoNegImpl;

@WebServlet("/ServletSolicitudPrestamo")
public class ServletSolicitudPrestamo extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletSolicitudPrestamo() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrestamoNeg pNeg = new PrestamoNegImpl();
        ArrayList<Prestamos> listPre = pNeg.obtenerPrestamosPendientes();
        request.setAttribute("listaPrestamo", listPre);
        request.getRequestDispatcher("VentanaPrestamosBanco.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrestamoNeg pNeg = new PrestamoNegImpl();
        CuentasNeg cue = new CuentasNegImpl();
        Prestamos obj = new Prestamos();
        Cuentas cuenta = new Cuentas();
        ArrayList<Prestamos> listPre = new ArrayList<>();
        CondicionPrestamos cp = new CondicionPrestamos();
        
        if (request.getParameter("btnAprobar") != null) {
            String numcuenta = request.getParameter("NumCuenta");
            int nc = Integer.parseInt(numcuenta);
            cuenta.setNumeroCuenta(nc);

            String importe = request.getParameter("Importe");
            float imp = 0;

            if (importe != null && !importe.isEmpty()) {
                try {
                    // Reemplaza las comas por puntos en caso de formato decimal con coma
                    //importe = importe.replace(',', '.');
                    // Convierte la cadena a float
                    imp = Float.parseFloat(importe);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir el importe a float: " + importe);
                    e.printStackTrace();
                }
            } else {
                System.out.println("El par�metro 'Importe' es nulo o est� vac�o.");
            }

            try {
                // Obtener el saldo actual de la cuenta desde la base de datos
                //float saldoActual = cue.obtenerSaldo(nc); // M�todo que deber�as tener en CuentasNegImpl

                // Sumar el importe al saldo actual
                //float nuevoSaldo = saldoActual + imp;

                // Actualizar el saldo en el objeto cuenta
                //cuenta.setSaldo(nuevoSaldo);

                cue.modificarSaldoNumCue(nc, imp);
                System.out.println("Monto a agregar: " + imp);
            } catch (Exception e) {
                System.out.println("Error al obtener o actualizar el saldo de la cuenta: " + e.getMessage());
                e.printStackTrace();
            }


            String numCuotasStr = request.getParameter("PlazoDePago");
            System.out.println("Valor recibido de PlazoDePago: " + numCuotasStr);

            int numCuotas = 0;
            if (numCuotasStr != null && !numCuotasStr.trim().isEmpty()) {
                try {
                    numCuotas = Integer.parseInt(numCuotasStr);
                    System.out.println("Valor convertido de PlazoDePago: " + numCuotas);
                } catch (NumberFormatException e) {
                    System.out.println("Error al convertir PlazoDePago a entero: " + numCuotasStr);
                    e.printStackTrace();
                }
            } else {
                System.out.println("PlazoDePago es nulo o vac�o");
            }

            
            int idPrestamo = Integer.parseInt(request.getParameter("id_prestamo"));
            obj.setIDPrestamo(idPrestamo);

          

            ArrayList<Cuota> listaCuotas = new ArrayList<>();
            float cuotaMonto = imp / numCuotas;
            System.out.println("Valor cuotaMonto: " + cuotaMonto);

           
            for (int i = 1; i <= numCuotas; i++) {
                Cuota cuota = new Cuota();
              cuota.setIdCuota(i);
                // Aqu� nos aseguramos de que el monto de la cuota se formatee correctamente antes de asignarlo
               
                cuota.setImporte(cuotaMonto); 
                
                cuota.setPagado(false);
                cuota.setIdPrestamo(idPrestamo);
                listaCuotas.add(cuota);
            }

            CuotaNeg cuotaNeg = new CuotaNegImpl();
            boolean cuotasAgregadas = cuotaNeg.agregarCuotas(listaCuotas);

            if (cuotasAgregadas) {
                cp.setIdCondicion(1);
                obj.setCondicion(cp);
                int filas = pNeg.cambiarCondicionPrestamo(obj);

                if (filas != 0) {
                    request.setAttribute("condicion", "El prestamo fue aprobado y las cuotas se registraron.");
                }
            } else {
                request.setAttribute("condicion", "Error al registrar las cuotas.");
            }
        }
        
        if (request.getParameter("btnRechazar") != null) {
            int idPrestamo = Integer.parseInt(request.getParameter("id_prestamo"));
        	pNeg.RechazarPrestamo(idPrestamo);
            request.setAttribute("condicion", "El prestamo fue rechazado.");

        }
        

        // Actualizamos la lista de prestamos pendientes
        listPre = pNeg.obtenerPrestamosPendientes();
        request.setAttribute("listaPrestamo", listPre);
        request.getRequestDispatcher("VentanaPrestamosBanco.jsp").forward(request, response);
    }
}

