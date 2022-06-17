package Entidad;

public class Localidad {
	private int idLocalidad;
	private Provincia pProvincia;
	private String descripcion;
	
	
	public Localidad() {}
	public Localidad(int idLocalidad, Provincia pProvincia, String descripcion) {
		super();
		this.idLocalidad = idLocalidad;
		this.pProvincia = pProvincia;
		this.descripcion = descripcion;
	}
	//Getters & Setters
	public int getIdLocalidad() {
		return idLocalidad;
	}
	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}
	public Provincia getpProvincia() {
		return pProvincia;
	}
	public void setpProvincia(Provincia pProvincia) {
		this.pProvincia = pProvincia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	//Metodo ToString
	@Override
	public String toString() {
		return "Localidad [idLocalidad=" + idLocalidad + ", pProvincia=" + pProvincia + ", descripcion=" + descripcion
				+ "]";
	}
	
	
	
	
}
