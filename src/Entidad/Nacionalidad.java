package Entidad;

public class Nacionalidad {
	private int idNacionalidad;
	private String descripcion;
	
	
	public Nacionalidad() {}
	
	public Nacionalidad(int idNacionalidad, String descripcion) {
		super();
		this.idNacionalidad = idNacionalidad;
		this.descripcion= descripcion;
	}
	//Getters and Setters
	public int getIdNacionalidad() {
		return idNacionalidad;
	}
	public void setIdNacionalidad(int idNacionalidad) {
		this.idNacionalidad = idNacionalidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String Descripcion) {
		descripcion = Descripcion;
	}

	//Metodo ToString
	@Override
	public String toString() {
		return "Nacionalidad [idNacionalidad=" + idNacionalidad + ", Descripcion=" + Descripcion + "]";
	}
	
	
	
}
