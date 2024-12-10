package excepciones;

public class BuscarNombreConNumerosException extends RuntimeException{

	
	public BuscarNombreConNumerosException(){
		
		
	}
	
	public BuscarNombreConNumerosException(String message) {
		super(message);
	}
	
	public String getMessage() {
        return "Ingrese solo letras para buscar el Cliente.";
    }
	
}
