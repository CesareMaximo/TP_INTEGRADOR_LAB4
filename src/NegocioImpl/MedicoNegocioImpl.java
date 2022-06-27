package NegocioImpl;

import java.util.List;

import Dao.MedicoDAO;
import DaoImpl.MedicoDAOImpl;
import Entidad.Medico;
import Negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio {

	
	MedicoDAO meDAO = new MedicoDAOImpl();
	
	@Override
	public boolean insert(Medico me) {
		return meDAO.insert(me);
	}

	@Override
	public boolean delete(Medico meDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Medico> readAll() {
		
		return meDAO.readAll();
	}

	@Override
	public List<Medico> readAllFiltro(int id) {
		
		return meDAO.readAllfiltro(id);
	}

	@Override
	public List<Medico> readAllBuscar(String nombre) {
		
		return meDAO.readAllBuscar(nombre);
	}

}
