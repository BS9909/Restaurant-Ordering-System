package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersCheck {
	
	private static Connection connection = null;
	private static ResultSet resultSet	= null;
	private static PreparedStatement preparedStatement = null;
	
	
	public UsersCheck() throws SQLException{
		try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/reastaurant_db?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","sochacki24");
		}
		catch(Exception e) {
			System.out.print("Error in connection with databse in UsersCheck" + e.getMessage());
		}
	}
	
	public boolean insertUsers(String first_name, String last_name, String email, String password) throws SQLException {
		try {
			preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				if(resultSet.getInt(1)>0){
					return false;
				}
			}
			preparedStatement = connection.prepareStatement("INSERT INTO users(first_name,last_name,email,user_password) VALUES(?,?,?,?)");
			preparedStatement.setString(1, first_name);
			preparedStatement.setString(2, last_name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			
			int result = preparedStatement.executeUpdate();
			return (result==1);

		}
		catch(Exception e) {
			System.out.print("Error in isertUser method: " + e.getMessage());
		}
		return false;
	}
	
	public int checkPassword(String email, String password) {
		try {
			preparedStatement = connection.prepareStatement("SELECT user_id FROM users WHERE email = ? AND user_password = ?");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,password);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return (resultSet.getInt(1));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return -1;
	}
}
