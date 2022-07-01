package Negocio;

import Entidad.Usuario;

public interface UsuarioNegocio {
	public boolean insert(Usuario us);
	public boolean delete(Usuario us);
	public Usuario iniciar(String nombre, String clave);
	public boolean existe(String nombre);
	public int ultimoUsuario();
}
