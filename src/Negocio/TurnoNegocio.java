package Negocio;

import java.util.ArrayList;

import Entidad.Turno;

public interface TurnoNegocio {
	
	public boolean insert (ArrayList<Turno> listaTurnos);
	public ArrayList<Turno> readAll();
	public Turno devuelveTurno(int id);
	public boolean agendarTurno(String dni, Turno turno);
}
