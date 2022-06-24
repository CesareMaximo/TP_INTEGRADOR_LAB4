package DaoImpl;

import java.util.List;

import Dao.PersonaDAO;
import Entidad.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PersonaImpl implements PersonaDAO{

	private static final String insert = "";
	//Baja logica
	private static final String delete = "update Persona set Estado = 0 where DNI like ''";
	private static final String readall = "select *  from Persona"; 
	
	@Override
	public boolean insert(Persona pe) {
		
		return false;
	}

	@Override
	public boolean delete(Persona peDelete) {
		PreparedStatement statement ;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			
			statement.setString(1, peDelete.getDni());
			
			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isInsertExitoso = true;
			}
		} 
		catch (SQLException e) 
		{
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
	public List<Persona> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Persona> personaList = new ArrayList<Persona>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				personaList.add(getPersona(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return personaList;
	}
	
	private Persona getPersona(ResultSet resultSet) throws SQLException
	{
		
		Persona pe = new Persona();
		Nacionalidad na = new Nacionalidad();
		Localidad lo = new Localidad();
		
		pe.setDni(resultSet.getString("DNI"));
		pe.setNombre(resultSet.getString("Nombre"));
		pe.setApellido(resultSet.getString("Apellido"));
		pe.setSexo(resultSet.getString("Sexo").charAt(0));
		//
		na.setIdNacionalidad( resultSet.getInt("idNacionalidad"));
		pe.setnNacionalidad(na);
		//
		pe.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
		pe.setDireccion(resultSet.getString("Direccion"));
		//
		lo.setIdLocalidad(resultSet.getInt("idLocalidad"));
		pe.setlLocalidad(lo);
		//
		pe.setEmail(resultSet.getString("Email"));
		pe.setEstado(resultSet.getBoolean("Estado"));
		
	
		
		return pe;
	}

}
