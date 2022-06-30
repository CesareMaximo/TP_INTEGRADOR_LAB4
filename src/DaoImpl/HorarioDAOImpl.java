package DaoImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidad.Horario;
import Entidad.Localidad;
import Entidad.Provincia;
import Dao.HorarioDAO;

public class HorarioDAOImpl implements HorarioDAO{
	
	private static final String readall = "SELECT * FROM Horario AS H INNER JOIN medico_x_horario AS MXH ON H.idHorario = MXH.idHorario WHERE idMedico = ?";

	@Override
	public List<Horario> readall(int idMedico) {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Horario> horarioList = new ArrayList<Horario>();
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Horario ho = new Horario();
				
				ho.setIdHorario(resultSet.getInt("idHorario"));
				ho.setDia(resultSet.getInt("Dia"));
				ho.setHorarioIngreso(resultSet.getTime("HorarioIngreso"));		
				ho.setHorarioEgreso(resultSet.getTime("HorarioEgreso"));
				
				horarioList.add(ho);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return horarioList;		
	}

}
