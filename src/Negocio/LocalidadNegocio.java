package Negocio;

import java.util.List;

import Entidad.Localidad;


public interface LocalidadNegocio {
 
	public List<Localidad> readAllxid(int idProvincia);
	public List<Localidad> readAll();
}
