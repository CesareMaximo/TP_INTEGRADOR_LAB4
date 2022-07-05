package Negocio;

import java.sql.Date;
import java.util.List;

import Entidad.Medico;

public interface MedicoNegocio {

	public boolean insert(Medico me);
	public boolean insertMe(Medico me);
	public boolean delete(String dni);
	public boolean update(Medico me);
	public List<Medico> readAll();
	public List<Medico> readAllFiltro(int id);
	public List<Medico> readAllBuscar(String nombre);
	public Medico mostrarMedico(int idMedico);
	public int totalPacientesXMedico(int idMedico, Date fecha1, Date fecha2);
}
