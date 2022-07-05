package NegocioImpl;

import java.sql.Date;
import java.util.List;

import Dao.MedicoDAO;
import DaoImpl.MedicoDAOImpl;
import Entidad.Medico;
import Negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio {

	
	MedicoDAO meDAO = new MedicoDAOImpl();
	
	@Override
	public boolean insert(Medico me) {
		return meDAO.insert(me);
	}

	@Override
	public boolean delete(String dni) {
		return meDAO.delete(dni);
		
	}

	@Override
	public List<Medico> readAll() {
		
		return meDAO.readAll();
	}

	@Override
	public List<Medico> readAllFiltro(int id) {
		
		return meDAO.readAllfiltro(id);
	}

	@Override
	public List<Medico> readAllBuscar(String nombre) {
		
		return meDAO.readAllBuscar(nombre);
	}

	@Override
	public Medico mostrarMedico(int idMedico) {
		return meDAO.mostrarMedico(idMedico);
	}

	@Override
	public boolean insertMe(Medico me) {
		return meDAO.insertMe(me);
	}

	@Override
	public boolean update(Medico me) {
		
		return meDAO.update(me);
	}

	@Override
	public int totalPacientesXMedico(int idMedico, Date fecha1, Date fecha2) {
		return meDAO.totalPacientesXMedico(idMedico, fecha1, fecha2);
	}

}
