package Entidad;

import java.sql.Time;

public class Horario {

	private int idHorario;
	private int dia;
	private Time horarioIngreso;
	private Time horarioEgreso;
	
	
	public Horario() {}
	
	public Horario(int idHorario, int dia, Time horarioIngreso, Time horarioEgreso) {
		super();
		this.idHorario = idHorario;
		this.dia = dia;
		this.horarioIngreso = horarioIngreso;
		this.horarioEgreso = horarioEgreso;
	}
	//Getters & Setters
	
	public int getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(int idHorario) {
		this.idHorario = idHorario;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public Time getHorarioIngreso() {
		return horarioIngreso;
	}
	public void setHorarioIngreso(Time horarioIngreso) {
		this.horarioIngreso = horarioIngreso;
	}
	public Time getHorarioEgreso() {
		return horarioEgreso;
	}
	public void setHorarioEgreso(Time horarioEgreso) {
		this.horarioEgreso = horarioEgreso;
	}
	
	//Metodo ToString
	@Override
	public String toString() {
		return "Horario [idHorario=" + idHorario + ", dia=" + dia + ", horarioIngreso=" + horarioIngreso
				+ ", horarioEgreso=" + horarioEgreso + "]";
	}
	
	
	
}
