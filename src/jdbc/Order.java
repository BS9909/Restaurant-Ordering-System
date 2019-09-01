package jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

public class Order {
	
	protected static Connection connection = null;
	protected static ResultSet resultSet	= null;
	private static CallableStatement callableStatement ;
	private static Statement statement = null;
	
	Order(Connection connection){
		this.connection = connection;
	}
	
protected static String takeDishName(String dishName) throws SQLException{
		
		callableStatement = connection.prepareCall("{CALL takeOrder(?,?,?)}");
		
		callableStatement.setString(1, dishName);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.INTEGER);
		
		resultSet = callableStatement.executeQuery();
				
		String dishOutParameter1 = callableStatement.getString(2);		
		
		return dishOutParameter1;
}
protected static int takeDishPrice(String dishName) throws SQLException{
		
		callableStatement = connection.prepareCall("{CALL takeOrder(?,?,?)}");
		
		callableStatement.setString(1, dishName);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.INTEGER);
		
		resultSet = callableStatement.executeQuery();
				
		int dishOutParameter2 = callableStatement.getInt(3);		
		
		return dishOutParameter2;
	}
protected static String takeDrinkName(String drinkName) throws SQLException{
		
		callableStatement = connection.prepareCall("{CALL takeDrink(?,?,?)}");
		
		callableStatement.setString(1, drinkName);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.INTEGER);
		
		resultSet = callableStatement.executeQuery();
				
		String drinkOutParameter1 = callableStatement.getString(2);
		
		return drinkOutParameter1;

}
protected static int takeDrinkPrice(String drinkName) throws SQLException{
		
		callableStatement = connection.prepareCall("{CALL takeDrink(?,?,?)}");
		
		callableStatement.setString(1, drinkName);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		callableStatement.registerOutParameter(3, Types.INTEGER);
		
		resultSet = callableStatement.executeQuery();
				
		int drinkOutParameter2 = callableStatement.getInt(3);
		
		return drinkOutParameter2;

	}
protected static String takeTable(String tableId) throws SQLException{
		
		callableStatement = connection.prepareCall("{CALL takeTable(?,?)}");
		
		callableStatement.setString(1, tableId);
		callableStatement.registerOutParameter(2, Types.VARCHAR);
		
		resultSet = callableStatement.executeQuery();
		
		
		String tableParameter = callableStatement.getString(2);
		
		return tableParameter;

	}
public void deleteAllOrder(String setString) throws SQLException{
	statement = connection.createStatement();
	
	statement.executeUpdate("DELETE FROM orders WHERE table_id = '" + setString + "'");
}
}


