package Negocio;

import java.sql.Date;
import java.util.ArrayList;

import Entidad.Turno;

public interface TurnoNegocio {
	
	public boolean insert (ArrayList<Turno> listaTurnos);
	
	public ArrayList<Turno> readAll();
	
	public Turno devuelveTurno(int id);
	
	public boolean agendarTurno(String dni, Turno turno);
	
	public boolean existeFechaTurno(int idMedico, Date fecha);
	
	public ArrayList<Turno> readPorMedico(int idMedico);

	public boolean update2(int idTurno, int estado, String observacion);
	
	public boolean liberarTurno (int idTurno);
	
	public int totalAusentes(Date fecha1, Date fecha2);
	
	public int totalAtendidosPorMes(int mes, int anio);
	
	public int total(int anio);

	public int totalPresentes(int anio);
	
	public ArrayList<Turno> filtroFechaEstado(int idEstado, Date fecha);
	
	public ArrayList<Turno> filtroFecha(Date fecha);
	
	public ArrayList<Turno> filtroEstado(int idEstado);
	
public ArrayList<Turno> filtroFechaEstado(int idEstado, Date fecha, int idMedico);
	
	public ArrayList<Turno> filtroFecha(Date fecha, int idMedico);
	
	public ArrayList<Turno> filtroEstado(int idEstado, int idMedico);
	
}
