package NegocioImpl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Dao.DiaXMedicoDAO;
import DaoImpl.DiaXMedicoDAOImpl;
import Entidad.DiaXMedico;
import Negocio.DiaXMedicoNegocio;

public class DiaXMedicoNegocioImpl implements DiaXMedicoNegocio{
	
	DiaXMedicoDAO hoDAO = new DiaXMedicoDAOImpl();

	@Override
	public List<DiaXMedico> readall(int idMedico) {
		return hoDAO.readall(idMedico);
	}

	@Override
	public boolean insert(DiaXMedico diaXMedico) {
	
		return hoDAO.Insert(diaXMedico);
	}

	@Override
	public ArrayList<DiaXMedico> readDias(int idMedico) {
		 
		return hoDAO.readDias(idMedico);
	}

	@Override
	public boolean diaTrabajoMedico(int idMedico, int idHorario) {
		return hoDAO.diaTrabajoMedico(idMedico, idHorario);
	}

}
