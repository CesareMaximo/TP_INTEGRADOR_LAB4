package NegocioImpl;

import java.util.List;

import Dao.NacionalidadDAO;
import DaoImpl.NacionalidadDAOImpl;
import Entidad.Nacionalidad;
import Negocio.NacionalidadNegocio;

public class NacionalidadNegocioImpl implements NacionalidadNegocio {

	NacionalidadDAO nacionalidad = new NacionalidadDAOImpl();

	@Override
	public List<Nacionalidad> readAll() {
		
	return nacionalidad.readAll();
	}

}
