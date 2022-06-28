package DaoImpl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Entidad.*;
import Dao.PacienteDAO;
import javafx.css.PseudoClass;

public class PacienteImpl implements PacienteDAO{

	private static final String delete = "";
	private static final String readall = "select * from paciente as pa inner join persona as pe on pe.DNI like pa.DNI INNER JOIN Nacionalidad as N on Pe.idNacionalidad = N.idNacionalidad WHERE pe.Estado = 1"; 
	private static final String update = "UPDATE Persona SET Nombre = ?, Apellido = ?, Sexo = ?, idNacionalidad = ?, FechaNacimiento = ?, Direccion = ?, idLocalidad = ?, Email = ?, Telefono1 = ?, Telefono2 = ? WHERE Dni = ?";
	private static final String readPaciente = "SELECT Pe.Estado, Pa.idPaciente, Pe.DNI, Pe.Nombre, Pe.Apellido, Pe.Sexo, Pe.idNacionalidad, N.Descripcion as DescripcionNac, Pe.FechaNacimiento, Pe.Direccion, Pe.idLocalidad, Lo.Descripcion as DescripcionLo, Pe.Email, Pe.Telefono1, Pe.Telefono2, Lo.idProvincia, Pro.Descripcion as DescripcionPro  FROM Paciente as Pa INNER JOIN Persona AS Pe ON Pe.Dni = Pa.Dni INNER JOIN Nacionalidad as N on Pe.idNacionalidad = N.idNacionalidad INNER JOIN Localidad as Lo ON Lo.idLocalidad = Pe.idLocalidad INNER JOIN Provincia as Pro ON Pro.idProvincia = Lo.idProvincia WHERE Pe.Dni = ?";
	
	@Override
	public boolean insert(Paciente pa) {
		Connection conexion = null;
		boolean isInsertExitoso = false;
		try {
			conexion = Conexion.getConexion().getSQLConexion();
			CallableStatement cst = conexion.prepareCall("call registrarPaciente(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			cst.setString(1, pa.getDni());
			cst.setString(2, pa.getNombre());
			cst.setString(3, pa.getApellido());
			cst.setString(4, String.valueOf(pa.getSexo()));
			cst.setInt(5, pa.getnNacionalidad().getIdNacionalidad());
			cst.setDate(6, pa.getFechaNacimiento());
			cst.setString(7, pa.getDireccion());
			cst.setInt(8, pa.getlLocalidad().getIdLocalidad());
			cst.setString(9, pa.getEmail());
			cst.setBoolean(10, true);
			cst.setString(11, pa.getTelefono1());
			cst.setString(12, pa.getTelefono2());

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
	public boolean delete(Paciente paDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Paciente> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Paciente> pacienteList = new ArrayList<Paciente>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				pacienteList.add(getPaciente(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return pacienteList;
	}

	private Paciente getPaciente(ResultSet resultSet) throws SQLException {
		Paciente pa = new Paciente();
		Nacionalidad na = new Nacionalidad();
		Localidad lo = new Localidad();
		
		pa.setDni(resultSet.getString("DNI"));
		pa.setIdPaciente(resultSet.getInt("idPaciente"));

		pa.setNombre(resultSet.getString("Nombre"));
		pa.setApellido(resultSet.getString("Apellido"));
		pa.setSexo(resultSet.getString("Sexo").charAt(0));
		//
		na.setIdNacionalidad( resultSet.getInt("idNacionalidad"));	
		na.setDescripcion( resultSet.getString("Descripcion"));
		pa.setnNacionalidad(na);
		//
		pa.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
		pa.setDireccion(resultSet.getString("Direccion"));
		//
		lo.setIdLocalidad(resultSet.getInt("idLocalidad"));
		pa.setlLocalidad(lo);
		//
		pa.setEmail(resultSet.getString("Email"));
		pa.setEstado(resultSet.getBoolean("Estado"));
		//
		pa.setTelefono1(resultSet.getString("Telefono1"));
		pa.setTelefono2(resultSet.getString("Telefono2"));
		//
		
		return pa;
	}
	
	private Paciente getPaciente2(ResultSet resultSet) throws SQLException {
		Paciente pa = new Paciente();
		Nacionalidad na = new Nacionalidad();
		Localidad lo = new Localidad();
		Provincia pro = new Provincia();
		
		pa.setDni(resultSet.getString("DNI"));
		pa.setIdPaciente(resultSet.getInt("idPaciente"));

		pa.setNombre(resultSet.getString("Nombre"));
		pa.setApellido(resultSet.getString("Apellido"));
		pa.setSexo(resultSet.getString("Sexo").charAt(0));
		//
		na.setIdNacionalidad( resultSet.getInt("idNacionalidad"));	
		na.setDescripcion( resultSet.getString("DescripcionNac"));
		pa.setnNacionalidad(na);
		//
		pa.setFechaNacimiento(resultSet.getDate("FechaNacimiento"));
		pa.setDireccion(resultSet.getString("Direccion"));
		//
		pro.setIdProvincia(resultSet.getInt("idProvincia"));
		pro.setDescripcion(resultSet.getString("DescripcionPro"));
		lo.setpProvincia(pro);
		//
		lo.setIdLocalidad(resultSet.getInt("idLocalidad"));
		lo.setDescripcion(resultSet.getString("DescripcionLo"));
		pa.setlLocalidad(lo);
		//
		pa.setEmail(resultSet.getString("Email"));
		pa.setEstado(resultSet.getBoolean("Estado"));
		//
		pa.setTelefono1(resultSet.getString("Telefono1"));
		pa.setTelefono2(resultSet.getString("Telefono2"));
		//
		return pa;
	}
	
	public List<Paciente> readAllBuscar(String nombre){
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Paciente> listaPaciente = new ArrayList<Paciente>();
		
	
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement("Select * from paciente as pa inner join persona as pe on pa.DNI=pe.DNI  INNER JOIN Nacionalidad as N on Pe.idNacionalidad = N.idNacionalidad where pe.DNI like "+"'%"+nombre+"%' or pe.Nombre like "+"'%"+nombre+"%' or pe.Apellido like "+"'%"+nombre+"%' or pe.Email like "+"'%"+nombre+"%'");
			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				listaPaciente.add(getPaciente(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return listaPaciente;
	}

	@Override
	public boolean update(Paciente pa) {
		boolean isUpdateExitoso = false;		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		try 
		{
			statement = conexion.prepareStatement(update);
			statement.setString(1, pa.getNombre());
			statement.setString(2, pa.getApellido());
			statement.setString(3, String.valueOf(pa.getSexo()));
			statement.setInt(4, pa.getnNacionalidad().getIdNacionalidad());
			statement.setDate(5, pa.getFechaNacimiento());
			statement.setString(6, pa.getDireccion());
			statement.setInt(7, pa.getlLocalidad().getIdLocalidad());
			statement.setString(8, pa.getEmail());
			statement.setString(9, pa.getTelefono1());
			statement.setString(10, pa.getTelefono2());
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
	public Paciente mostrarPaciente(String dni) {
		Connection cn = Conexion.getConexion().getSQLConexion();
		Paciente pa = new Paciente();
		PreparedStatement statement;
		ResultSet resultSet; 
		try
		 {
			statement = cn.prepareStatement(readPaciente);
			statement.setString(1, dni);
			resultSet = statement.executeQuery();
			resultSet.next();
			pa = getPaciente2(resultSet);			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return pa;			
	}
}
