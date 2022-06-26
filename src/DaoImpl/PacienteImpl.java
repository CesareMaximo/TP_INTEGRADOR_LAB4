package DaoImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Entidad.*;
import Dao.PacienteDAO;
import javafx.css.PseudoClass;

public class PacienteImpl implements PacienteDAO{

	private static final String insert = "";
	private static final String delete = "";
	private static final String readall = "select * from paciente as pa inner join persona as pe on pe.DNI like pa.DNI"; 
	
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
		ArrayList<Paciente> pacienteList = new ArrayList<Paciente>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				pacienteList.add(getPaciente(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return pacienteList;
	}

	private Paciente getPaciente(ResultSet resultSet) throws SQLException {
		Paciente pa = new Paciente();
		
		Nacionalidad na = new Nacionalidad();
		Localidad lo = new Localidad();
		
		pa.setDni(resultSet.getString("DNI"));
		pa.setIdPaciente(resultSet.getInt("idPaciente"));

		pa.setNombre(resultSet.getString("Nombre"));
		pa.setApellido(resultSet.getString("Apellido"));
		pa.setSexo(resultSet.getString("Sexo").charAt(0));
		//
		na.setIdNacionalidad( resultSet.getInt("idNacionalidad"));
		pa.setnNacionalidad(na);
		//
		pa.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
		pa.setDireccion(resultSet.getString("Direccion"));
		//
		lo.setIdLocalidad(resultSet.getInt("idLocalidad"));
		pa.setlLocalidad(lo);
		//
		pa.setEmail(resultSet.getString("Email"));
		pa.setEstado(resultSet.getBoolean("Estado"));
		
	
		
		return pa;
	}
	
	public List<Paciente> readAllBuscar(String nombre){
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Paciente> listaPaciente = new ArrayList<Paciente>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("select * from paciente as pa inner join persona as pe.DNI like "+"'%"+nombre+"%' or pe.Nombre like "+"'%"+nombre+"%' ");
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				listaPaciente.add(getPaciente(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listaPaciente;
	}
}
