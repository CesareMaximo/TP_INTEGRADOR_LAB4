package Negocio;

import java.util.List;

import Entidad.Medico;

public interface MedicoNegocio {

	public boolean insert(Medico me);
	public boolean delete(Medico meDelete);
	public List<Medico> readAll();
}
