package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.NacionalidadDAO;
import Entidad.Nacionalidad;
import Entidad.Usuario;

public class NacionalidadDAOImpl implements NacionalidadDAO{

	private static final String readall = "SELECT * FROM Nacionalidad"; 

	
	
	@Override
	public List<Nacionalidad> readAll() {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Nacionalidad> nacList = new ArrayList<Nacionalidad>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Nacionalidad nac = new Nacionalidad();
				nac.setIdNacionalidad(resultSet.getInt("idNacionalidad"));
				nac.setDescripcion(resultSet.getString("Descripcion"));
				nacList.add(nac);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return nacList;
	}



}
