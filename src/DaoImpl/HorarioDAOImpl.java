package DaoImpl;

import java.sql.Connection;
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
	private static final String insert = "insert into medico_x_horario (idMedico,idHorario) values (?,?)";
	
	private static final String delete = "delete from medico_x_horario where idMedico = ? and idHorario = ?";
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

	@Override
	public boolean Insert(int idMedico, int idHorario) {
	

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, idMedico);
			statement.setInt(2, idHorario);
			
			
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
	public boolean delete(int idMedico, int idHorario) {
		
		PreparedStatement statement ;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, idMedico);
			statement.setInt(2, idHorario);
			
			
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
