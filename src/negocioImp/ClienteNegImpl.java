package negocioImp;

import java.util.ArrayList;

import dao.ClienteDao;
import daoImp.ClienteDaoImpl;
import entidad.Cliente;
import negocio.ClienteNeg;

public class ClienteNegImpl implements ClienteNeg{
	private ClienteDao clientedao;
	
	public ClienteNegImpl() {
        clientedao = new ClienteDaoImpl();
    }

	
	public ArrayList<Cliente> obtenerClientes() {
        return clientedao.obtenerClientes(); 
    }
	
	public int eliminarCliente(int dni) {
		
		return clientedao.eliminarCliente(dni);
	}
	
	public ArrayList <Cliente> obtenerClientePorNombre(String nombre){
		return clientedao.obtenerClientesPorNombre(nombre);
	}
	
	public Cliente obtenerClientesPorUsuarioObj(String nombre){
		return clientedao.obtenerClientesPorUsuarioObj(nombre);
	}
	
	public ArrayList <Cliente> obtenerClientesPorUsuario(String usuario){
		return clientedao.obtenerClientesPorUsuario(usuario);
		
	}
	
		public Cliente BuscarCliente(Cliente obj) {
		return clientedao.BuscarCliente(obj);
	}
	
	public int modificarCliente(Cliente cliente) {
		return clientedao.modificarCliente(cliente);
	}


	@Override
	public int agregarCliente(Cliente obj) {

		return clientedao.agregarCliente(obj);
	}


	@Override
	public boolean existeDNI(int dni) {
		
		return clientedao.existeDNI(dni);
	}
}
