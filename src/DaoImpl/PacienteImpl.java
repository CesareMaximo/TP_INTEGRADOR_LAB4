package DaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.PacienteDAO;
import Entidad.Paciente;
import javafx.css.PseudoClass;

public class PacienteImpl implements PacienteDAO{

	private static final String insert = "INSERT INTO Paciente(Dni) VALUES(?)";
	private static final String delete = "delete from personas where Dni like ?";
	private static final String readall = "SELECT * FROM Paciente"; 
	
	@Override
	public boolean insert(Paciente pa) {
				
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try {
			statement = conexion.prepareStatement(insert);
			statement.setString(1, pa.getDni());

			if (statement.executeUpdate() > 0) {
				conexion.commit();
				isInsertExitoso = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return isInsertExitoso;
	}

	@Override
	public boolean delete(Paciente paDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Paciente> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Paciente> lista = new ArrayList<Paciente>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				lista.add(getPaciente(resultSet));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}

	private Paciente getPaciente(ResultSet resultSet) throws SQLException {
		int id = resultSet.getInt("idPaciente");
		String dni = resultSet.getString("Dni");
		Paciente paciente = new Paciente();
		paciente.setDni(dni);
		paciente.setIdPaciente(id);
		
		return paciente;
	}
}
