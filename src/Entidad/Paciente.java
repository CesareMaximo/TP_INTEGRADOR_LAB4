package Entidad;

import java.sql.Date;

public class Paciente extends Persona {

	
	private int idPaciente;
	
	public Paciente() {
		super();
	}
	
	
	public Paciente(int idPaciente, String dni,String nombre,String apellido, char sexo, Nacionalidad nNacionalidad,Date fechaNacimiento,String direccion,
			Localidad lLocalidad,String email,boolean estado, String telefono1, String telefono2) {
		
		super(dni,nombre,apellido,sexo,nNacionalidad,lLocalidad,email,estado,fechaNacimiento,direccion, telefono1, telefono2);
		this.idPaciente = idPaciente;
	}
	
	//Getters & Setters
	public int getIdPaciente() {
		return idPaciente;
	}
	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	
}
