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
		// TODO Auto-generated method stub
		return false;
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

}
