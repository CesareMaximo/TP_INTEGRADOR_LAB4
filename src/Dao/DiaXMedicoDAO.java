package Dao;
import java.sql.Time;
import java.util.List;

import Entidad.Especialidad;
import Entidad.DiaXMedico;

public interface DiaXMedicoDAO {

	public List<DiaXMedico> readall(int idMedico);
	public boolean Insert(DiaXMedico diaXMedico);
	public boolean delete(int idMedico,int idHorario);
}
