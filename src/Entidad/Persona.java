package Entidad;

import java.sql.Date;

public class Persona {

	private String dni;
	private String nombre;
	private String apellido;
	private char sexo;
	private Nacionalidad nNacionalidad;
	private Date fechaNacimiento;
	private String direccion;
	private Localidad lLocalidad;
	private String email;
	private boolean estado;
	private String telefono1;
	private String telefono2;

	public Persona() {
	}

	public Persona(String dni, String nombre, String apellido, char sexo, Nacionalidad nNacionalidad,
			Localidad lLocalidad, String email, boolean estado, Date fechaNacimiento, String direccion,
			String telefono1, String telefono2) {

		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nNacionalidad = nNacionalidad;
		this.lLocalidad = lLocalidad;
		this.email = email;
		this.estado = estado;
		this.fechaNacimiento = fechaNacimiento;
		this.direccion = direccion;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
	}

	// Getters & Setters
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Nacionalidad getnNacionalidad() {
		return nNacionalidad;
	}

	public void setnNacionalidad(Nacionalidad nNacionalidad) {
		this.nNacionalidad = nNacionalidad;
	}

	public Localidad getlLocalidad() {
		return lLocalidad;
	}

	public void setlLocalidad(Localidad lLocalidad) {
		this.lLocalidad = lLocalidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	// Metodo ToString
	@Override
	public String toString() {
		return "Persona [dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", nNacionalidad=" + nNacionalidad + ", lLocalidad=" + lLocalidad + ", email=" + email + ", estado="
				+ estado + ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + "]";
	}

}
