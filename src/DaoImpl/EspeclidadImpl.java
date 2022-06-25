package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.EspecialidadDAO;
import Entidad.Especialidad;
import Entidad.Persona;
import Entidad.Usuario;

public class EspeclidadImpl implements EspecialidadDAO{

	
	private static final String insert = " INSERT INTO especlidad(Descripcion) VALUES(?)";
	private static final String delete = "";
	private static final String readall = "select * from especialidad";
	
	@Override
	public boolean insert(Especialidad es) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Especialidad esDelete) {
		// TODO Auto-generated method stub
		return false;
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
	
	
	
}
