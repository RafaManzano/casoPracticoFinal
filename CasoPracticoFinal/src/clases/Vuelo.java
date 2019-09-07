package clases;

import java.time.LocalDateTime;

public class Vuelo {
	private int id;
	private String destino;
	private LocalDateTime fechaHoraSalida;
	private LocalDateTime fechaHoraLlegada;
	private int totalPasajeros;
	
	public Vuelo() {
		super();
	}

	public Vuelo(int id, String destino, LocalDateTime fechaHoraSalida, LocalDateTime fechaHoraLlegada, int totalPasajeros) {
		this.id = id;
		this.destino = destino;
		this.fechaHoraSalida = fechaHoraSalida;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.totalPasajeros = totalPasajeros;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public LocalDateTime getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
		this.fechaHoraLlegada = fechaHoraLlegada;
	}

	public int getTotalPasajeros() {
		return totalPasajeros;
	}

	public void setTotalPasajeros(int totalPasajeros) {
		this.totalPasajeros = totalPasajeros;
	}
	
	
	
	
}
