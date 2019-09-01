package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MakeOrder extends Order{
	private static PreparedStatement preparedStatement= null;
	
	MakeOrder(Connection connection){
		super(connection);
	}
	
	public static void setOrder(String dishName,String drinkName, String tableId) throws SQLException {
		preparedStatement = connection.prepareStatement("INSERT INTO orders(table_id,dish_name,drink_name,dish_price,drink_price) VALUES (?,?,?,?,?)");
		preparedStatement.setString(1,takeTable(tableId));
		preparedStatement.setString(2,takeDishName(dishName));
		preparedStatement.setString(3,takeDrinkName(drinkName));
		preparedStatement.setInt(4,takeDishPrice(dishName));
		preparedStatement.setInt(5,takeDrinkPrice(drinkName));
		
		preparedStatement.executeUpdate();
	}

	public static void setOrderDish(String dishName, String tableId) throws SQLException {
		preparedStatement = connection.prepareStatement("INSERT INTO orders (table_id,dish_name,dish_price) VALUES (?,?,?)");
		
		preparedStatement.setString(1,takeTable(tableId));
		preparedStatement.setString(2,takeDishName(dishName));
		preparedStatement.setInt(3,takeDishPrice(dishName));
		
		preparedStatement.executeUpdate();
	}
	public static void setOrderDrink(String drinkName, String tableId) throws SQLException {
		preparedStatement = connection.prepareStatement("INSERT INTO orders (table_id, drink_name,drink_price) VALUES (?,?,?)");
		
		preparedStatement.setString(1,takeTable(tableId));
		preparedStatement.setString(2,takeDrinkName(drinkName));
		preparedStatement.setInt(3,takeDrinkPrice(drinkName));
		
		preparedStatement.executeUpdate();
	}
}
