package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectDishes {
	private static Connection connection = null;
	private static ResultSet resultSet	= null;
	private static PreparedStatement preparedStatement = null;
	private static MakeConnection makeConnection = null;

	
	public SelectDishes() throws SQLException{
		makeConnection = new MakeConnection(connection);
	}
	public static int countOrder(String tableName,String firstColumnName,String foodType) throws SQLException {
		String querry = "SELECT COUNT(*) FROM "+tableName+" WHERE "+firstColumnName+" = ?";

		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setString(1, foodType);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return (resultSet.getInt(1));
			}

		} catch (SQLException e) {
			System.out.print("Error in countOrder food method " + e.getMessage());
			e.printStackTrace();
		}finally {
			if(preparedStatement != null)  {
				preparedStatement.close();
			}
		}
		return -1;
	}
	
	public static ArrayList<String> insertOrder(String tableName, String firstColumnName,String secondColumnName,String foodType) throws SQLException {
		
		String querry = "SELECT "+firstColumnName+" FROM "+tableName+" WHERE "+secondColumnName+" = ?";
		ArrayList<String> orderList = new ArrayList<>();
		try {
			preparedStatement =makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setString(1, foodType);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				orderList.add(resultSet.getString(1));
			
			}

		} catch (SQLException e) {
			System.out.print("Error in insertOrder method " + e.getMessage());
			e.printStackTrace();
		}finally {
			if(preparedStatement != null)  {
				preparedStatement.close();
			}
		}
		return orderList;
	}	
      
}
