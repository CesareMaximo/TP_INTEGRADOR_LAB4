package NegocioImpl;

import java.util.List;

import Dao.ProvinciaDAO;
import DaoImpl.ProvinciaDAOImpl;
import Entidad.Provincia;
import Negocio.ProvinciaNegocio;

public class ProvinciaNegocioImpl implements ProvinciaNegocio {

	@Override
	public List<Provincia> readAll() {
		ProvinciaDAO pDao = new ProvinciaDAOImpl();
		return pDao.readAll();
	}

}
