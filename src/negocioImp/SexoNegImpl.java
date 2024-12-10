package negocioImp;

import java.util.ArrayList;
import java.util.List;

import dao.SexoDao;
import daoImp.SexoDaoImpl;
import entidad.Sexo;
import negocio.SexoNeg;

public class SexoNegImpl implements SexoNeg {
	private SexoDao Sdao = new SexoDaoImpl();
	
	public ArrayList<Sexo> obtenerSexos() {
		
		return Sdao.obtenerSexos();
	}

}
