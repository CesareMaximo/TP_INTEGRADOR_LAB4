package Entidad;

public class Provincia {

		private int idProvincia;
		private String descripcion;
		
		public Provincia() {}
		
		public Provincia(int idProvincia, String descripcion) {
			super();
			this.idProvincia = idProvincia;
			this.descripcion = descripcion;
		}
		//Getters & Setters
		public int getIdProvincia() {
			return idProvincia;
		}
		public void setIdProvincia(int idProvincia) {
			this.idProvincia = idProvincia;
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
			return descripcion;
		}
		
		
		
}
