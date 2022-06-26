package NegocioImpl;

import java.util.List;

import Entidad.Localidad;
import Negocio.LocalidadNegocio;

public class LocalidadNegocioImpl implements LocalidadNegocio {

	@Override
	public List<Localidad> readAll(int idProvincia) {
		LocalidadNegocio loc = new LocalidadNegocioImpl();
		
		return loc.readAll(idProvincia);
	}

}
