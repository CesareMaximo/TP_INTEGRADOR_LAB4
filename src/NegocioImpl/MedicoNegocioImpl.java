package NegocioImpl;

import java.util.List;

import Dao.MedicoDAO;
import DaoImpl.MedicoImpl;
import Entidad.Medico;
import Negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio {

	
	MedicoDAO meDAO = new MedicoImpl();
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
		// TODO Auto-generated method stub
		return null;
	}

}
