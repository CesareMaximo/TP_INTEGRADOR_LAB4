package NegocioImpl;

import java.sql.Date;
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

	@Override
	public boolean existeFechaTurno(int idMedico, Date fecha) {
		return tur.existeFechaTurno(idMedico, fecha);
	}

	@Override
	public ArrayList<Turno> readPorMedico(int idMedico) {
		
		return tur.readPorMedico(idMedico);
	}

	@Override
	public boolean update2(int idTurno, int estado, String observacion) {

		return tur.update2(idTurno, estado, observacion);
	}

	@Override
	public boolean liberarTurno(int idTurno) {
		return tur.liberarTurno(idTurno);
	}

	@Override
	public int totalAusentes(Date fecha1, Date fecha2) {
		
		return tur.totalAusentes(fecha1, fecha2);
	}

	@Override
	public int totalAtendidosPorMes(int mes, int anio) {
		return tur.totalAtendidosPorMes(mes, anio);
	}

	@Override
	public int total(int anio) {
		return tur.total(anio);
	}

	@Override
	public int totalPresentes(int anio) {
		
		return tur.totalPresentes(anio);
	}

	@Override
	public ArrayList<Turno> filtroFechaEstado(int idEstado, Date fecha) {
		
		return tur.filtroFechaEstado(idEstado, fecha);
	}

	@Override
	public ArrayList<Turno> filtroFecha(Date fecha) {

		return tur.filtroFecha(fecha);
	}

	@Override
	public ArrayList<Turno> filtroEstado(int idEstado) {
		return tur.filtroEstado(idEstado);
	}

	@Override
	public ArrayList<Turno> filtroFechaEstado(int idEstado, Date fecha, int idMedico) {
		
		return tur.filtroFechaEstado(idEstado, fecha, idMedico);
	}

	@Override
	public ArrayList<Turno> filtroFecha(Date fecha, int idMedico) {
		return tur.filtroFecha(fecha, idMedico);
	}

	@Override
	public ArrayList<Turno> filtroEstado(int idEstado, int idMedico) {
		return tur.filtroEstado(idEstado, idMedico);
	}

}
