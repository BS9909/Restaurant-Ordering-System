package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class EditOrder extends Order {

	private static Statement statement= null;
	
	EditOrder(Connection connection) {
		super(connection);
	}

public void editDish(String setNewDish, String oldDish, String setTable_id) throws SQLException{
	statement = connection.createStatement();
	
	statement.executeUpdate("UPDATE orders SET dish_name = '" + setNewDish + "'" + "WHERE dish_name = '" + oldDish  + "'" + "AND table_id ='" +setTable_id+"'");
	statement.executeUpdate("UPDATE orders SET dish_price = '" + takeDishPrice(setNewDish) + "'" + "WHERE dish_price = '" + takeDishPrice(oldDish)  + "'" + "AND table_id ='" +setTable_id+"'");
     
	}
public void editDrink(String setNewDrink, String oldDrink, String setTable_id) throws SQLException{
	statement = connection.createStatement();
	
	statement.executeUpdate("UPDATE orders SET drink_name = '" + setNewDrink + "'" + "WHERE drink_name = '" + oldDrink  + "'" + "AND table_id ='" +setTable_id+"'");
	statement.executeUpdate("UPDATE orders SET drink_price = '" + takeDrinkPrice(setNewDrink) + "'" + "WHERE drink_price = '" + takeDrinkPrice(oldDrink)  + "'" + "AND table_id ='" +setTable_id+"'");
	}
public void deleteDish(String oldDish, String setTable_id) throws SQLException{
	statement = connection.createStatement();
	
	statement.executeUpdate("UPDATE orders SET dish_name = " + "NULL" + " WHERE dish_name = '" + oldDish  + "' AND table_id ='" +setTable_id+"'");
	statement.executeUpdate("UPDATE orders SET dish_price = " + "NULL" + " WHERE dish_price ='" + takeDishPrice(oldDish)  + "' AND table_id ='" +setTable_id+"'");
     
	}

public void deleteDrink(String oldDrink, String setTable_id) throws SQLException{
	statement = connection.createStatement();
	
	statement.executeUpdate("UPDATE orders SET drink_name = " + "NULL"  + " WHERE drink_name = '" + oldDrink + "' AND table_id ='" +setTable_id+"'");
	statement.executeUpdate("UPDATE orders SET drink_price = " + "NULL"  + " WHERE drink_price = '" + takeDrinkPrice(oldDrink) + "' AND table_id ='" +setTable_id+"'");
	}
}




