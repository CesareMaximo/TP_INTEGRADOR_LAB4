package DaoImpl;
import java.util.List;

import Dao.PacienteDAO;
import Entidad.Paciente;
public class PacienteImpl implements PacienteDAO{

	private static final String insert = "INSERT INTO personas(Dni, Nombre, apellido) VALUES(?, ?, ?)";
	private static final String delete = "delete from personas where Dni like ?";
	private static final String readall = "SELECT * FROM personas"; 
	
	@Override
	public boolean insert(Paciente pa) {
		
		
		return false;
	}

	@Override
	public boolean delete(Paciente paDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Paciente> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
