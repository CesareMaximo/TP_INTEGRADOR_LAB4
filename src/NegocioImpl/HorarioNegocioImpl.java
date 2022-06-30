package NegocioImpl;

import java.util.List;

import Dao.HorarioDAO;
import DaoImpl.HorarioDAOImpl;
import Entidad.Horario;
import Negocio.HorarioNegocio;

public class HorarioNegocioImpl implements HorarioNegocio{
	
	HorarioDAO hoDAO = new HorarioDAOImpl();

	@Override
	public List<Horario> readall(int idMedico) {
		return hoDAO.readall(idMedico);
	}

}
