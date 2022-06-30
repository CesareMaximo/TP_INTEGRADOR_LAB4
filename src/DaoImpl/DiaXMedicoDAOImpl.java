package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Entidad.Dia;
import Entidad.DiaXMedico;
import Entidad.Localidad;
import Entidad.Provincia;
import Dao.DiaXMedicoDAO;

public class DiaXMedicoDAOImpl implements DiaXMedicoDAO{
	
	private static final String readall = "SELECT dxm.idDia as idD, dxm.idMedico, dxm.HorarioIngreso, dxm.HorarioEgreso, d.Descripcion FROM Dia_x_Medico as dxm inner join Dia as d on dxm.idDia = d.idDia WHERE dxm.idMedico = ? and dxm.Estado = 1";
	private static final String insert = "insert into Dia_x_Medico (idDia, idMedico, HorarioIngreso, HorarioEgreso, Estado) values (?,?,?,?,?)";
	private static final String delete = "delete from medico_x_horario where idMedico = ? and idHorario = ?";
	
	
	@Override
	public List<DiaXMedico> readall(int idMedico) {
		
		PreparedStatement statement;
		ResultSet resultSet; 
		
		ArrayList<DiaXMedico> horarioList = new ArrayList<DiaXMedico>();
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				DiaXMedico ho = new DiaXMedico();
				Dia dia = new Dia();
				dia.setId(resultSet.getInt("idD"));
				dia.setDescripcion(resultSet.getString("Descripcion"));
				ho.setDia(dia);
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
	public boolean Insert(DiaXMedico diaXMedico) {
	

		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, diaXMedico.getDia().getId());
			statement.setInt(2, diaXMedico.getMedico().getIdMedico().getIdUsuario());
			statement.setTime(3, diaXMedico.getHorarioIngreso());
			statement.setTime(4, diaXMedico.getHorarioEgreso());
			statement.setBoolean(5, true);
			
			
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
