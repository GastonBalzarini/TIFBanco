package dao;

import java.util.ArrayList;

import entidad.Cliente;
import entidad.Cuentas;

public interface ClienteDao {
	public int agregarCliente (Cliente obj);
	public ArrayList<Cliente> obtenerClientes();
	public int eliminarCliente(int dni);
	public ArrayList<Cliente> obtenerClientesPorNombre(String nombre);
	public Cliente obtenerClientesPorUsuarioObj(String nombre);
	public int modificarCliente(Cliente obj);
	public Cliente BuscarCliente(Cliente obj);
	public boolean existeDNI(int dni);
	public ArrayList <Cliente> obtenerClientesPorUsuario(String usuario);
}
