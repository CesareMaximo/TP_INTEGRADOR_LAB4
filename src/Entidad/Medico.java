package Entidad;

public class Medico extends Persona{

	
	private String dni;
	private Usuario idMedico;
	private Especialidad eEspecialidad;
	private DiaXMedico hHorario;
	
	public Medico() {
		super();
	}
	
	public Medico(String dni, Usuario idMedico, Especialidad eEspecialidad, DiaXMedico hHorario) {
		super();
		this.dni = dni;
		this.idMedico = idMedico;
		this.eEspecialidad = eEspecialidad;
		this.hHorario = hHorario;
	}

	//Getters & Setters
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public Usuario getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(Usuario idMedico) {
		this.idMedico = idMedico;
	}
	public Especialidad geteEspecialidad() {
		return eEspecialidad;
	}
	public void seteEspecialidad(Especialidad eEspecialidad) {
		this.eEspecialidad = eEspecialidad;
	}
	public DiaXMedico gethHorario() {
		return hHorario;
	}
	public void sethHorario(DiaXMedico hHorario) {
		this.hHorario = hHorario;
	}
	
	//Metodo Tostring
	@Override
	public String toString() {
		return "Medico [dni=" + dni + ", idMedico=" + idMedico + ", eEspecialidad=" + eEspecialidad + ", hHorario="
				+ hHorario + "]";
	}
	
	
	
	
}
