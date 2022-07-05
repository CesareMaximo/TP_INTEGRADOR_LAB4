package DaoImpl;

import java.sql.Connection;
import java.sql.Date;
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
import Negocio.PacienteNegocio;
import NegocioImpl.PacienteNegocioImpl;

public class TurnoDAOImpl implements TurnoDAO {
	
	private static final String insert = "INSERT INTO Turno(idMedico, Fecha, idEstado, Hora) VALUES(?, ?, 1, ?)";
	private static final String readAll = "SELECT T.idTurno, T.idMedico, T.Fecha, T.idPaciente, (SELECT PE.Nombre FROM Paciente PA"
			+ " INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteNombre,"
			+ " (SELECT PE.Apellido FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteApellido,"
			+ " (SELECT PE.DNI FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteDNI,"
			+ " T.idEstado, ES.NombreEstado, T.Hora, T.Observación,"
			+ " (SELECT P.Apellido FROM medico AS M INNER JOIN Persona P ON P.DNI =M.DNI WHERE M.idMedico = T.idMedico) as MedicoApellido,"
			+ " (SELECT P.Nombre FROM medico AS M INNER JOIN Persona P ON P.DNI =M.DNI WHERE M.idMedico = T.idMedico) as MedicoNombre, "
			+ " (SELECT E.idEspecialidad FROM Especialidad E INNER JOIN Medico M ON M.idEspecialidad = E.idEspecialidad WHERE M.idMedico = T.idMedico) AS idEspe,"
			+ " (SELECT E.Descripcion FROM Especialidad E INNER JOIN Medico M ON M.idEspecialidad = E.idEspecialidad WHERE M.idMedico = T.idMedico) AS idDesEspe"
			+ " FROM Turno T LEFT JOIN PACIENTE PA ON T.idPaciente = PA.idPaciente "
			+ " LEFT JOIN PERSONA P ON P.DNI = PA.DNI "
			+ " LEFT JOIN ESTADOS ES ON T.idEstado = ES.idEstado "
			+ " WHERE ((select concat(T.fecha, ' ', T.hora) as FechaHora) >= NOW()) AND (SELECT P.Estado FROM medico AS M INNER JOIN Persona P ON P.DNI = M.DNI WHERE M.idMedico = T.idMedico) = 1 ORDER BY T.Fecha ASC";
	private static final String readTurno = "select Es.NombreEstado, T.Observación,T.idTurno,E.idEspecialidad,(select P.Apellido from Medico m inner join Persona as P on P.DNI = m.DNI where m.idMedico = t.idMedico) as ApellidoMedico,(select P.Nombre from Medico m inner join Persona as P on P.DNI = m.DNI where m.idMedico = t.idMedico) as NombreMedico,T.Hora,T.Fecha, E.Descripcion from Turno T inner join Medico as M on M.idMedico = T.idMedico inner join Persona P on P.DNI = M.DNI inner join Especialidad as E on E.idEspecialidad = M.idEspecialidad inner join Estados as Es on Es.idEstado = T.idEstado where idTurno = ?";
	private static final String update2 = "UPDATE Turno SET Observación = ?, idEstado = ? where idTurno = ?";
	private static final String update = "UPDATE Turno SET idEstado = 2, idPaciente = ? where idTurno = ?";
	private static final String existeFecha = "SELECT CASE WHEN exists ( SELECT * FROM turno WHERE idMedico = ? AND Fecha = ?) THEN 'TRUE' ELSE 'FALSE' END";
	private static final String readxMedico = "SELECT T.idTurno, T.idMedico, T.Fecha, T.idPaciente, (SELECT PE.Nombre FROM Paciente PA"
			+ " INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteNombre,"
			+ " (SELECT PE.Apellido FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteApellido,"
			+ " (SELECT PE.DNI FROM Paciente PA INNER JOIN Persona PE ON PE.DNI = PA.DNI WHERE PA.idPaciente = T.idPaciente) AS PacienteDNI,"
			+ " T.idEstado, ES.NombreEstado, T.Hora, T.Observación,"
			+ " (SELECT P.Apellido FROM medico AS M INNER JOIN Persona P ON P.DNI =M.DNI WHERE M.idMedico = T.idMedico) as MedicoApellido,"
			+ " (SELECT P.Nombre FROM medico AS M INNER JOIN Persona P ON P.DNI =M.DNI WHERE M.idMedico = T.idMedico) as MedicoNombre, "
			+ " (SELECT E.idEspecialidad FROM Especialidad E INNER JOIN Medico M ON M.idEspecialidad = E.idEspecialidad WHERE M.idMedico = T.idMedico) AS idEspe,"
			+ " (SELECT E.Descripcion FROM Especialidad E INNER JOIN Medico M ON M.idEspecialidad = E.idEspecialidad WHERE M.idMedico = T.idMedico) AS idDesEspe"
			+ " FROM Turno T LEFT JOIN PACIENTE PA ON T.idPaciente = PA.idPaciente "
			+ " LEFT JOIN PERSONA P ON P.DNI = PA.DNI "
			+ " LEFT JOIN ESTADOS ES ON T.idEstado = ES.idEstado "
			+ " WHERE ((select concat(T.fecha, ' ', T.hora) as FechaHora) >= NOW()) AND T.idMedico = ? AND T.idEstado != 1 AND (SELECT P.Estado FROM medico AS M INNER JOIN Persona P ON P.DNI = M.DNI WHERE M.idMedico = T.idMedico) = 1 ORDER BY T.Fecha ASC";
	private static final String liberarTurno = "UPDATE Turno SET idEstado = 1, idPaciente = null where idTurno = ?";
	private static final String totalAus = "SELECT COUNT(idPaciente) AS Total FROM Turno WHERE idEstado=3 AND Fecha between ? AND ? ;";
	private static final String totalAtendidos = "SELECT Count(idTurno) AS Total FROM TURNO WHERE idEstado=4 AND MONTH(Fecha) = ? AND YEAR(Fecha)= ?";
	private static final String totalPacientes = "SELECT SUM(idpaciente) AS Total FROM Turno WHERE YEAR(Fecha)= ?";
	private static final String totalPresentes = "SELECT SUM(idpaciente) AS Total FROM Turno WHERE idTurno = 4 AND YEAR(Fecha)= ?";
	
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
				turno.setObservacion(resultSet.getString("Observación"));
				
