package Dao;

import java.sql.Date;
import java.util.List;

import Entidad.Medico;;

public interface MedicoDAO {

	public boolean insert(Medico me); //inserta usuario y persona
	public boolean insertMe(Medico me); //inserta medico al obtener el ultimo idusuario
	public boolean delete(String dni);
	public boolean update(Medico me);
	public List<Medico> readAll();
	public List<Medico> readAllfiltro(int id);
	public List<Medico> readAllBuscar(String nombre);
	public Medico mostrarMedico(int idMedico);
	public int totalPacientesXMedico(int idMedico, Date fecha1, Date fecha2);
	public boolean existeMedico(String dni);
}
