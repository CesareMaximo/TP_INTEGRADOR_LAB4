package Entidad;

import java.sql.Date;
import java.sql.Time;

public class Turno {

	private int idTurno;
	private Medico mMedico;
	private Date fecha;
	private Paciente pPaciente;
	private Estado eEstado;
	private Time hora;
	private String observacion;
	
	
	public Turno() {}
	
	
	public Turno(int idTurno, Medico mMedico, Date fecha, Paciente pPaciente,
			Estado eEstado, Time hora, String observacion) {
		super();
		this.idTurno = idTurno;
		this.mMedico = mMedico;

		this.fecha = fecha;
		this.pPaciente = pPaciente;
		this.eEstado = eEstado;
		this.hora = hora;
		this.observacion = observacion;
	}
	
	//Getters & Setters
	public int getIdTurno() {
		return idTurno;
	}
	public void setIdTurno(int idTurno) {
		this.idTurno = idTurno;
	}
	public Medico getmMedico() {
		return mMedico;
	}
	public void setmMedico(Medico mMedico) {
		this.mMedico = mMedico;
	}

	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Paciente getpPaciente() {
		return pPaciente;
	}
	public void setpPaciente(Paciente pPaciente) {
		this.pPaciente = pPaciente;
	}
	public Estado geteEstado() {
		return eEstado;
	}
	public void seteEstado(Estado eEstado) {
		this.eEstado = eEstado;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	//Metodo ToString
	@Override
	public String toString() {
		return "Turno [idTurno=" + idTurno + ", mMedico=" + mMedico + ", fecha="
				+ fecha + ", pPaciente=" + pPaciente + ", eEstado=" + eEstado + ", hora=" + hora + ", observacion="
				+ observacion + "]";
	}
	
	
	
	
}
