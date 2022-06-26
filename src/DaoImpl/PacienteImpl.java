package DaoImpl;
import java.sql.CallableStatement;
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

	private static final String delete = "";
	private static final String readall = "select * from paciente as pa inner join persona as pe on pe.DNI like pa.DNI"; 
	
	@Override
	public boolean insert(Paciente pa) {
		Connection conexion = null;
		boolean isInsertExitoso = false;
		try {
			conexion = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = conexion.prepareCall("call registrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			cst.setString(1, pa.getDni());
			cst.setString(2, pa.getNombre());
			cst.setString(3, pa.getApellido());
			cst.setString(4, String.valueOf(pa.getSexo()));
			cst.setInt(5, pa.getnNacionalidad().getIdNacionalidad());
			cst.setDate(6, pa.getFechaNacimiento());
			cst.setString(7, pa.getDireccion());
			cst.setInt(8, pa.getlLocalidad().getIdLocalidad());
			cst.setString(9, pa.getEmail());
			cst.setBoolean(10, true);
			cst.setString(11, pa.getTelefono1());
			cst.setString(12, pa.getTelefono2());

			if (cst.executeUpdate()>0) {
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
		//
		pa.setTelefono1(resultSet.getString("Telefono1"));
		pa.setTelefono2(resultSet.getString("Telefono2"));
		
	
		
		return pa;
	}
	
	public List<Paciente> readAllBuscar(String nombre){
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Paciente> listaPaciente = new ArrayList<Paciente>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("Select * from paciente as pa inner join persona as pe on pa.DNI=pe.DNI where pe.DNI like "+"'%"+nombre+"%' or pe.Nombre like "+"'%"+nombre+"%' or pe.Apellido like "+"'%"+nombre+"%' or pe.Email like "+"'%"+nombre+"%'");
			
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
