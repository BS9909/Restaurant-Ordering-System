package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersCheck {
	
	private static Connection connection = null;
	private static ResultSet resultSet	= null;
	private static PreparedStatement preparedStatement = null;
	private static MakeConnection makeConnection = null;
	
	public UsersCheck() throws SQLException{
		makeConnection = new MakeConnection(connection);
	}
	
	public boolean insertUsers(String first_name, String last_name, String email, String password) throws SQLException {
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement("SELECT COUNT(*) FROM users WHERE email = ?");
			preparedStatement.setString(1, email);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				if(resultSet.getInt(1)>0){
					return false;
				}
			}
			preparedStatement = makeConnection.getConnection().prepareStatement("INSERT INTO users(first_name,last_name,email,user_password) VALUES(?,?,?,?)");
			preparedStatement.setString(1, first_name);
			preparedStatement.setString(2, last_name);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, password);
			
			int result = preparedStatement.executeUpdate();
			return (result==1);

		}
		catch(Exception e) {
			System.out.print("Error in isertUser method: " + e.getMessage());
		}finally {
			if(preparedStatement != null)  {
				//preparedStatement.close();
			}
		}
		return false;
	}
	
	public int checkPassword(String email, String password) throws SQLException {
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement("SELECT user_id FROM users WHERE email = ? AND user_password = ?");
			preparedStatement.setString(1,email);
			preparedStatement.setString(2,password);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return (resultSet.getInt(1));
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			if(preparedStatement != null)  {
				//preparedStatement.close();
			}
		}
		return -1;
	}
}
