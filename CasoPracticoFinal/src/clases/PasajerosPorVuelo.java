package clases;

public class PasajerosPorVuelo {
	private String destino;
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	
	public PasajerosPorVuelo() {
		super();
	}

	public PasajerosPorVuelo(String destino, String nombre, String primerApellido, String segundoApellido) {
		this.destino = destino;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	
	
}
