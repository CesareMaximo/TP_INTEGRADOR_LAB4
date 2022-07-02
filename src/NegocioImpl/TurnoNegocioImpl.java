package NegocioImpl;

import java.util.ArrayList;

import Dao.TurnoDAO;
import DaoImpl.TurnoDAOImpl;
import Entidad.Turno;
import Negocio.TurnoNegocio;

public class TurnoNegocioImpl implements TurnoNegocio {
	
	TurnoDAO turno = new TurnoDAOImpl();

	@Override
	public boolean insert(ArrayList<Turno> listaTurnos) {
		
		return turno.insert(listaTurnos);
	}

	@Override
	public ArrayList<Turno> readAll() {
		return turno.readAll();
	}

}
