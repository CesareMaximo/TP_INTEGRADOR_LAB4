package Negocio;

import java.util.List;

import Entidad.Paciente;

public interface PacienteNegocio {

	public boolean insert(Paciente pa);
	public boolean delete(String dni);
	public List<Paciente> readAll();
	public List<Paciente> readAllBuscar(String nombre);
	public boolean update(Paciente pa);
	public Paciente mostrarPaciente(String dni);
	public boolean existePaciente(String dni);
}
