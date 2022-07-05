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

	
}
