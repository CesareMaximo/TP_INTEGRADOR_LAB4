package Dao;

import java.util.List;

import Entidad.Medico;;

public interface MedicoDAO {

	public boolean insert(Medico me);
	public boolean delete(Medico meDelete);
	public List<Medico> readAll();
	public List<Medico> readAllfiltro(int id);
	public List<Medico> readAllBuscar(String nombre);
}
