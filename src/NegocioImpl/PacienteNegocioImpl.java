package NegocioImpl;

import java.util.List;

import Entidad.Medico;
import Entidad.Paciente;
import Exceptions.PacienteNotFoundException;
import Negocio.PacienteNegocio;
import Dao.*;
import DaoImpl.*;

public class PacienteNegocioImpl implements PacienteNegocio {

	
	PacienteDAO paDAO = new PacienteDAOImpl();
	@Override
	public boolean insert(Paciente pa) {
		return paDAO.insert(pa);
	}

	@Override
	public boolean delete(String dni) {
		
		return paDAO.delete(dni);
	}

	@Override
	public List<Paciente> readAll() {
		return paDAO.readAll();
	}

	@Override
	public List<Paciente> readAllBuscar(String nombre) {
		
		return paDAO.readAllBuscar(nombre);
	}

	@Override
	public boolean update(Paciente pa) {
		return paDAO.update(pa);
	}

	@Override
	public Paciente mostrarPaciente(String dni) {
		return paDAO.mostrarPaciente(dni);
	}

	@Override
	public boolean existePaciente(String dni) {
		return paDAO.existePaciente(dni);
	}

	@Override
	public boolean existePaciente2(String dni) throws PacienteNotFoundException {
		return paDAO.existePaciente2(dni);
	}

	
}
