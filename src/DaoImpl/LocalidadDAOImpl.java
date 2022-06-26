package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.LocalidadDAO;
import Entidad.Localidad;

public class LocalidadDAOImpl implements LocalidadDAO{

	private static final String readall = "Select * from Localidad where idProvincia = ?";
	
	@Override
	public List<Localidad> readAll(int idProvincia) {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Localidad> localidadList = new ArrayList<Localidad>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, idProvincia);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Localidad loc = new Localidad();
				
				loc.setIdLocalidad(resultSet.getInt("idLocalidad"));
				loc.setDescripcion(resultSet.getString("descripcion"));
				loc.setIdLocalidad(resultSet.getInt("idProvincia"));
				
				localidadList.add(loc);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return localidadList;
	}

}