				listaTurno.add(turno);
			}
			
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return listaTurno;
	}

	@Override
	public Turno devuelveTurno(int id) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Turno tu = new Turno();
		Medico me = new Medico();
		Especialidad es = new Especialidad();
		Estado est = new Estado();
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(readTurno);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			resultSet.next();
			tu.setIdTurno(id);
			tu.setFecha(resultSet.getDate("Fecha"));
			tu.setHora(resultSet.getTime("Hora"));
			tu.setObservacion(resultSet.getString("Observación"));
			me.setApellido(resultSet.getString("ApellidoMedico"));
			me.setNombre(resultSet.getString("NombreMedico"));
			es.setIdEspecialidad(resultSet.getInt("idEspecialidad"));
			es.setDescripcion(resultSet.getString("Descripcion"));
			est.setDescripcion(resultSet.getString("NombreEstado"));
			tu.seteEstado(est);
			me.seteEspecialidad(es);
			tu.setmMedico(me);
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tu;
	}

	@Override
	public boolean agendarTurno(String dni, Turno turno) {
		boolean isUpdateExitoso = false;		
		PreparedStatement statement;
		Paciente pac = new Paciente();
		PacienteNegocio paNeg = new PacienteNegocioImpl();
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(update);
			pac = paNeg.mostrarPaciente(dni);
			statement.setInt(1, pac.getIdPaciente());
			statement.setInt(2, turno.getIdTurno());

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}

	@Override
	public boolean existeFechaTurno(int idMedico, Date fecha) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean existe = false;
		ResultSet resultSet;
		try {
			statement = conexion.prepareStatement(existeFecha);
			statement.setInt(1, idMedico);
			statement.setDate(2, fecha);
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
	public ArrayList<Turno> readPorMedico(int idMedico) {
		ArrayList<Turno> listaTurno = new ArrayList<Turno>();
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		ResultSet resultSet;
		
		try{
			statement = conexion.prepareStatement(readxMedico);
			statement.setInt(1, idMedico);
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
				turno.setObservacion(resultSet.getString("Observación"));
				
				listaTurno.add(turno);
			}
			
			
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		
		return listaTurno;
	}

	@Override
	public boolean update2(int idTurno, int estado, String observacion) {
		boolean isUpdateExitoso = false;		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(update2);;
			statement.setString(1, observacion);
			statement.setInt(2, estado);
			statement.setInt(3, idTurno);

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}

	@Override
	public boolean liberarTurno(int idTurno) {
		boolean isUpdateExitoso = false;		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(liberarTurno);;
			statement.setInt(1, idTurno);

			if(statement.executeUpdate() > 0)
			{
				conexion.commit();
				isUpdateExitoso = true;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return isUpdateExitoso;
	}

	@Override
	public int totalAusentes(Date fecha1, Date fecha2) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int total = 0;
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(totalAus);
			statement.setDate(1, fecha1);
			statement.setDate(2, fecha2);
			resultSet = statement.executeQuery();
			resultSet.next();
			total = resultSet.getInt("Total");		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return total;	
	}

	@Override
	public int totalAtendidosPorMes(int mes, int anio) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int total = 0;
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(totalAtendidos);
			statement.setInt(1, mes);
			statement.setInt(2, anio);
			resultSet = statement.executeQuery();
			resultSet.next();
			total = resultSet.getInt("Total");		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return total;	
	}

	@Override
	public int total(int anio) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int tot = 0;
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(totalPacientes);
			statement.setInt(1, anio);

			resultSet = statement.executeQuery();
			resultSet.next();
			tot = resultSet.getInt("Total");		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tot;
	}

	@Override
	public int totalPresentes(int anio) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int tot = 0;
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(totalPresentes);
			statement.setInt(1, anio);

			resultSet = statement.executeQuery();
			resultSet.next();
			tot = resultSet.getInt("Total");		
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tot;
	}
		
}
