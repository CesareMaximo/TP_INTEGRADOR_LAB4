package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Dao.UsuarioDAO;
import Entidad.Usuario;

import java.sql.SQLException;


public class UsuarioDAOImpl implements UsuarioDAO{
	
	private static final String insertAdmin = "INSERT INTO Usuarios(NombreUsuario, Clave, Tipo, Estado) VALUES(?, ?, ?, 1)";
	private static final String delete = "update usuarios set Estado =0 where idUsuario like '?'";
	private static final String readall = "SELECT * FROM Usuarios"; 
	private static final String iniciar = "SELECT U.idUsuario, U.NombreUsuario, U.clave, U.Tipo FROM Usuarios U WHERE U.NombreUsuario = ? AND U.clave = ? AND U.Estado = 1;";
	private static final String existe = "SELECT * FROM Usuarios WHERE NombreUsuario = ?";
	private static final String ultimoId = "SELECT idUsuario FROM Usuarios ORDER BY idUsuario DESC LIMIT 1";
	
	@Override
	public boolean insertAdmin(Usuario usu) {
		
		PreparedStatement statement;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(insertAdmin);
			statement.setString(1,usu.getNombreUsuario());
			statement.setString(2, usu.getClave());
			statement.setString(3, usu.getTipo());
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
	public boolean delete(Usuario usuDelete) {
		PreparedStatement statement ;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean isInsertExitoso = false;
		try
		{
			statement = conexion.prepareStatement(delete);
			
			statement.setBoolean(1, usuDelete.isEstado() );
			
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
	public List<Usuario> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; 
		ArrayList<Usuario> usuList = new ArrayList<Usuario>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usuList.add(getUsuario(resultSet));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usuList;
		
	}
	
	private Usuario getUsuario(ResultSet resultSet) throws SQLException
	{
		
		Usuario usu = new Usuario();
		usu.setIdUsuario( resultSet.getInt("idUsuario"));
		usu.setNombreUsuario(resultSet.getString("NombreUsuario")) ;
		usu.setTipo(resultSet.getString("Tipo")) ;
		
		
		return usu;
	}
	
	
	
	@Override
	public boolean insertUsuMedico(Usuario usu) {
		// Acá tenemos que ver que onda el Procedimiento Almacenado
		return false;
	}
	

	@Override
	public Usuario iniciar(String nombre, String clave) {
		Usuario usuario = null;
		Connection cn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			cn =  Conexion.getConexion().getSQLConexion();
			statement = cn.prepareStatement(iniciar);
			statement.setString(1, nombre);
			statement.setString(2, clave);
			rs = statement.executeQuery();
			
			while (rs.next()) {
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("idUsuario"));
				usuario.setNombreUsuario(rs.getString("NombreUsuario"));
				usuario.setClave(rs.getString("clave"));
				usuario.setTipo(rs.getString("Tipo"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return usuario;
	}

	@Override
	public boolean existe(String nombre) {
		
		PreparedStatement statement;
		ResultSet rs;
		Connection conexion = Conexion.getConexion().getSQLConexion();
		boolean estado = false;
		try 
		{
			statement = conexion.prepareStatement(existe);
			statement.setString(1, nombre);
			rs = statement.executeQuery();
			while (rs.next()) {
				conexion.commit();
				estado = true;
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return estado;
	}

	@Override
	public int ultimoUsuario() {
		PreparedStatement statement;
		ResultSet resultSet; 
		Usuario usu = new Usuario();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(ultimoId);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				usu.setIdUsuario( resultSet.getInt("idUsuario"));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return usu.getIdUsuario();
	}

}
