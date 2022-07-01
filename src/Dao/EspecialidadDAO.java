package Dao;

import java.util.List;

import Entidad.Especialidad;

public interface EspecialidadDAO {

	public boolean insert(Especialidad es);
	public boolean delete(Especialidad esDelete);
	public List<Especialidad> readAll();
	public Especialidad readAllxId(int idEspecialidad);
	public boolean update(Especialidad esMod);
}
