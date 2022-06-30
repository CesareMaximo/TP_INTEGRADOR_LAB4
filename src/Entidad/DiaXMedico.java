package Entidad;

import java.sql.Time;

public class DiaXMedico {

	private Medico medico;
	private Dia dia;
	private Time horarioIngreso;
	private Time horarioEgreso;
	private boolean estado;
	
	
	public DiaXMedico() {}


	public DiaXMedico(Medico medico, Dia dia, Time horarioIngreso, Time horarioEgreso, boolean estado) {
		super();
		this.medico = medico;
		this.dia = dia;
		this.horarioIngreso = horarioIngreso;
		this.horarioEgreso = horarioEgreso;
		this.estado = estado;
	}



	public Medico getMedico() {
		return medico;
	}


	public void setMedico(Medico medico) {
		this.medico = medico;
	}


	public Dia getDia() {
		return dia;
	}


	public void setDia(Dia dia) {
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


	public boolean isEstado() {
		return estado;
	}


	public void setEstado(boolean estado) {
		this.estado = estado;
	}


	@Override
	public String toString() {
		return "DiaXMedico [medico=" + medico + ", dia=" + dia + ", horarioIngreso=" + horarioIngreso
				+ ", horarioEgreso=" + horarioEgreso + ", estado=" + estado + "]";
	}

	
	
	
}
