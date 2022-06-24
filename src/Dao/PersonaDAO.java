package Dao;

import java.util.List;

import Entidad.Medico;
import Entidad.Persona;

public interface PersonaDAO {

	public boolean insert(Persona pe);
	public boolean delete(Persona peDelete);
	public List<Persona> readAll();
}
