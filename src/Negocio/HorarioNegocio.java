package Negocio;

import java.util.List;

import Entidad.Horario;

public interface HorarioNegocio {
	
	public List<Horario> readall(int idMedico);
}
