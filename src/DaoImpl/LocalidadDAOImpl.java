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

	private static final String readallxid = "Select Lo.idLocalidad, Lo.Descripcion as DescripcionLo, Pro.idProvincia, Pro.Descripcion as DescripcionPro from Localidad Lo INNER JOIN Provincia Pro ON Pro.idProvincia = Lo.idProvincia where Lo.idProvincia = ?";
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
				Provincia pro = new Provincia();
				
				pro.setIdProvincia(resultSet.getInt("idProvincia"));
				pro.setDescripcion(resultSet.getString("DescripcionPro"));
				loc.setIdLocalidad(resultSet.getInt("idLocalidad"));
				loc.setDescripcion(resultSet.getString("DescripcionLo"));
				loc.setpProvincia(pro);
				
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
