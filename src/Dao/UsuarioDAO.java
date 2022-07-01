package Dao;

import java.util.List;

import Entidad.Usuario;

public interface UsuarioDAO {
	public boolean insertAdmin(Usuario usu);
	public boolean delete(Usuario usuDelete);
	public List<Usuario> readAll();
	public Usuario iniciar(String nombre, String clave);
	public boolean existe(String nombre);
	public boolean insertUsuMedico(Usuario usu);
	public int ultimoUsuario();
}
