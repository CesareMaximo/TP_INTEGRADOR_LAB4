package Entidad;

import java.sql.Date;

public class Paciente extends Persona {

	
	private int idPaciente;
	private String dni;
	
	public Paciente() {
		super();
	}
	
	
	public Paciente(int idPaciente, String dni,String nombre,String apellido, char sexo, Nacionalidad nNacionalidad,Date fechaNacimiento,String direccion,
			Localidad lLocalidad,String email,boolean estado) {
		
		super(dni,nombre,apellido,sexo,nNacionalidad,lLocalidad,email,estado,fechaNacimiento,direccion);
		this.idPaciente = idPaciente;
		this.dni = dni;
	}
	
	//Getters & Setters
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
}
