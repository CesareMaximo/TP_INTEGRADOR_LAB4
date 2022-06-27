package NegocioImpl;

import java.util.List;

import Dao.LocalidadDAO;
import DaoImpl.LocalidadDAOImpl;
import Entidad.Localidad;
import Negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	
	LocalidadDAO loc = new LocalidadDAOImpl();
	@Override
	public List<Localidad> readAll() {
		
		
		return loc.readAll();
	}

	@Override
	public List<Localidad> readAllxid(int idProvincia) {
		// TODO Auto-generated method stub
		return null;
	}

}
