package negocio;

import java.util.ArrayList;

import entidad.Cliente;

public interface ClienteNeg {
	
	public int agregarCliente (Cliente obj);

	public ArrayList<Cliente> obtenerClientes();
	
	public int eliminarCliente(int dni);
	
	public ArrayList <Cliente> obtenerClientePorNombre(String nombre);
	
	public Cliente obtenerClientesPorUsuarioObj(String nombre);
	
	public Cliente BuscarCliente(Cliente obj);
	
	public int modificarCliente(Cliente cliente);
	
	public boolean existeDNI(int dni);
	
	public ArrayList <Cliente> obtenerClientesPorUsuario(String usuario);
	
}
