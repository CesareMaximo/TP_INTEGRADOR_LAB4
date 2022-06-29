package Negocio;

import java.util.List;

import Entidad.Medico;

public interface MedicoNegocio {

	public boolean insert(Medico me);
	public boolean delete(String dni);
	public List<Medico> readAll();
	public List<Medico> readAllFiltro(int id);
	public List<Medico> readAllBuscar(String nombre);
}
