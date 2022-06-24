package DaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import Dao.UsuarioDAO;
import Entidad.Usuario;

public class UsuarioImpl implements UsuarioDAO{
	
	private static final String insert = "INSERT INTO Usuarios(NombreUsuario, Clave, Tipo, Estado) VALUES(?, ?, ?, 1)";
	private static final String delete = "UPDATE Usuarios SET Estado=0 WHERE NombreUsuario = ?";
	private static final String readall = "SELECT * FROM Usuarios"; 
	private static final String iniciar = "SELECT U.idUsuario, U.NombreUsuario, U.clave, U.Tipo FROM Usuarios U WHERE U.NombreUsuario = ? AND U.clave = ? AND U.Estado = 1;";

	@Override
	public boolean insert(Usuario usu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Usuario usuDelete) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Usuario> readAll() {
		// TODO Auto-generated method stub
		return null;
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
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				
				if (statement != null) {
					statement.close();
				}
				
				if (cn != null) {
					cn.close();
					cn = null;
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return usuario;
	}

}
