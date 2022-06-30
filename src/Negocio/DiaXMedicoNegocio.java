package Negocio;

import java.sql.Time;
import java.util.List;

import Entidad.DiaXMedico;

public interface DiaXMedicoNegocio {
	
	public List<DiaXMedico> readall(int idMedico);
	public boolean insert (DiaXMedico diaXMedico);
}
