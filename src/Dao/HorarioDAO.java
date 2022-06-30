package Dao;
import java.util.List;

import Entidad.Especialidad;
import Entidad.Horario;

public interface HorarioDAO {

	public List<Horario> readall(int idMedico);
	public boolean Insert(int idMedico,int idHorario);
	public boolean delete(int idMedico,int idHorario);
}
