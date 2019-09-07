package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import clases.Usuario;

public class DataBaseConnection {
	private Connection conn;
	private String user;
	private String password;
	
	public DataBaseConnection(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}

	// Metodo que establece la conexion con la base de datos	
	public Connection getConnection() throws ClassNotFoundException {
		conn = null;
		
		Properties info;
		String urlConn;
		
		
		urlConn = "jdbc:mysql://localhost:3306/Aeropuerto?"
				+ "useUnicode=true&useJDBCCompliantTimezoneShift="
				+ "true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		
		info = new Properties();
		info.put("user", this.user);
		info.put("password", this.password);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(urlConn, info);
			System.out.println("Conectado a la base de datos");
		} catch (SQLException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
				.log(Level.INFO, null, e);
				
		}
		
		return conn;
	}
	
	public ResultSet dameLogin(Usuario usuario) throws SQLException {
		ResultSet rs = null;
		String query = "SELECT * FROM Usuarios"
				+ "WHERE login = ? AND password = ?"  ;
		PreparedStatement stmt;
		
		try {
			conn = getConnection();
			stmt = conn.prepareStatement(query);
			stmt.setString(1, usuario.getUsuario());
			stmt.setString(2, usuario.getPassword());
			rs = stmt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		
		return rs;
	}
	
	public ResultSet damePasajerosVuelos() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT V.destino, P.nombre, P.primerApellido, P.segundoApellido FROM pasajeros AS P\r\n" + 
				"INNER JOIN vuelos AS V ON P.idVuelo = V.id;";
		
		Statement stmt;
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
		} 
		
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.getLogger(DataBaseConnection.class.getName())
		 	.log(Level.INFO, null, e);
		}
		
		return rs;
	}
	

	
}

















