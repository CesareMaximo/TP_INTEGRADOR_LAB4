package DaoImpl;

import java.util.List;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Dao.MedicoDAO;
import Entidad.*;
public class MedicoDAOImpl implements  MedicoDAO{

	
	//private static final String insert = "";
	private static final String delete = "UPDATE Persona set Estado = 0 where DNI like ?";
	private static final String readall = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad WHERE p.Estado = 1";
	private static final String readallFiltro = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where m.idEspecialidad = ? and p.Estado = 1";
	//private static final String readallBuscar = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where p.Nombre like '%"++"%' or p.Apellido like '%"++"%' ";
	
	@Override
	public boolean insert(Medico me) {
		Connection conexion = null;
		boolean isInsertExitoso = false;
		try {
			conexion = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = conexion.prepareCall("call registrarUsuMedico(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			//falta todo lo de usuario: nombreUsuario, clave, tipo
			cst.setString(4, me.getDni());
			//idespecialidad
			cst.setString(6, me.getNombre());
			cst.setString(7, me.getApellido());
			cst.setString(8, String.valueOf(me.getSexo()));
			cst.setInt(9, me.getnNacionalidad().getIdNacionalidad());
			cst.setDate(10, me.getFechaNacimiento());
			cst.setString(11, me.getDireccion());
			cst.setInt(12, me.getlLocalidad().getIdLocalidad());
			cst.setString(13, me.getEmail());
			cst.setBoolean(14, true);
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
		me.setDni(resultSet.getString("DNI"));
		me.setIdMedico( resultSet.getInt("idMedico"));
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

}
