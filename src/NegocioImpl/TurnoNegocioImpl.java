package NegocioImpl;

import java.util.ArrayList;

import Dao.TurnoDAO;
import DaoImpl.TurnoDAOImpl;
import Entidad.Turno;
import Negocio.TurnoNegocio;

public abstract class TurnoNegocioImpl implements TurnoNegocio {
	
	TurnoDAO turno = new TurnoDAOImpl();

	@Override
	public boolean insert(ArrayList<Turno> listaTurnos) {
		
		return turno.insert(listaTurnos);
	}

	@Override
	public ArrayList<Turno> readAll() {
		return turno.readAll();
	}

	@Override
	public Turno devuelveTurno(int id) {
		
		return turno.devuelveTurno(id);
	}
	
	@Override
	public boolean agendarTurno(String dni, Turno turno) {
		
		return turno.agendarTurno(dni, turno);
	}
}
