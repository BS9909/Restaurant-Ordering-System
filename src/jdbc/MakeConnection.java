package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
	private static Connection connection = null;
	Boolean error = false;
	private static String jdbcURL = "jdbc:mysql://localhost/reastaurant_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&failOverReadOnly=false&maxReconnects=10" ;//mariadb9.iq.pl
	private static String user = "root";//"zydor_bart";
	private static String password = "sochacki24";//"q5ej5XcBVfxWhs0gCbOz"
		
	MakeConnection(Connection connection) throws SQLException{
		this.connection = connection;
	try {
		getConnection();
		System.out.print("Connected to the data base in MakeConnection");		
		}
		catch(Exception e) {
			error = true;
			System.out.print("Error in connection in MakeConnection consturctor" + e.getMessage());
			}
		
		finally{
		if(connection!=null)			connection.close();
		if(error)						System.out.print("Problem with connection");
		else							System.out.print("Program finished");
		}
	}
	public Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL,user,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
}