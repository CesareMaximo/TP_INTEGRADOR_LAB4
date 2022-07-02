package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.TurnoDAO;
import Entidad.Estado;
import Entidad.Medico;
import Entidad.Paciente;
import Entidad.Persona;
import Entidad.Turno;
import Entidad.Usuario;

public class TurnoDAOImpl implements TurnoDAO {
	
	private static final String insert = "INSERT INTO Turno(idMedico, Fecha, idEstado, Hora) VALUES(?, ?, 1, ?)";
	private static final String readAll = "SELECT * FROM Turno T LEFT JOIN PACIENTE PA ON T.idPaciente = PA.idPaciente LEFT JOIN PERSONA P ON P.DNI = PA.DNI INNER JOIN ESTADOS ES ON T.idEstado = ES.idEstado WHERE T.Fecha >= NOW() ORDER BY T.Fecha ASC";

	@Override
	public boolean insert(ArrayList<Turno> listaTurnos) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		
		try
		{
			statement = conexion.prepareStatement(insert);
			
			for (Turno turno : listaTurnos) {
				statement.setInt(1,turno.getmMedico().getIdMedico().getIdUsuario());
				statement.setDate(2, turno.getFecha());
				statement.setTime(3, turno.getHora());
				if(statement.executeUpdate() > 0)
				{
					conexion.commit();
					isInsertExitoso = true;
				}
				
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
		return isInsertExitoso;
	}

	@Override
	public ArrayList<Turno> readAll() {
		
		ArrayList<Turno> listaTurno = new ArrayList();
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		
		try{
			statement = conexion.prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while (resultSet.next()){
				
				Turno turno = new Turno();
				Medico me = new Medico();
				Usuario usu = new Usuario();
				Paciente pac = new Paciente();
				Estado est = new Estado();
				
				turno.setIdTurno(resultSet.getInt("idTurno"));
				usu.setIdUsuario(resultSet.getInt("idMedico"));
				me.setIdMedico(usu);
				turno.setmMedico(me);
				pac.setIdPaciente(resultSet.getInt("idPaciente"));
				pac.setDni(resultSet.getString("DNI"));
				turno.setpPaciente(pac);
				turno.setFecha(resultSet.getDate("Fecha"));
				est.setIdEstado(resultSet.getInt("idEstado"));
				est.setDescripcion(resultSet.getString("Descripcion"));
				turno.seteEstado(est);
				
				listaTurno.add(turno);
			}
			
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return listaTurno;
	}

}
