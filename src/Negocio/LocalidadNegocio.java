package Negocio;

import java.util.List;

import Entidad.Localidad;


public interface LocalidadNegocio {
 
	public List<Localidad> readAll(int idProvincia);
}
