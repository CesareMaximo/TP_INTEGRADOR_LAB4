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
	private static final String readDias = "SELECT * from Dia_x_Medico WHERE idMedico = ? AND Estado=1";
	private static final String existeHorario = "SELECT CASE WHEN exists ( SELECT * FROM Dia_x_medico WHERE idMedico = ? AND idDia = ? AND Estado = 1) THEN 'TRUE' ELSE 'FALSE' END";
	private static final String estaDeBaja = "SELECT CASE WHEN exists ( SELECT * FROM Dia_x_medico WHERE idMedico = ? AND idDia = ? AND Estado = 0) THEN 'TRUE' ELSE 'FALSE' END";
	private static final String delete = "UPDATE dia_x_medico SET Estado = 0 WHERE idMedico = ? AND idDia = ?";
	private static final String darAlta = "UPDATE dia_x_medico SET Estado = 1, HorarioIngreso = ?, HorarioEgreso = ? WHERE idMedico = ? AND idDia = ?";
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
	public boolean delete(DiaXMedico diaXMedico) {
		
		PreparedStatement statement ;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			statement.setInt(1, diaXMedico.getMedico().getIdMedico().getIdUsuario());
			statement.setInt(2, diaXMedico.getDia().getId());
			
			
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
	public ArrayList<DiaXMedico> readDias(int idMedico) {
		PreparedStatement statement;
		ResultSet resultSet; 
		
		ArrayList<DiaXMedico> dias = new ArrayList<DiaXMedico>();
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readDias);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				DiaXMedico dxm = new DiaXMedico();
				Dia dia = new Dia();
				dia.setId(resultSet.getInt("idDia"));
				dxm.setDia(dia);
				dxm.setHorarioIngreso(resultSet.getTime("horarioIngreso"));
				dxm.setHorarioEgreso(resultSet.getTime("horarioEgreso"));
				
				dias.add(dxm);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return dias;		
	}

	@Override
	public boolean diaTrabajoMedico(int idMedico, int idHorario) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean existe = false;
		ResultSet resultSet;
		try {
			statement = conexion.prepareStatement(existeHorario);
			statement.setInt(1, idMedico);
			statement.setInt(2, idHorario);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				existe = Boolean.valueOf(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return existe;
	}

	@Override
	public boolean darAlta(DiaXMedico diaXMedico) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(darAlta);
		
			statement.setTime(1, diaXMedico.getHorarioIngreso());
			statement.setTime(2, diaXMedico.getHorarioEgreso());
			statement.setInt(3, diaXMedico.getMedico().getIdMedico().getIdUsuario());
			statement.setInt(4, diaXMedico.getDia().getId());
			
			
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
