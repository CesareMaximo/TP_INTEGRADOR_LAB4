package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.LocalidadDAO;
import Entidad.Localidad;
import Entidad.Provincia;

public class LocalidadDAOImpl implements LocalidadDAO{

	private static final String readallxid = "Select * from Localidad where idProvincia = ?";
	private static final String readall = "Select * from Localidad ";
	
	@Override
	public List<Localidad> readAllxid(int idProvincia) {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Localidad> localidadList = new ArrayList<Localidad>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallxid);
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

	@Override
	public List<Localidad> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Localidad> localidadList = new ArrayList<Localidad>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Localidad loc = new Localidad();
				
				loc.setIdLocalidad(resultSet.getInt("idLocalidad"));
				loc.setDescripcion(resultSet.getString("descripcion"));		
				Provincia po = new Provincia();
				po.setIdProvincia(resultSet.getInt("idProvincia"));
				loc.setpProvincia(po);
				
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
