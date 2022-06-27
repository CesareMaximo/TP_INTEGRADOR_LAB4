package Dao;

import java.util.List;

import Entidad.Paciente;

public interface PacienteDAO {

	public boolean insert(Paciente pa);
	public boolean delete(Paciente paDelete);
	public List<Paciente> readAll();
	public List<Paciente> readAllBuscar(String nombre);
	public boolean update(Paciente pa);
	public Paciente mostrarPaciente(String dni);
}
