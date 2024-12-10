package excepciones;

public class BuscarDniConLetrasException extends RuntimeException{

	public BuscarDniConLetrasException() {
		
	}
	
	public BuscarDniConLetrasException(String message) {
        super(message);
    }
	
	public String getMessage() {
        return "Ingrese solo números para buscar el DNI.";
    }
	
}
