package NegocioImpl;

import Dao.UsuarioDAO;
import DaoImpl.UsuarioDAOImpl;
import Entidad.Usuario;
import Negocio.UsuarioNegocio;

public class UsuarioNegocioImpl implements UsuarioNegocio {

	UsuarioDAO usuario = new UsuarioDAOImpl();

	@Override
	public boolean insert(Usuario us) {
		
		return usuario.insertAdmin(us);
	}

	@Override
	public boolean delete(Usuario us) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario iniciar(String nombre, String clave) {
		
		Usuario user = new Usuario();
		
		user = usuario.iniciar(nombre, clave);
		
		return user;
	}

	@Override
	public boolean existe(String nombre) {
		
		return usuario.existe(nombre);
	}

	@Override
	public int ultimoUsuario() {

		return usuario.ultimoUsuario();
	}

	
}
