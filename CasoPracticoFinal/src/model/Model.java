package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bbdd.DataBaseConnection;
import clases.Pasajero;
import clases.PasajeroFull;
import clases.PasajerosPorVuelo;
import clases.Usuario;
import clases.Vuelo;

/**
 * Session Bean implementation class Model
 */
@Stateless
@LocalBean
public class Model {

	private Map<String, List<PasajerosPorVuelo>> pasajerosVuelos;
	private Usuario usuario;
	private List<Vuelo> vuelos;
	private PasajeroFull pasajero;
	private List<Pasajero> pasajeros;
    /**
     * Default constructor. 
     */
    public Model() {
        // TODO Auto-generated constructor stub
    }
	public Map<String, List<PasajerosPorVuelo>> getPasajerosVuelos() {
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("rmanzano", "Temp2019$$");
		try {
			ResultSet rs = conexion.damePasajerosVuelos();
			List<PasajerosPorVuelo> pasVuelo;
			pasVuelo = new ArrayList<>();
			while(rs.next()) {
				pasVuelo.add(new PasajerosPorVuelo( 
						rs.getString("destino"),
						rs.getString("nombre"),
						rs.getString("primerApellido"),
						rs.getString("segundoApellido")));
			}
			pasajerosVuelos = pasVuelo.stream().collect(Collectors.groupingBy(PasajerosPorVuelo::getDestino));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return pasajerosVuelos;
	}
	public void setPasajerosVuelos(Map<String, List<PasajerosPorVuelo>> pasajerosVuelos) {
		this.pasajerosVuelos = pasajerosVuelos;
	}
	public Usuario getUsuario(Usuario usu)  {
		DataBaseConnection bbdd = new DataBaseConnection("rmanzano", "Temp2019$$");
		usuario = new Usuario();
		ResultSet rs = null;
		try {
			rs = bbdd.dameLogin(usu);
			if(rs.next() && rs.getRow() != 0) {
				usuario.setUsuario(rs.getString("login"));
				usuario.setPassword(rs.getString("password"));
			}
			else {
				usuario = null;
			}
			
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Vuelo> getVuelos() {
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("rmanzano","Temp2019$$");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				
		try {
			ResultSet rs = conexion.dameVuelos();
			vuelos = new ArrayList<>();
			while (rs.next()) {
				vuelos.add(new Vuelo(rs.getInt("id"),rs.getString("destino"),  
						Instant.ofEpochMilli(rs.getDate("fechaHoraSalida").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(),
						Instant.ofEpochMilli(rs.getDate("fechaHoraLlegada").getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime(), 
						rs.getInt("totalPasajeros")));
				
				
				}
			
		} 
		catch (SQLException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return vuelos;
	}
	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}
	
	public PasajeroFull getPasajero() {
		return pasajero;
	}
	public void setPasajero(PasajeroFull pasajero) {
		DataBaseConnection conexion;
		conexion = new DataBaseConnection("rmanzano","Temp2019$$");
		
		try {
			conexion.altaPasajero(pasajero);
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		this.pasajero = pasajero;
	}
	public List<Pasajero> getPasajeros(int idVuelo) {
		DataBaseConnection dataBaseConnection;
    	dataBaseConnection = new DataBaseConnection("rmanzano", "Temp2019$$");
    	
    	try {
			ResultSet rs = dataBaseConnection.damePasajerosPorVuelo(idVuelo);
			pasajeros = new ArrayList<>();
			while(rs.next()) {
				pasajeros.add(new Pasajero(
						rs.getString("nombre"), 
						rs.getString("primerApellido"),
						rs.getString("segundoApellido")));
			}
		}
    	catch (SQLException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
    	
		return pasajeros;
	}
	

	public void setPasajeros(List<Pasajero> pasajeros) {
		this.pasajeros = pasajeros;
	}

    
}
