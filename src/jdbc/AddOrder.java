package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;

public class AddOrder {
	private static Connection connection = null;
	private static ResultSet resultSet	= null;
	private static PreparedStatement preparedStatement = null;
	private static CallableStatement callableStatement ;
	private static MakeConnection makeConnection = null;
	
	public AddOrder() throws SQLException {
		makeConnection = new MakeConnection(connection);
	}
	public void insertDish(String tableId, String dishName) throws SQLException {
		String querry = "INSERT INTO orders(table_id, dish_name,dish_price) VALUES (?,?,?)";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setString(1, tableId);
			preparedStatement.setString(2, dishName);
			preparedStatement.setInt(3, takeDishPrice(dishName));
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(preparedStatement != null)  {
				preparedStatement.close();
			}
		}
	}
	public void insertDrink(String tableId, String drinkName) throws SQLException {
		String querry = "INSERT INTO orders(table_id, drink_name,drink_price) VALUES (?,?,?)";
		try {
			preparedStatement = makeConnection.getConnection().prepareStatement(querry);
			
			preparedStatement.setString(1, tableId);
			preparedStatement.setString(2, drinkName);
			preparedStatement.setInt(3, takeDrinkPrice(drinkName));
			
			preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(preparedStatement != null)  {
				preparedStatement.close();
			}
		}
	}
private static int takeDishPrice(String dishName) throws SQLException{
		
		callableStatement = makeConnection.getConnection().prepareCall("{CALL takeOrder(?,?,?)}");
		
		callableStatement.setString(1, dishName);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.INTEGER);
		
		resultSet = callableStatement.executeQuery();
				
		int dishOutParameter2 = callableStatement.getInt(3);		
		
		return dishOutParameter2;
	}
private static  int takeDrinkPrice(String drinkName) throws SQLException{
	
	callableStatement = makeConnection.getConnection().prepareCall("{CALL takeDrink(?,?,?)}");
	
	callableStatement.setString(1, drinkName);
	callableStatement.registerOutParameter(2, Types.VARCHAR);
	callableStatement.registerOutParameter(3, Types.INTEGER);
	
	resultSet = callableStatement.executeQuery();
			
	int drinkOutParameter2 = callableStatement.getInt(3);
	
	return drinkOutParameter2;

}
}
