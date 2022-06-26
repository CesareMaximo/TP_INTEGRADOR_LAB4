package Entidad;

import java.sql.Date;

public class Medico extends Persona{

	
	private String dni;
	private int idMedico;
	private Especialidad eEspecialidad;
	private Horario hHorario;
	
	public Medico() {
		super();
	}
	
	
	public Medico(String dni, int idMedico, Especialidad eEspecialidad, Horario hHorario,String nombre
			,String apellido, char sexo, Nacionalidad nNacionalidad,Date fechaNacimiento,String direccion,Localidad lLocalidad,String email,boolean estado, String telefono1, String telefono2) {
		
		super(dni,nombre,apellido,sexo,nNacionalidad,lLocalidad,email,estado,fechaNacimiento,direccion, telefono1, telefono2);
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
	public int getIdMedico() {
		return idMedico;
	}
	public void setIdMedico(int idMedico) {
		this.idMedico = idMedico;
	}
	public Especialidad geteEspecialidad() {
		return eEspecialidad;
	}
	public void seteEspecialidad(Especialidad eEspecialidad) {
		this.eEspecialidad = eEspecialidad;
	}
	public Horario gethHorario() {
		return hHorario;
	}
	public void sethHorario(Horario hHorario) {
		this.hHorario = hHorario;
	}
	
	//Metodo Tostring
	@Override
	public String toString() {
		return "Medico [dni=" + dni + ", idMedico=" + idMedico + ", eEspecialidad=" + eEspecialidad + ", hHorario="
				+ hHorario + "]";
	}
	
	
	
	
}
