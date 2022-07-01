package DaoImpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.EspecialidadDAO;
import Entidad.Especialidad;
import Entidad.Paciente;
import Entidad.Persona;
import Entidad.Usuario;

public class EspeclidadImpl implements EspecialidadDAO{

	
	private static final String insert = " INSERT INTO especialidad(Descripcion) VALUES(?)";
	private static final String delete = "delete from especialidad where idEspecialidad = ?";
	private static final String readall = "select * from especialidad";
	private static final String readallxId = "select * from especialidad where idEspecialidad =?";
	private static final String update = "update especialidad set Descripcion = ? where idEspecialidad = ?";
	
	@Override
	public boolean insert(Especialidad es) {
	
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setString(1, es.getDescripcion());
		
		
			
			
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
	public boolean delete(Especialidad esDelete) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, esDelete.getIdEspecialidad());
			
		
			
			
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
	public List<Especialidad> readAll() {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Especialidad> especialidadList = new ArrayList<Especialidad>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				especialidadList.add(getEspecialidad(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return especialidadList;
	
	}

	private Especialidad getEspecialidad(ResultSet resultSet) throws SQLException
	{
		
		Especialidad es = new Especialidad();
		
		es.setIdEspecialidad(resultSet.getInt("idEspecialidad"));
		es.setDescripcion(resultSet.getString("Descripcion"));
		
		
		return es;
	}

	@Override
	public Especialidad readAllxId(int idEspecialidad) {
		
		
		Connection cn = Conexion.getConexion().getSQLConexion();
		
		Especialidad es = new Especialidad();
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(readallxId);
			statement.setInt(1, idEspecialidad);
			resultSet = statement.executeQuery();
			resultSet.next();
			es = getEspecialidad(resultSet);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return es;	
		
	}

	@Override
	public boolean update(Especialidad esMod) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, esMod.getDescripcion());
			statement.setInt(2, esMod.getIdEspecialidad());
		
		
			
			
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
	
	
	

	
	
	
}
