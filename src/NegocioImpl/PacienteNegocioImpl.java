package NegocioImpl;

import java.util.List;

import Entidad.Medico;
import Entidad.Paciente;
import Negocio.PacienteNegocio;
import Dao.*;
import DaoImpl.*;

public class PacienteNegocioImpl implements PacienteNegocio {

	
	PacienteDAO paDAO = new PacienteImpl();
	@Override
	public boolean insert(Paciente pa) {
		return paDAO.insert(pa);
	}

	@Override
	public boolean delete(Paciente paDelete) {
		// TODO Auto-generated method stub
		return false;
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

	
}
