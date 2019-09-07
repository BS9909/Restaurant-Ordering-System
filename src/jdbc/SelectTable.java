package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class SelectTable {
	
	boolean error  = false;
	private static Connection connection = null;
	private static ResultSet resultSet	= null;
	private static PreparedStatement preparedStatement = null;
	private static MakeConnection makeConnection = null;

	
	public SelectTable() throws SQLException{
		makeConnection = new MakeConnection(connection);

	}
	public static int countTables(boolean isAvailable) throws SQLException {
		String querry = "SELECT COUNT(*) FROM restaurants_tables WHERE table_available = ?";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setBoolean(1, isAvailable);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return  resultSet.getInt(1);
			}
		}catch(Exception e) {
			System.out.print("Error in countTables method from selectTable class" + e.getMessage());
			e.printStackTrace();

		}finally {
			if(preparedStatement != null)  {
				preparedStatement.close();
			}
		}
		return -1;
	}
	public static ArrayList<String> getTables(Boolean isAvailable) throws SQLException{
		String querry = "SELECT table_id FROM restaurants_tables WHERE table_available = ?";
		ArrayList<String> tablesList = new ArrayList<>();

		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setBoolean(1, isAvailable);
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				  tablesList.add(resultSet.getString(1));
				
			}
		}catch(Exception e) {
			System.out.print("Error in getTables method from selectTable class" + e.getMessage());
			e.printStackTrace();
		}finally {
			if(preparedStatement != null)  {
				preparedStatement.close();
			}
		}
		return tablesList;
	}
}