package Entidad;

public class Usuario {

	
	
	private int idUsuario;
	private String nombreUsuario;
	private String clave;
	private String tipo;
	private boolean estado;
	
	
	public Usuario() {}
	

	public Usuario(int idUsuario, String nombreUsuario, String clave, String tipo, boolean estado) {
		super();
		this.idUsuario = idUsuario;
		this.nombreUsuario = nombreUsuario;
		this.clave = clave;
		this.tipo = tipo;
		this.estado = estado;
	}


	//Getters & Setters
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	//Metodo ToString
	
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", clave=" + clave + ", tipo="
				+ tipo + ", estado=" + estado + "]";
	}

	
	
	
	
	
	
	
	
	
}
