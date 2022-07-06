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
		return dao.delete(esDelete);
	}

	@Override
	public List<Especialidad> readAll() {
	
		return dao.readAll();
	}

	@Override
	public Especialidad readAllxId(int idEspecialidad) {
		
		return dao.readAllxId(idEspecialidad);
	}

	@Override
	public boolean update(Especialidad esMod) {
		
		return dao.update(esMod);
	}

	@Override
	public boolean existe(String des) {
		
		return dao.existe(des);
	}
	
	
	
		
		

}
