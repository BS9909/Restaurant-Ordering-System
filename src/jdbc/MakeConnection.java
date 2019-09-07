package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MakeConnection {
	private static Connection connection = null;
	Boolean error = false;
	private static String jdbcURL = "jdbc:mysql://localhost:3306/reastaurant_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String user = "root";
	private static String password = "sochacki24";

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
		connection = DriverManager.getConnection(jdbcURL,user,password);
		return connection;
	}
}