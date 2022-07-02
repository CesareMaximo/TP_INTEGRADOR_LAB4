package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.tomcat.util.threads.ResizableExecutor;

import Dao.TurnoDAO;
import Entidad.Especialidad;
import Entidad.Estado;
import Entidad.Medico;
import Entidad.Paciente;
import Entidad.Persona;
import Entidad.Turno;
import Entidad.Usuario;

public class TurnoDAOImpl implements TurnoDAO {
	
	private static final String insert = "INSERT INTO Turno(idMedico, Fecha, idEstado, Hora) VALUES(?, ?, 1, ?)";
	private static final String readAll = "SELECT T.idTurno, T.idMedico, T.Fecha, T.idPaciente, (SELECT PE.Nombre FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteNombre, (SELECT PE.Apellido FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteApellido, (SELECT PE.DNI FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteDNI, T.idEstado, ES.NombreEstado, T.Hora, T.Observaci�n,(SELECT P.Apellido FROM medico AS M INNER JOIN Persona P ON P.DNI =M.DNI WHERE M.idMedico = T.idMedico) as MedicoApellido, (SELECT P.Nombre FROM medico AS M INNER JOIN Persona P ON P.DNI =M.DNI WHERE M.idMedico = T.idMedico) as MedicoNombre, (SELECT E.idEspecialidad FROM Especialidad E INNER JOIN Medico M ON M.idEspecialidad = E.idEspecialidad WHERE M.idMedico = T.idMedico) AS idEspe, (SELECT E.Descripcion FROM Especialidad E INNER JOIN Medico M ON M.idEspecialidad = E.idEspecialidad WHERE M.idMedico = T.idMedico) AS idDesEspe FROM Turno T LEFT JOIN PACIENTE PA ON T.idPaciente = PA.idPaciente LEFT JOIN PERSONA P ON P.DNI = PA.DNI LEFT JOIN ESTADOS ES ON T.idEstado = ES.idEstado WHERE T.Fecha >= NOW() ORDER BY T.Fecha ASC";

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
				Especialidad espe = new Especialidad();
				
				turno.setIdTurno(resultSet.getInt("idTurno"));
				usu.setIdUsuario(resultSet.getInt("idMedico"));
				espe.setDescripcion(resultSet.getString("idDesEspe"));
				espe.setIdEspecialidad(resultSet.getInt("idEspe"));
				me.setIdMedico(usu);
				me.seteEspecialidad(espe);
				me.setApellido(resultSet.getString("MedicoNombre"));
				me.setNombre(resultSet.getString("MedicoApellido"));
				turno.setmMedico(me);
				pac.setIdPaciente(resultSet.getInt("idPaciente"));
				pac.setDni(resultSet.getString("PacienteDNI"));
				pac.setApellido(resultSet.getString("PacienteApellido"));
				pac.setNombre(resultSet.getString("PacienteNombre"));
				turno.setpPaciente(pac);
				turno.setFecha(resultSet.getDate("Fecha"));
				est.setIdEstado(resultSet.getInt("idEstado"));
				est.setDescripcion(resultSet.getString("NombreEstado"));
				turno.seteEstado(est);
				turno.setHora(resultSet.getTime("Hora"));
				turno.setObservacion(resultSet.getString("Observaci�n"));
				
				listaTurno.add(turno);
			}
			
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return listaTurno;
	}

}