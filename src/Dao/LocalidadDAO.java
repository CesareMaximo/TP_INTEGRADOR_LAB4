package Dao;

import java.util.List;

import Entidad.Localidad;



public interface LocalidadDAO {
	public List<Localidad> readAll(int idProvincia);
}
