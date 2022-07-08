package DaoImpl;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Dao.MedicoDAO;
import Entidad.*;
public class MedicoDAOImpl implements  MedicoDAO{

	
	private static final String insert = "INSERT INTO Medico(idMedico, DNI, idEspecialidad) VALUES (?, ?, ?)";
	private static final String delete = "UPDATE Persona set Estado = 0 where DNI like ?";
	private static final String readall = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad WHERE p.Estado = 1";
	private static final String readallFiltro = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where m.idEspecialidad = ? and p.Estado = 1";
	private static final String readMedico = "SELECT P.DNI, P.Nombre, P.Apellido, P.Sexo, P.idNacionalidad, P.FechaNacimiento, P.Direccion, P.idLocalidad, P.Email, P.Telefono1, P.Telefono2, P.Estado, M.idMedico, M.idEspecialidad, E.Descripcion AS DesEspe, L.Descripcion AS DesLoc, PRO.idProvincia, PRO.Descripcion AS DesPro, N.Descripcion AS DesNac, U.NombreUsuario, U.Clave FROM Medico AS M INNER JOIN Persona AS P ON P.DNI = M.DNI INNER JOIN Especialidad E ON E.idEspecialidad = M.idEspecialidad INNER JOIN Localidad L ON L.idLocalidad = P.idLocalidad INNER JOIN Provincia PRO ON PRO.idProvincia = L.idProvincia INNER JOIN Nacionalidad N ON N.idNacionalidad = P.idNacionalidad INNER JOIN usuarios U ON U.idUsuario=M.idMedico WHERE IdMedico=?";
	private static final String update = "UPDATE Persona SET Nombre = ?, Apellido = ?, Sexo = ?, idNacionalidad = ?, FechaNacimiento = ?, Direccion = ?, idLocalidad = ?, Email = ?, Telefono1 = ?, Telefono2 = ? WHERE Dni = ?";
	private static final String totalPaciente = "SELECT COUNT(distinct idPaciente) AS Total FROM Turno WHERE idMedico = ? AND idEstado=4 AND Fecha between ? AND ? ;";
	private static final String exists = "SELECT CASE WHEN exists ( SELECT * FROM Medico WHERE DNI = ?) THEN 'TRUE' ELSE 'FALSE' END";
	//private static final String readallBuscar = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where p.Nombre like '%"++"%' or p.Apellido like '%"++"%' ";
	
	
	@Override
	public boolean insert(Medico me) {
		Connection conexion = null;
		boolean isInsertExitoso = false;
		try {
			conexion = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = conexion.prepareCall("call registrarUsuMedico(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			cst.setString(1, me.getIdMedico().getNombreUsuario());
			cst.setString(2, me.getIdMedico().getClave());
			cst.setString(3, "Medico");
			cst.setString(4, me.getDni());
			cst.setString(5, me.getNombre());
			cst.setString(6, me.getApellido());
			cst.setString(7, String.valueOf(me.getSexo()));
			cst.setInt(8, me.getnNacionalidad().getIdNacionalidad());
			cst.setDate(9, me.getFechaNacimiento());
			cst.setString(10, me.getDireccion());
			cst.setInt(11, me.getlLocalidad().getIdLocalidad());
			cst.setString(12, me.getEmail());
			cst.setBoolean(13, true);
			cst.setString(14, me.getTelefono1());
			cst.setString(15, me.getTelefono2());
			//idUsuario = IdMedico

			if (cst.executeUpdate()>0) {
				conexion.commit();
				isInsertExitoso = true;
			}
			
		} catch (SQLException e) {
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
	public boolean insertMe(Medico me) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insert);
			statement.setInt(1, me.getIdMedico().getIdUsuario());
			statement.setString(2, me.getDni());
			statement.setInt(3, me.geteEspecialidad().getIdEspecialidad());
	
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
	public List<Medico> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Medico> medicoList = new ArrayList<Medico>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				medicoList.add(getMedico(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return medicoList;
	}
	
	private Medico getMedico(ResultSet resultSet) throws SQLException
	{
		
		Medico me = new Medico();
		Especialidad es = new Especialidad();
		Nacionalidad na = new Nacionalidad();
		Localidad lo = new Localidad();
		Usuario us = new Usuario();
		me.setDni(resultSet.getString("DNI"));
		us.setIdUsuario(resultSet.getInt("idMedico"));
		me.setIdMedico(us);
		//
		es.setIdEspecialidad(resultSet.getInt("idEspecialidad"));	
		es.setDescripcion(resultSet.getString("Descripcion"));
		me.seteEspecialidad(es);
		//
		me.setNombre(resultSet.getString("Nombre"));
		me.setApellido(resultSet.getString("Apellido"));
		me.setSexo(resultSet.getString("Sexo").charAt(0));
		//
		na.setIdNacionalidad( resultSet.getInt("idNacionalidad"));
		me.setnNacionalidad(na);
		//
		me.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
		me.setDireccion(resultSet.getString("Direccion"));
		//
		lo.setIdLocalidad(resultSet.getInt("idLocalidad"));
		me.setlLocalidad(lo);
		//
		me.setEmail(resultSet.getString("Email"));
		me.setEstado(resultSet.getBoolean("Estado"));
			
		return me;
	}

	@Override
	public List<Medico> readAllfiltro(int id) {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Medico> medicoList = new ArrayList<Medico>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readallFiltro);
			statement.setInt(1,id);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				medicoList.add(getMedico(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return medicoList;
	}

	@Override
	public List<Medico> readAllBuscar(String nombre) {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Medico> medicoList = new ArrayList<Medico>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where p.Nombre like "+"'%"+ nombre+"%' or p.Apellido like "+"'%"+nombre+"%' or p.DNI like "+"'%"+nombre+"%' ");
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				medicoList.add(getMedico(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return medicoList;
	}

	@Override
	public boolean delete(String dni) {
		boolean isUpdateExitoso = false;		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(delete);
			statement.setString(1, dni);
			
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
	public Medico mostrarMedico(int idMedico) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Medico me = new Medico();
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(readMedico);
			statement.setInt(1, idMedico);
			resultSet = statement.executeQuery();
			resultSet.next();
			me = getMedico2(resultSet);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return me;		
	}

	private Medico getMedico2(ResultSet resultSet) throws SQLException{		
		Medico me = new Medico();
		Especialidad es = new Especialidad();
		Nacionalidad na = new Nacionalidad();
		Provincia pro = new Provincia();
		Localidad lo = new Localidad();
		Usuario us = new Usuario();
		me.setDni(resultSet.getString("DNI"));
		us.setIdUsuario(resultSet.getInt("idMedico"));
		us.setClave(resultSet.getString("Clave"));
		us.setNombreUsuario(resultSet.getString("NombreUsuario"));
		me.setIdMedico(us);
		//
		es.setIdEspecialidad(resultSet.getInt("idEspecialidad"));
		es.setDescripcion(resultSet.getString("DesEspe"));
		me.seteEspecialidad(es);
		//
		me.setNombre(resultSet.getString("Nombre"));
		me.setApellido(resultSet.getString("Apellido"));
		me.setSexo(resultSet.getString("Sexo").charAt(0));
		//
		na.setIdNacionalidad( resultSet.getInt("idNacionalidad"));
		na.setDescripcion(resultSet.getString("DesNac"));
		me.setnNacionalidad(na);
		//
		me.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
		me.setDireccion(resultSet.getString("Direccion"));
		//
		pro.setIdProvincia(resultSet.getInt("idProvincia"));
		pro.setDescripcion(resultSet.getString("DesPro"));
		lo.setIdLocalidad(resultSet.getInt("idLocalidad"));
		lo.setDescripcion(resultSet.getString("DesLoc"));
		lo.setpProvincia(pro);
		
		me.setlLocalidad(lo);
		//
		me.setEmail(resultSet.getString("Email"));
		me.setEstado(resultSet.getBoolean("Estado"));
		me.setTelefono1(resultSet.getString("Telefono1"));
		me.setTelefono2(resultSet.getString("Telefono2"));
			
		return me;
	}

	@Override
	public boolean update(Medico me) {
		boolean isUpdateExitoso = false;		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, me.getNombre());
			statement.setString(2, me.getApellido());
			statement.setString(3, String.valueOf(me.getSexo()));
			statement.setInt(4, me.getnNacionalidad().getIdNacionalidad());
			statement.setDate(5, me.getFechaNacimiento());
			statement.setString(6, me.getDireccion());
			statement.setInt(7, me.getlLocalidad().getIdLocalidad());
			statement.setString(8, me.getEmail());
			statement.setString(9, me.getTelefono1());
			statement.setString(10, me.getTelefono2());
			statement.setString(11, me.getDni());
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
	public int totalPacientesXMedico(int idMedico, Date fecha1, Date fecha2) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		int total = 0;
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(totalPaciente);
			statement.setInt(1, idMedico);
			statement.setDate(2, fecha1);
			statement.setDate(3, fecha2);
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
	public boolean existeMedico(String dni) {
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean existe = false;
		ResultSet resultSet;
		try {
			statement = conexion.prepareStatement(exists);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				existe = Boolean.valueOf(resultSet.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return existe;
	}

}
