package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Dao.ProvinciaDAO;
import Entidad.Provincia;

public class ProvinciaDAOImpl implements ProvinciaDAO {

	private static final String readall = "Select * from Provincia";
	
	@Override
	public List<Provincia> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Provincia> provList = new ArrayList<Provincia>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Provincia pro = new Provincia();
				pro.setIdProvincia(resultSet.getInt("idProvincia"));
				pro.setDescripcion(resultSet.getString("Descripcion"));
				 provList.add(pro);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return provList;
	}

}
