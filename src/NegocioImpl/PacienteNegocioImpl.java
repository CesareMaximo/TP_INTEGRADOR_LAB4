package NegocioImpl;

import java.util.List;

import Entidad.Paciente;
import Negocio.PacienteNegocio;
import Dao.*;
import DaoImpl.*;

public class PacienteNegocioImpl implements PacienteNegocio {

	
	PacienteDAO paDAO = new PacienteImpl();
	@Override
	public boolean insert(Paciente pa) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Paciente paDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Paciente> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
