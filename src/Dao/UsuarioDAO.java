package Dao;

import java.util.List;

import Entidad.Usuario;

public interface UsuarioDAO {
	public boolean insert(Usuario usu);
	public boolean delete(Usuario usuDelete);
	public List<Usuario> readAll();
	public Usuario iniciar(String nombre, String clave);
}
