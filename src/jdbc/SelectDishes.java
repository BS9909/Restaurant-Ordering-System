package jdbc;

 import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SelectDishes {
 	private static ResultSet resultSet	= null;
	private static PreparedStatement preparedStatement = null;
	private static MakeConnection makeConnection = null;

	
	public SelectDishes() throws SQLException{
		makeConnection = MakeConnection.getInstance();
	}
	public static int countOrder(String tableName,String firstColumnName,String foodType) throws SQLException {
		String query = "SELECT COUNT(*) FROM "+tableName+" WHERE "+firstColumnName+" = ?";

		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setString(1, foodType);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				return (resultSet.getInt(1));
			}

		} catch (SQLException e) {
			System.out.print("Error in countOrder food method " + e.getMessage());
			e.printStackTrace();
		}
		return -1;
	}
	
	public static ArrayList<String> insertOrder(String tableName, String firstColumnName,String secondColumnName,String foodType) throws SQLException {
		
		String query = "SELECT "+firstColumnName+" FROM "+tableName+" WHERE "+secondColumnName+" = ?";
		ArrayList<String> orderList = new ArrayList<>();
		try {
			preparedStatement =makeConnection.getConnection().prepareStatement(query);
			
			preparedStatement.setString(1, foodType);

			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				orderList.add(resultSet.getString(1));
			
			}

		} catch (SQLException e) {
			System.out.print("Error in insertOrder method " + e.getMessage());
			e.printStackTrace();
		}
		return orderList;
	}	
      
}
