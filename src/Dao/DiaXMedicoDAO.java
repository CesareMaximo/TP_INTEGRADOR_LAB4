package Dao;
import java.util.ArrayList;
import java.util.List;
import Entidad.DiaXMedico;

public interface DiaXMedicoDAO {

	public List<DiaXMedico> readall(int idMedico);
	public boolean Insert(DiaXMedico diaXMedico);
	public boolean delete(int idMedico,int idHorario);
	public ArrayList<DiaXMedico> readDias(int idMedico);
}
