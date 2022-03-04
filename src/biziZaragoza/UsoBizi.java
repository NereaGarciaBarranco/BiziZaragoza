package biziZaragoza;

public class UsoBizi {
	private int usuario;
	private String fechaRetiro;
	private int estacionRetiro;
	private String fechaAnclaje;
	private int estacionAnclaje;
	
	// Metodo constructor solo con los datos estrictamente necesarios
	public UsoBizi (int usuario, int estacionRetiro, int estacionAnclaje) {
		this.usuario = usuario;
		fechaRetiro = "";
		this.estacionRetiro = estacionRetiro;
		fechaAnclaje = "";
		this.estacionAnclaje = estacionAnclaje;
	}
	
	// Metodo constructor que recibe todos los atributos por parámetro
	public UsoBizi (int usuario, String fechaRetiro, int estacionRetiro, 
			String fechaAnclaje, int estacionAnclaje) {
		this.usuario = usuario;
		this.fechaRetiro = fechaRetiro;
		this.estacionRetiro = estacionRetiro;
		this.fechaAnclaje = fechaAnclaje;
		this.estacionAnclaje = estacionAnclaje;
	}
	
	// Metodos get y set de la clase UsoBizi
	public int getUsuario() {
		return usuario;
	}
	
	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}
	
	public String getFechaRetiro() {
		return fechaRetiro;
	}
	
	public void setFechaRetiro(String fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}
	
	public int getEstacionRetiro() {
		return estacionRetiro;
	}
	
	public void setEstacionRetiro(int estacionRetiro) {
		this.estacionRetiro = estacionRetiro;
	}
	
	public String getFechaAnclaje() {
		return fechaAnclaje;
	}
	
	public void setFechaAnclaje(String fechaAnclaje) {
		this.fechaAnclaje = fechaAnclaje;
	}
	
	public int getEstacionAnclaje() {
		return estacionAnclaje;
	}
	
	public void setEstacionAnclaje(int estacionAnclaje) {
		this.estacionAnclaje = estacionAnclaje;
	}
	
	@Override
	public String toString() {
		return usuario + " " + fechaRetiro + " " + estacionRetiro 
				+ " " + fechaAnclaje + " " + estacionAnclaje;
	}
}
