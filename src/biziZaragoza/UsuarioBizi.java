package biziZaragoza;

public class UsuarioBizi implements Comparable <UsuarioBizi>{
	private int identificador;
	private int traslados;
	private int circulares;
	private int total;
	
	// Diferentes metodos constructores de UsuarioBizi
	public UsuarioBizi () {
		identificador = 0;
		traslados = 0;
		circulares = 0;
		total = 0;
	}
	
	public UsuarioBizi (int identificador) {
		this.identificador = identificador;
		traslados = 0;
		circulares = 0;
		total = 0;
	}

	public UsuarioBizi (int identificador, int traslados, int circulares, int total) {
		this.identificador = identificador;
		this.traslados = traslados;
		this.circulares = circulares;
		this.total = total;
	}
	
	// Metodos get y set de la clase
	public int getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	public int getTraslados() {
		return traslados;
	}
	
	public void setTraslados(int traslados) {
		this.traslados = traslados;
	}
	
	public int getCirculares() {
		return circulares;
	}
	
	public void setCirculares(int circulares) {
		this.circulares = circulares;
	}
	
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	@Override
	public String toString() {
		return identificador + "        " + traslados 
				+ "         " + circulares + "        " + total;
	}

	@Override
	public int compareTo(UsuarioBizi u) {
		if (this.total > u.getTotal()) {
			return -1;
		} else if (this.total < u.getTotal()) {
			return 1;
		} else {
			// Si son iguales
			return 0;
		}	
	}
}
