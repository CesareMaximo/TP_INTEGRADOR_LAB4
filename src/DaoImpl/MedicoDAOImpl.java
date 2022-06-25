package DaoImpl;

import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Dao.MedicoDAO;
import Entidad.*;
public class MedicoDAOImpl implements  MedicoDAO{

	
	private static final String insert = "";
	private static final String delete = "";
	private static final String readall = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad";
	private static final String readallFiltro = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where m.idEspecialidad = ?";
	//private static final String readallBuscar = "select * from medico as m inner join persona as p on p.DNI like m.DNI inner join especialidad as es on es.idEspecialidad = m.idEspecialidad where p.Nombre like '%"++"%' or p.Apellido like '%"++"%' ";
	
	@Override
	public boolean insert(Medico me) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Medico meDelete) {
		// TODO Auto-generated method stub
		return false;
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

}
