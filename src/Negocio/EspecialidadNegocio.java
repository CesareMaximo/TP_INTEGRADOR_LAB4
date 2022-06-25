package Negocio;

import java.util.List;

import Entidad.Especialidad;

public interface EspecialidadNegocio {

	public boolean insert(Especialidad es);
	public boolean delete(Especialidad esDelete);
	public List<Especialidad> readAll();
	
}
