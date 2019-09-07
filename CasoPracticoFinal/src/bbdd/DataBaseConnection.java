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

import clases.PasajeroFull;
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
		String query = "SELECT `login`, `password` FROM `usuarios`"
				+ "WHERE `login` = ? AND `password` = ?;"  ;
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

	public ResultSet dameVuelos() throws SQLException {
		ResultSet rs = null;
		String query = "SELECT * FROM aeropuerto.vuelos";

		Statement stmt;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			
        } 
		catch (ClassNotFoundException e) {
			Logger.getLogger(DataBaseConnection.class.getName())
			.log(Level.INFO, null, e);
		}
		return rs;
	}
	

	public void altaPasajero(PasajeroFull p) throws SQLException {
		// PRIMERO: Insertar en la tabla de pasajeros
		String queryPasajero = "INSERT INTO pasajeros (`idVuelo`, `nombre`, `primerApellido`, "
				+ "`segundoApellido`)"
				+ " VALUES (?, ?, ?, ?)";
		PreparedStatement stmt;
		
		
		try {
			conn = getConnection();
			conn.setAutoCommit(false); // Inicia una transaccion
			
			// Recuperar el ID generado
			Long lastInsertedId = 0L;
			
			// para recuperar el ultimo id insertado hay q pasar un segundo parametro al metodo prepareStatement
			stmt = conn.prepareStatement(queryPasajero, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, p.getIdVuelo());
			stmt.setString(2, p.getNombre());
			stmt.setString(3, p.getPrimerApellido());
			stmt.setString(4, p.getSegundoApellido());
			
			int totalInsertado; // Comprobar si se ha insertado el registro
			totalInsertado = stmt.executeUpdate();
			
			if(totalInsertado == 0) {
				throw new SQLException("Error en el alta del Pasajero");
			} 
			else {
				ResultSet generatedKeys = stmt.getGeneratedKeys();
				if(generatedKeys.next()) {
					lastInsertedId = generatedKeys.getLong(1);
				}
				
				// SEGUNDO: Con el ID de estudiante recuperado, insertar el telefono correspondiente
				String queryTelefonos; // En tabla Telefonos
				queryTelefonos = "INSERT INTO `telefonos` (`idPasajero`, `numero`) VALUES (?, ?)";
				PreparedStatement stmt2;
				stmt2 = conn.prepareStatement(queryTelefonos);
				stmt2.setLong(1, lastInsertedId);
				stmt2.setString(2, p.getTelefono());
				stmt2.executeUpdate();

				// TERCERO: Con el ID de estudiante recuperado, insertar el correo correspondiente
				String queryCorreos; // En tabla Correos
				queryCorreos = "INSERT INTO `correos` (`idPasajero`, `correo`) VALUES (?, ?)";
				PreparedStatement stmt3;
				stmt3 = conn.prepareStatement(queryCorreos);
				stmt3.setLong(1, lastInsertedId);
				stmt3.setString(2, p.getCorreo());
				stmt3.executeUpdate();
			}
			
			conn.commit();
		} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		   Logger.getLogger(DataBaseConnection.class.getName()).log(Level.INFO, null, e);
		} 
		finally {
			conn.setAutoCommit(true);
		}
	}
	
}

















