package Dao;
import java.util.List;

import Entidad.Horario;

public interface HorarioDAO {

	public List<Horario> readall(int idMedico);
}
