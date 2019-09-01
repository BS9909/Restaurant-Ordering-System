package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Payment extends Order {
	private static Statement statement = null;
	private static ResultSet resultSet = null;
	String tableId = null;
	boolean isPay;
	
	Payment(Connection connection, String tableId){
		super(connection);
		this.tableId = tableId;
		isPay = false;
	}
	

private ResultSet orderCalculation() throws SQLException{
	statement = connection.createStatement();
	
	resultSet = statement.executeQuery("SELECT sum(drink_price) + sum(dish_price) as 'to_pay' FROM ORDERS WHERE table_id = '" + tableId + "'");
	
	return resultSet;
	}

public void setPay() throws SQLException {
	statement = connection.createStatement();
	
	statement.executeUpdate("UPDATE orders SET is_pay = TRUE WHERE is_pay = FALSE AND table_id ='" + tableId + "'");
	isPay = true;
	}

public void displaySum() throws SQLException {
	resultSet = orderCalculation();
	while(resultSet.next()) {
		System.out.format("%-6s", resultSet.getInt(1));
	}
}

public void deleteAllOrder(String setString) throws SQLException{
	if(isPay) {
		statement = connection.createStatement();	
		statement.executeUpdate("DELETE FROM orders WHERE table_id = '" + setString + "'");
		isPay  = false;
		System.out.print("Zap³acono, wartoœæ is pay ustawiona na: " + isPay);
		}
	}
}
