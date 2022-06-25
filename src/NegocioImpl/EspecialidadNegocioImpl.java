package NegocioImpl;

import java.util.List;

import Dao.EspecialidadDAO;
import DaoImpl.EspeclidadImpl;
import Entidad.Especialidad;
import Negocio.EspecialidadNegocio;

public class EspecialidadNegocioImpl implements EspecialidadNegocio{

	
	EspecialidadDAO dao = new EspeclidadImpl();

	@Override
	public boolean insert(Especialidad es) {
		
		return dao.insert(es);
	}

	@Override
	public boolean delete(Especialidad esDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Especialidad> readAll() {
	
		return dao.readAll();
	}
	
	
	
		
		

}
