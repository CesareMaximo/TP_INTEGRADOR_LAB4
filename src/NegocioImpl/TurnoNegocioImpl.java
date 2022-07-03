package NegocioImpl;

import java.util.ArrayList;

import Dao.TurnoDAO;
import DaoImpl.TurnoDAOImpl;
import Entidad.Turno;
import Negocio.TurnoNegocio;

public class TurnoNegocioImpl implements TurnoNegocio {
	
	TurnoDAO tur = new TurnoDAOImpl();

	@Override
	public boolean insert(ArrayList<Turno> listaTurnos) {
		
		return tur.insert(listaTurnos);
	}

	@Override
	public ArrayList<Turno> readAll() {
		return tur.readAll();
	}

	@Override
	public Turno devuelveTurno(int id) {
		
		return tur.devuelveTurno(id);
	}

	@Override
	public boolean agendarTurno(String dni, Turno turno) {

		return tur.agendarTurno(dni, turno);
	}

}
