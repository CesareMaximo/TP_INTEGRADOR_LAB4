package Negocio;


import java.util.ArrayList;
import java.util.List;

import Entidad.DiaXMedico;

public interface DiaXMedicoNegocio {
	
	public List<DiaXMedico> readall(int idMedico);
	public boolean insert (DiaXMedico diaXMedico);
	public ArrayList<DiaXMedico> readDias(int idMedico);
	public boolean diaTrabajoMedico(int idMedico,int idHorario);
	public boolean darAlta(DiaXMedico diaXMedico);
	public boolean delete(DiaXMedico diaXMedico);
	public boolean estadoBaja(DiaXMedico diaXMedico);
}
