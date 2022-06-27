package Dao;

import java.util.List;

import Entidad.Localidad;



public interface LocalidadDAO {
	public List<Localidad> readAllxid(int idProvincia);
	public List<Localidad> readAll();
}
