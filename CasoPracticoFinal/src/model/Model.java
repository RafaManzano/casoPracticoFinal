package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import bbdd.DataBaseConnection;
import clases.PasajerosPorVuelo;

/**
 * Session Bean implementation class Model
 */
@Stateless
@LocalBean
public class Model {

	private Map<String, List<PasajerosPorVuelo>> pasajerosVuelos;
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

    
}
